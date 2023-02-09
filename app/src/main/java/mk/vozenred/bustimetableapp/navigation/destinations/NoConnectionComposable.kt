package mk.vozenred.bustimetableapp.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import mk.vozenred.bustimetableapp.ui.screens.no_connection.NoConnectionScreen
import mk.vozenred.bustimetableapp.ui.viewmodels.SplashScreenViewModel
import mk.vozenred.bustimetableapp.util.Constants.NO_CONNECTION_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.noConnectionComposable(
  navigateToSplashScreen: () -> Unit,
  navigateToSearchScreen: () -> Unit,
  splashScreenViewModel: SplashScreenViewModel
) {
  composable(
    route = NO_CONNECTION_SCREEN
  ) {
    NoConnectionScreen(
      navigateToSplashScreen = navigateToSplashScreen,
      navigateToSearchScreen = navigateToSearchScreen,
      splashScreenViewModel = splashScreenViewModel
    )
  }
}