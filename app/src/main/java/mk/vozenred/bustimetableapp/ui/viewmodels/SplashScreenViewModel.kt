package mk.vozenred.bustimetableapp.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mk.vozenred.bustimetableapp.data.repositories.firebase.FirebaseRemoteConfigDb
import mk.vozenred.bustimetableapp.data.repositories.firebase.FirestoreRepository
import mk.vozenred.bustimetableapp.data.repositories.local.DataStoreRepository
import mk.vozenred.bustimetableapp.data.repositories.local.RelationsRepository
import mk.vozenred.bustimetableapp.util.Constants.DB_VERSION_PREFERENCE_KEY
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
  private val relationsRepository: RelationsRepository,
  private val firestoreRepository: FirestoreRepository,
  private val dataStoreRepository: DataStoreRepository,
  private val firebaseRemoteConfigDb: FirebaseRemoteConfigDb
) : ViewModel() {

  var networkStatus: MutableState<Boolean> = mutableStateOf(false)

  private var _loading: MutableLiveData<LoadingState> = MutableLiveData(LoadingState.Loading)
  val loading: LiveData<LoadingState> = _loading

  private val _dataStoreValue: MutableStateFlow<String?> = MutableStateFlow(null)
  val dataStoreValue: StateFlow<String?> = _dataStoreValue

  init {
    readDatabaseVersionFromDataStore(DB_VERSION_PREFERENCE_KEY)
  }

  fun fetchAndStoreRelationsToLocalDb() {
    viewModelScope.launch {
      _loading.value = LoadingState.Loading
      withContext(Dispatchers.IO) {
        val allRelations = firestoreRepository.getRelationsFromFirestore().data
        for (relation in allRelations!!) {
          relationsRepository.addRelation(relation)
        }
      }
      saveDatabaseVersionToDataStore(DB_VERSION_PREFERENCE_KEY, readFirestoreDatabaseVersion())
      _loading.value = LoadingState.Success
    }
  }

  fun saveDatabaseVersionToDataStore(key: String, value: String) {
    viewModelScope.launch(Dispatchers.IO) {
      dataStoreRepository.save(key, value)
    }
  }

  fun readDatabaseVersionFromDataStore(key: String) {
    viewModelScope.launch(Dispatchers.IO) {
      _dataStoreValue.value = dataStoreRepository.read(key)
    }
  }

  fun setLoadingState(value: LoadingState) {
    _loading.value = value
  }

  fun readFirestoreDatabaseVersion(): String {
    return firebaseRemoteConfigDb.getDbVersion()
  }
}

sealed class LoadingState {
  object Success : LoadingState()
  object Loading : LoadingState()
  object Failed : LoadingState()
}