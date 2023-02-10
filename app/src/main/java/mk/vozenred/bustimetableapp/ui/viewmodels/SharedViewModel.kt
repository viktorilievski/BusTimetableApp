package mk.vozenred.bustimetableapp.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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

  private val _relations: MutableStateFlow<List<Relation>> = MutableStateFlow(mutableListOf())
  val relations: StateFlow<List<Relation>> = _relations

  private val _selectedRelation: MutableStateFlow<Relation> = MutableStateFlow(Relation())
  val selectedRelation: StateFlow<Relation> = _selectedRelation

  fun getRelations() {
    selectedCompany.value = "Сите"
    viewModelScope.launch(Dispatchers.IO) {
      relationsRepository.getRelations(_startPointSelected.value, _endPointSelected.value)
        .collect {
          _relations.value = it
        }
    }
  }

  fun getAllStartPoints() {
    viewModelScope.launch(Dispatchers.IO) {
      relationsRepository.getAllStartingPoints().collect {
        startPoints.value = it
        filteredStartPoints.value = startPoints.value
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

  fun setSelectedCompany(companyName: String) {
    selectedCompany.value = companyName
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

  fun getRelationsForSelectedCompany(companyName: String) {
    if (companyName.isEmpty()) {
      getRelations()
    } else {
      viewModelScope.launch(Dispatchers.IO) {
        relationsRepository.getRelationsForSelectedCompany(
          _startPointSelected.value,
          _endPointSelected.value,
          companyName
        ).collect {
          _relations.value = it
        }
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

  fun updateReportRelationFields(selectedRelation: Relation?) {
    TODO("Not yet implemented")
  }

}