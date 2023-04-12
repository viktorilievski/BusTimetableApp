package mk.vozenred.bustimetableapp.util

object Constants {

  // Room Database
  const val RELATIONS_DATABASE_NAME = "relations_database"
  const val RELATIONS_DATABASE_TABLE = "relations_table"

  // Navigation Routes
  const val SPLASH_SCREEN = "splash"
  const val NO_CONNECTION_SCREEN = "no_connection"
  const val SEARCH_SCREEN = "search"
  const val CONTACT_SCREEN = "contact"
  const val REPORT_SCREEN = "report/{relationId}"
  const val RELATIONS_SCREEN = "relationsList"
  const val FAVORITES_SCREEN = "favorites"
  const val INFO_SCREEN = "info"
  const val SELECT_FROM_DESTINATION_SCREEN = "selectFromDestination"
  const val SELECT_TO_DESTINATION_SCREEN = "selectToDestination"

  // DataStore Preferences
  const val PREFERENCE_NAME = "app_preferences"
  const val DB_VERSION_PREFERENCE_KEY = "db_version"
  const val LIVE_RELATION_PREFERENCE_KEY = "live_relation"

  // Firebase - Firestore
  const val RELATIONS_COLLECTION = "relacii"
  const val DEPARTURE_TIME = "departureTime"
  const val FIREBASE_CONFIG_DB_VERSION_KEY = "databaseVersion"

  // Utils
  const val SPLASH_SCREEN_DELAY = 500L

  // Argument Key
  const val RELATION_ARGUMENT_KEY = "relationId"

  // Contact constants
  const val CONTACT_NUMBER = "tel:070510928"
}