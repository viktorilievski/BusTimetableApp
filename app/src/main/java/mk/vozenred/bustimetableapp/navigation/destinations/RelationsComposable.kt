package mk.vozenred.bustimetableapp.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import mk.vozenred.bustimetableapp.ui.screens.relations.RelationsScreen
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel
import mk.vozenred.bustimetableapp.util.Constants.RELATIONS_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.relationsComposable(
  sharedViewModel: SharedViewModel,
  navigateFromDrawerTo: (String) -> Unit,
  navigateToReportScreen: (Int) -> Unit,
) {
  composable(
    route = RELATIONS_SCREEN,
    popExitTransition = {
      slideOutHorizontally(targetOffsetX = { it })
    }
  ) {
    RelationsScreen(
      sharedViewModel = sharedViewModel,
      navigateFromDrawerTo = navigateFromDrawerTo,
      navigateToReportScreen = navigateToReportScreen,
    )
  }
}