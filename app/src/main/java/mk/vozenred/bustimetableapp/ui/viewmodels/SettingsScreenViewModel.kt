package mk.vozenred.bustimetableapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mk.vozenred.bustimetableapp.data.repositories.local.DataStoreRepository
import mk.vozenred.bustimetableapp.util.Constants.LIVE_RELATION_PREFERENCE_KEY
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
  private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

  private val _liveRelationToggle: MutableStateFlow<Boolean> = MutableStateFlow(false)
  val liveRelationToggle: StateFlow<Boolean> = _liveRelationToggle

  init {
    viewModelScope.launch {
      val liveRelationValue = dataStoreRepository.read(LIVE_RELATION_PREFERENCE_KEY)
      if (liveRelationValue == null) {
        saveToDataStore(LIVE_RELATION_PREFERENCE_KEY, false)
        _liveRelationToggle.value = false
      } else {
        _liveRelationToggle.value =
          dataStoreRepository.read(LIVE_RELATION_PREFERENCE_KEY) as Boolean
      }
    }
  }

  fun saveToDataStore(key: String, value: Boolean) {
    viewModelScope.launch {
      Log.d("SettingsScreenViewModel", "Key -> $key | Value -> $value")
      _liveRelationToggle.value = value
      dataStoreRepository.save(key, value)
    }
  }
}