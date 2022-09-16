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
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val relationsRepository: RelationsRepository
) : ViewModel() {

    private val _startPointSelected: MutableState<String> = mutableStateOf("")
    val startPointSelected: State<String> = _startPointSelected

    private val _endPointSelected: MutableState<String> = mutableStateOf("")
    val endPointSelected: State<String> = _endPointSelected

    val startPoints: MutableState<List<String>> = mutableStateOf(mutableListOf())
    val endPoints: MutableState<List<String>> = mutableStateOf(mutableListOf())

    private val _relations: MutableStateFlow<List<Relation>> = MutableStateFlow(mutableListOf())
    val relations: StateFlow<List<Relation>> = _relations

    fun getRelations() {
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
            }
        }
    }

    fun getAllEndPoints() {
        viewModelScope.launch(Dispatchers.IO) {
            relationsRepository.getAllEndPoints().collect {
                endPoints.value = it
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
                }
        }
    }
}