package mk.vozenred.bustimetableapp.util

object Constants {

    // Room Database
    const val RELATIONS_DATABASE_NAME = "relations_database"
    const val RELATIONS_DATABASE_TABLE = "relations_table"
    const val FAVORITE_RELATIONS_TABLE = "favorite_relations_table"

    // Navigation Routes
    const val SPLASH_SCREEN = "splash"
    const val SEARCH_SCREEN = "search"
    const val RELATIONS_SCREEN = "relationsList"
    const val FAVORITES_SCREEN = "favorites"
    const val SELECT_FROM_DESTINATION_SCREEN = "selectFromDestination"
    const val SELECT_TO_DESTINATION_SCREEN = "selectToDestination"

    // DataStore Preferences
    const val PREFERENCE_NAME = "app_preferences"
    const val DB_VERSION_PREFERENCE_KEY = "db_version"

    // Firebase - Firestore
    const val RELATIONS_COLLECTION = "relacii"
    const val DEPARTURE_TIME = "departureTime"
    const val FIREBASE_CONFIG_DB_VERSION_KEY = "databaseVersion"

    // Utils
    const val SPLASH_SCREEN_DELAY = 500L
}