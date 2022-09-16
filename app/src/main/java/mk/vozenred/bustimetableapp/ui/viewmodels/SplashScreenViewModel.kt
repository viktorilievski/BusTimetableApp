package mk.vozenred.bustimetableapp.ui.viewmodels

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

    private var _loading: MutableLiveData<Boolean> = MutableLiveData(true)
    val loading: LiveData<Boolean> = _loading

    private val _dataStoreValue: MutableStateFlow<String?> = MutableStateFlow(null)
    val dataStoreValue: StateFlow<String?> = _dataStoreValue

    init {
        readFromDataStore(DB_VERSION_PREFERENCE_KEY)
    }

    fun fetchAndStoreRelationsToLocalDb() {
        viewModelScope.launch {
            _loading.value = true
            withContext(Dispatchers.IO) {
                val allRelations = firestoreRepository.getRelationsFromFirestore().data
                for (relation in allRelations!!) {
                    relationsRepository.addRelation(relation)
                }
            }
            saveToDataStore(DB_VERSION_PREFERENCE_KEY, readFirestoreDatabaseVersion())
            _loading.value = false
        }
    }

    fun saveToDataStore(key: String, value: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.save(key, value)
        }
    }

    fun readFromDataStore(key: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _dataStoreValue.value = dataStoreRepository.read(key)
        }
    }

    fun setLoadingState(value: Boolean) {
        _loading.value = value
    }

    fun readFirestoreDatabaseVersion(): String {
        return firebaseRemoteConfigDb.getDbVersion()
    }

}