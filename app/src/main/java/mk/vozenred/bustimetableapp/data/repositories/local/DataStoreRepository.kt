package mk.vozenred.bustimetableapp.data.repositories.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
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

  suspend fun save(key: String, value: Any) {
    val dataStoreKey = getDataStoreKey(key, value)
    dataStore.edit { preference ->
      preference[dataStoreKey!!] = value
    }
  }

  @Suppress("UNCHECKED_CAST")
  private fun getDataStoreKey(key: String, value: Any): Preferences.Key<Any>? {
    return when (value) {
      is String -> {
        return stringPreferencesKey(key) as Preferences.Key<Any>
      }
      is Boolean -> {
        return booleanPreferencesKey(key) as Preferences.Key<Any>
      }
      is Int -> {
        return intPreferencesKey(key) as Preferences.Key<Any>
      }
      is Double -> {
        return doublePreferencesKey(key) as Preferences.Key<Any>
      }
      else -> {
        null
      }
    }
  }

  suspend fun read(key: String): Any? {
    val dataStoreKey = stringPreferencesKey(key)
    val preferences = dataStore.data.first()
    return preferences[dataStoreKey]
  }
}