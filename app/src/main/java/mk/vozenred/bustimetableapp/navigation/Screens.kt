package mk.vozenred.bustimetableapp.navigation

import androidx.navigation.NavHostController
import mk.vozenred.bustimetableapp.util.Constants.RELATIONS_SCREEN
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

    val searchToRelationsScreen: () -> Unit = {
        navController.navigate(route = RELATIONS_SCREEN)
    }

    val searchToStartDestinationScreen: () -> Unit = {
        navController.navigate(route = SELECT_FROM_DESTINATION_SCREEN)
    }

    val searchToEndDestinationScreen: () -> Unit = {
        navController.navigate(route = SELECT_TO_DESTINATION_SCREEN)
    }

    val selectFromDestinationToSearch: (shouldClearBackstack: Boolean) -> Unit = { shouldClearBackstack ->
        navController.navigate(route = SEARCH_SCREEN) {
            popUpTo(SELECT_FROM_DESTINATION_SCREEN) { inclusive = true }
            popUpTo(SEARCH_SCREEN) { inclusive = shouldClearBackstack }
        }
    }

    val selectToDestinationToSearch: (shouldClearBackstack: Boolean) -> Unit = { shouldClearBackstack ->
        navController.navigate(route = SEARCH_SCREEN) {
            popUpTo(SELECT_TO_DESTINATION_SCREEN) { inclusive = true }
            popUpTo(SEARCH_SCREEN) { inclusive = shouldClearBackstack }
        }
    }
}