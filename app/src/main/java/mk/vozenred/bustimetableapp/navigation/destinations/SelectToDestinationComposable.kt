package mk.vozenred.bustimetableapp.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import mk.vozenred.bustimetableapp.ui.screens.select_relation.SelectToDestinationScreen
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel
import mk.vozenred.bustimetableapp.util.Constants.SELECT_TO_DESTINATION_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.selectToDestinationComposable(
  navigateToSearchScreen: () -> Unit,
  sharedViewModel: SharedViewModel
) {
  composable(
    route = SELECT_TO_DESTINATION_SCREEN,
    enterTransition = {
      slideInHorizontally(
        initialOffsetX = { it }
      )
    },
    popExitTransition = {
      slideOutHorizontally(
        targetOffsetX = { it }
      )
    }
  ) {
    SelectToDestinationScreen(
      sharedViewModel = sharedViewModel,
      navigateToSearchScreen = navigateToSearchScreen
    )
  }
}