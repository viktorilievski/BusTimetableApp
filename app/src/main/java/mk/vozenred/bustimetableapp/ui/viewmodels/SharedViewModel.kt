package mk.vozenred.bustimetableapp.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mk.vozenred.bustimetableapp.components.topbars.utils.PointType
import mk.vozenred.bustimetableapp.components.topbars.utils.SortOption
import mk.vozenred.bustimetableapp.data.model.Relation
import mk.vozenred.bustimetableapp.data.repositories.local.RelationsRepository
import mk.vozenred.bustimetableapp.util.SearchAppBarState
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
  private val relationsRepository: RelationsRepository
) : ViewModel() {

  private val _startPointSelected: MutableState<String> = mutableStateOf("")
  val startPointSelected: State<String> = _startPointSelected

  private val _endPointSelected: MutableState<String> = mutableStateOf("")
  val endPointSelected: State<String> = _endPointSelected

  private val startPoints: MutableState<List<String>> = mutableStateOf(mutableListOf())
  private val endPoints: MutableState<List<String>> = mutableStateOf(mutableListOf())
  var filteredStartPoints: MutableState<List<String>> = mutableStateOf(mutableListOf())
  var filteredEndPoints: MutableState<List<String>> = mutableStateOf(mutableListOf())
  val companiesForRelation: MutableState<List<String>> = mutableStateOf(mutableListOf())
  val selectedCompany: MutableState<String> = mutableStateOf("Сите")

  private val searchAppBarText: MutableState<String> = mutableStateOf("")
  val searchAppBarState: MutableState<SearchAppBarState> = mutableStateOf(SearchAppBarState.CLOSED)

  private val _relations: MutableSharedFlow<List<Relation>> = MutableSharedFlow()
  val relations: SharedFlow<List<Relation>> = _relations

  private val _selectedRelation: MutableStateFlow<Relation> = MutableStateFlow(Relation())
  val selectedRelation: StateFlow<Relation> = _selectedRelation

  fun getRelations(companyName: String?) {
    selectedCompany.value = companyName ?: "Сите"
    viewModelScope.launch(Dispatchers.IO) {
      _relations.emit(
        relationsRepository.getRelations(
          _startPointSelected.value,
          _endPointSelected.value,
          companyName
        )
      )
    }
  }

  fun getAllStartPoints() {
    viewModelScope.launch(Dispatchers.IO) {
      relationsRepository.getStartAllPointsAlphaSorted().collect {
        startPoints.value = it
        filteredStartPoints.value = startPoints.value
      }
    }
  }

  fun sortPoints(sortOption: SortOption, pointType: PointType) {
    viewModelScope.launch(Dispatchers.IO) {
      when (pointType) {
        PointType.START_POINT -> {
          when (sortOption) {
            SortOption.ALPHABETICAL -> {
              relationsRepository.getStartAllPointsAlphaSorted().collect {
                startPoints.value = it
                filteredStartPoints.value = startPoints.value
              }
            }
            SortOption.ALPHABETICAL_INVERTED -> {
              relationsRepository.getStartAllPointsAlphaSortedInv().collect {
                startPoints.value = it
                filteredStartPoints.value = startPoints.value
              }
            }
            SortOption.MAX_RELATIONS -> {
              relationsRepository.getStartPointsMaxRelationsSorted().collect {
                startPoints.value = it
                filteredStartPoints.value = startPoints.value
              }
            }
            SortOption.MIN_RELATIONS -> {
              relationsRepository.getStartPointsMinRelationsSorted().collect {
                startPoints.value = it
                filteredStartPoints.value = startPoints.value
              }
            }
          }
        }
        PointType.END_POINT -> {
          when (sortOption) {
            SortOption.ALPHABETICAL -> {
              relationsRepository.getAllEndPointsAlphaSorted(_startPointSelected.value).collect {
                endPoints.value = it
                filteredEndPoints.value = endPoints.value
              }
            }
            SortOption.ALPHABETICAL_INVERTED -> {
              relationsRepository.getAllEndPointsAlphaSortedInv(_startPointSelected.value).collect {
                endPoints.value = it
                filteredEndPoints.value = endPoints.value
              }
            }
            SortOption.MAX_RELATIONS -> {
              relationsRepository.getEndPointsMaxRelationsSorted(_startPointSelected.value)
                .collect {
                  endPoints.value = it
                  filteredEndPoints.value = endPoints.value
                }
            }
            SortOption.MIN_RELATIONS -> {
              relationsRepository.getEndPointsMinRelationsSorted(_startPointSelected.value)
                .collect {
                  endPoints.value = it
                  filteredEndPoints.value = endPoints.value
                }
            }
          }
        }
      }
    }
  }

  fun getCompaniesForSelectedRelation() {
    viewModelScope.launch(Dispatchers.IO) {
      relationsRepository.getCompaniesForRelation(
        _startPointSelected.value,
        _endPointSelected.value
      ).collect {
        companiesForRelation.value = it
      }
    }
  }

  fun setStartPoint(startPoint: String) {
    _startPointSelected.value = startPoint
  }

  fun setEndPoint(endPoint: String) {
    _endPointSelected.value = endPoint
  }

  fun clearEndPoint() {
    _endPointSelected.value = ""
  }

  fun getEndPointsForSelectedStartPoint() {
    viewModelScope.launch(Dispatchers.IO) {
      relationsRepository.getEndPointsForSelectedStartPoint(_startPointSelected.value)
        .collect {
          endPoints.value = it
          filteredEndPoints.value = endPoints.value
        }
    }
  }


  fun searchAppBarOnCloseClick() {
    filteredStartPoints.value = startPoints.value
    filteredEndPoints.value = endPoints.value
    if (searchAppBarText.value.isEmpty()) {
      closeSearchTopAppbar()
    } else {
      searchAppBarText.value = ""
    }
  }

  fun getStartPointsForEnteredText(enteredText: String) {
    searchAppBarText.value = enteredText
    filteredStartPoints.value = startPoints.value.filter { startPoint ->
      startPoint.lowercase().startsWith(enteredText.lowercase())
    }
  }

  fun getEndPointsForEnteredText(enteredText: String) {
    searchAppBarText.value = enteredText
    filteredEndPoints.value = endPoints.value.filter { endPoint ->
      endPoint.lowercase().startsWith(enteredText.lowercase())
    }
  }

  fun clearSearchAppBarText() {
    searchAppBarText.value = ""
  }

  fun topAppBarOnSearchClick() {
    searchAppBarState.value = SearchAppBarState.OPENED
  }

  fun closeSearchTopAppbar() {
    searchAppBarState.value = SearchAppBarState.CLOSED
  }

  fun getSelectedRelation(relationId: Int) {
    viewModelScope.launch(Dispatchers.IO) {
      relationsRepository.getRelation(relationId).collect {
        _selectedRelation.value = it
      }
    }
  }

  fun swapCities() {
    val tempCity = _startPointSelected.value
    _startPointSelected.value = _endPointSelected.value
    _endPointSelected.value = tempCity
  }

  fun setRelationFavoriteStatus(relationId: Int, isRelationFavorite: Boolean) {
    viewModelScope.launch(Dispatchers.IO) {
      relationsRepository.setRelationFavoriteStatus(relationId, isRelationFavorite)
      val companyName: String? = if (selectedCompany.value == "Сите") {
        null
      } else {
        selectedCompany.value
      }
      getRelations(companyName)
    }
  }
}