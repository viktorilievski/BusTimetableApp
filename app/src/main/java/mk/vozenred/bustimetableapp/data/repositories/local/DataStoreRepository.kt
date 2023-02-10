package mk.vozenred.bustimetableapp.data.repositories.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.first
import mk.vozenred.bustimetableapp.util.Constants.PREFERENCE_NAME
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

@ViewModelScoped
class DataStoreRepository @Inject constructor(
  @ApplicationContext private val context: Context
) {

  private val dataStore = context.dataStore

  suspend fun save(key: String, value: String) {
    val dataStoreKey = stringPreferencesKey(key)
    dataStore.edit { preference ->
      preference[dataStoreKey] = value
    }
  }

  suspend fun read(key: String): String? {
    val dataStoreKey = stringPreferencesKey(key)
    val preferences = dataStore.data.first()
    return preferences[dataStoreKey]
  }
}