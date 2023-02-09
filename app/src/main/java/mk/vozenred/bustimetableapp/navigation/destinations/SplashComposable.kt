package mk.vozenred.bustimetableapp.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import mk.vozenred.bustimetableapp.ui.screens.splash.SplashScreen
import mk.vozenred.bustimetableapp.ui.viewmodels.SplashScreenViewModel
import mk.vozenred.bustimetableapp.util.Constants.SPLASH_SCREEN

fun NavGraphBuilder.splashComposable(
  navigateToSearchScreen: () -> Unit,
  navigateToNoConnectionScreen: () -> Unit,
  splashScreenViewModel: SplashScreenViewModel
) {
  composable(
    route = SPLASH_SCREEN,
  ) {
    SplashScreen(
      navigateToSearchScreen = navigateToSearchScreen,
      navigateToNoConnectionScreen = navigateToNoConnectionScreen,
      splashScreenViewModel = splashScreenViewModel
    )
  }
}