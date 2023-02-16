package mk.vozenred.bustimetableapp.navigation

import android.util.Log
import androidx.navigation.NavHostController
import mk.vozenred.bustimetableapp.util.Constants.CONTACT_SCREEN
import mk.vozenred.bustimetableapp.util.Constants.FAVORITES_SCREEN
import mk.vozenred.bustimetableapp.util.Constants.NO_CONNECTION_SCREEN
import mk.vozenred.bustimetableapp.util.Constants.RELATIONS_SCREEN
import mk.vozenred.bustimetableapp.util.Constants.REPORT_SCREEN
import mk.vozenred.bustimetableapp.util.Constants.SEARCH_SCREEN
import mk.vozenred.bustimetableapp.util.Constants.SELECT_FROM_DESTINATION_SCREEN
import mk.vozenred.bustimetableapp.util.Constants.SELECT_TO_DESTINATION_SCREEN
import mk.vozenred.bustimetableapp.util.Constants.SPLASH_SCREEN

class Screens(navController: NavHostController) {
  val splashToSearchScreen: () -> Unit = {
    navController.navigate(route = SEARCH_SCREEN) {
      popUpTo(SPLASH_SCREEN) { inclusive = true }
    }
  }

  val splashToNoConnectionScreen: () -> Unit = {
    navController.navigate(route = NO_CONNECTION_SCREEN) {
      popUpTo(SPLASH_SCREEN) { inclusive = true }
    }
  }

  val noConnectionToSplashScreen: () -> Unit = {
    navController.navigate(route = SPLASH_SCREEN) {
      popUpTo(NO_CONNECTION_SCREEN) { inclusive = true }
    }
  }

  val noConnectionToSearchScreen: () -> Unit = {
    navController.navigate(route = SEARCH_SCREEN) {
      popUpTo(NO_CONNECTION_SCREEN) { inclusive = true }
    }
  }

  val searchToRelationsScreen: () -> Unit = {
    navController.navigate(route = RELATIONS_SCREEN)
  }

  val searchToStartDestinationScreen: () -> Unit = {
    navController.navigate(route = SELECT_FROM_DESTINATION_SCREEN)
  }

  val searchToEndDestinationScreen: () -> Unit = {
    navController.navigate(route = SELECT_TO_DESTINATION_SCREEN)
  }

  val selectFromDestinationToSearch: () -> Unit = {
      navController.popBackStack(route = SEARCH_SCREEN, inclusive = false)
    }

  val selectToDestinationToSearch: () -> Unit = {
      navController.popBackStack(route = SEARCH_SCREEN, inclusive = false)
    }

  val navigateToSearchScreenFromDrawer: () -> Unit = {
    navController.popBackStack(route = SEARCH_SCREEN, inclusive = false)
  }

  val navigateToContactScreenFromDrawer: () -> Unit = {
    navController.navigate(route = CONTACT_SCREEN)
  }

  val navigateToReportScreen: (Int) -> Unit = { relationId ->
    navController.navigate(route = "report/${relationId}") {
      popUpTo(RELATIONS_SCREEN) { inclusive = true }
    }
  }

  val navigateToRelationsScreen: () -> Unit = {
    navController.navigateUp()
  }

  val navigateToFavoriteRelationsFromDrawer: () -> Unit = {
    navController.navigate(route = FAVORITES_SCREEN)
  }
}