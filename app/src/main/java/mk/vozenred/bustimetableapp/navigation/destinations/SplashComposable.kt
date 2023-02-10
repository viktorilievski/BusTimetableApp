package mk.vozenred.bustimetableapp.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import mk.vozenred.bustimetableapp.ui.screens.splash.SplashScreen
import mk.vozenred.bustimetableapp.ui.viewmodels.SplashScreenViewModel
import mk.vozenred.bustimetableapp.util.Constants.SPLASH_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.splashComposable(
  navigateToSearchScreen: () -> Unit,
  navigateToNoConnectionScreen: () -> Unit,
  splashScreenViewModel: SplashScreenViewModel
) {
  composable(
    route = SPLASH_SCREEN,
    exitTransition = {
      slideOutVertically(
        targetOffsetY = { fullHeight -> -fullHeight }
      )
    }
  ) {
    SplashScreen(
      navigateToSearchScreen = navigateToSearchScreen,
      navigateToNoConnectionScreen = navigateToNoConnectionScreen,
      splashScreenViewModel = splashScreenViewModel
    )
  }

}