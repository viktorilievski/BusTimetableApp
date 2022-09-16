package mk.vozenred.bustimetableapp.data.repositories.firebase

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import mk.vozenred.bustimetableapp.util.Constants.FIREBASE_CONFIG_DB_VERSION_KEY

class FirebaseRemoteConfigDb {

    private val config = Firebase.remoteConfig

    init {
        initializeRemoteConfig()
    }

    private fun initializeRemoteConfig() {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        config.setConfigSettingsAsync(configSettings)
        config.fetchAndActivate()
    }

    fun getDbVersion(): String {
        return config.getString(FIREBASE_CONFIG_DB_VERSION_KEY)
    }
}