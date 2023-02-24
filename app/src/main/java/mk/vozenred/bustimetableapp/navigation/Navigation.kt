package mk.vozenred.bustimetableapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import mk.vozenred.bustimetableapp.navigation.destinations.*
import mk.vozenred.bustimetableapp.ui.viewmodels.FavoriteRelationsViewModel
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel
import mk.vozenred.bustimetableapp.ui.viewmodels.SplashScreenViewModel
import mk.vozenred.bustimetableapp.util.Constants.SPLASH_SCREEN

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupNavigation(
  navController: NavHostController,
  sharedViewModel: SharedViewModel,
  favoriteRelationsViewModel: FavoriteRelationsViewModel,
  splashScreenViewModel: SplashScreenViewModel
) {
  val screen = remember(navController) {
    Screens(navController = navController)
  }

  AnimatedNavHost(
    navController = navController,
    startDestination = SPLASH_SCREEN,

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
      navigateToContactScreen = screen.navigateToContactScreenFromDrawer,
      navigateToFavoriteRelationsScreen = screen.navigateToFavoriteRelationsFromDrawer,
      sharedViewModel = sharedViewModel
    )
    relationsComposable(
      sharedViewModel = sharedViewModel,
      navigateToSearchScreen = screen.navigateToSearchScreenFromDrawer,
      navigateToContactScreen = screen.navigateToContactScreenFromDrawer,
      navigateToReportScreen = screen.navigateToReportScreen,
      navigateToFavoriteRelationsScreen = screen.navigateToFavoriteRelationsFromDrawer
    )
    selectFromDestinationComposable(
      navigateToSearchScreen = screen.selectFromDestinationToSearch,
      sharedViewModel = sharedViewModel
    )
    selectToDestinationComposable(
      navigateToSearchScreen = screen.selectToDestinationToSearch,
      sharedViewModel = sharedViewModel
    )
    contactComposable(
      navigateToSearchScreen = screen.navigateToSearchScreenFromDrawer,
      navigateToFavoriteRelationsScreen = screen.navigateToFavoriteRelationsFromDrawer,
    )
    reportComposable(
      sharedViewModel = sharedViewModel,
      onBackArrowClick = screen.navigateToPreviousScreen,
    )
    favoritesComposable(
      favoriteRelationsViewModel = favoriteRelationsViewModel,
      navigateToReportScreen = screen.navigateToReportScreen,
      navigateToSearchScreen = screen.navigateToSearchScreenFromDrawer,
      navigateToContactScreen = screen.navigateToContactScreenFromDrawer
    )
  }
}