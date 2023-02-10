package mk.vozenred.bustimetableapp.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.composable
import mk.vozenred.bustimetableapp.ui.screens.report.ReportScreen
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel
import mk.vozenred.bustimetableapp.util.Constants.RELATION_ARGUMENT_KEY
import mk.vozenred.bustimetableapp.util.Constants.REPORT_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.reportComposable(
  sharedViewModel: SharedViewModel,
  navigateToRelationsScreen: () -> Unit
) {
  composable(
    route = REPORT_SCREEN,
    arguments = listOf(navArgument(RELATION_ARGUMENT_KEY) {
      type = NavType.IntType
    })
  ) { navBackStackEntry ->
    val relationId = navBackStackEntry.arguments!!.getInt(RELATION_ARGUMENT_KEY)

    LaunchedEffect(key1 = relationId) {
      sharedViewModel.getSelectedRelation(relationId)
    }

    ReportScreen(
      sharedViewModel = sharedViewModel,
      navigateToRelationScreen = navigateToRelationsScreen
    )
  }
}