package mk.vozenred.bustimetableapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import mk.vozenred.bustimetableapp.navigation.destinations.*
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel
import mk.vozenred.bustimetableapp.ui.viewmodels.SplashScreenViewModel
import mk.vozenred.bustimetableapp.util.Constants.SPLASH_SCREEN

@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
    splashScreenViewModel: SplashScreenViewModel
) {
    val screen = remember(navController) {
        Screens(navController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {
        splashComposable(
            navigateToSearchScreen = screen.splashToSearchScreen,
            navigateToNoConnectionScreen = screen.splashToNoConnectionScreen,
            splashScreenViewModel = splashScreenViewModel
        )
        noConnectionComposable(
            navigateToSplashScreen = screen.noConnectionToSplashScreen,
            navigateToSearchScreen = screen.noConnectionToSearchScreen,
            splashScreenViewModel = splashScreenViewModel
        )
        searchComposable(
            navigateToListRelations = screen.searchToRelationsScreen,
            navigateToStartDestination = screen.searchToStartDestinationScreen,
            navigateToEndDestination = screen.searchToEndDestinationScreen,
            sharedViewModel = sharedViewModel
        )
        relationsComposable(
            sharedViewModel = sharedViewModel
        )
        selectFromDestinationComposable(
            navigateToSearchScreen = screen.selectFromDestinationToSearch,
            sharedViewModel = sharedViewModel
        )
        selectToDestinationComposable(
            navigateToSearchScreen = screen.selectToDestinationToSearch,
            sharedViewModel = sharedViewModel
        )
    }
}