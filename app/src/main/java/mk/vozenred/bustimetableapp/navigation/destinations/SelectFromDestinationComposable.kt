package mk.vozenred.bustimetableapp.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import mk.vozenred.bustimetableapp.ui.screens.select_relation.SelectFromDestinationScreen
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel
import mk.vozenred.bustimetableapp.util.Constants.SELECT_FROM_DESTINATION_SCREEN

fun NavGraphBuilder.selectFromDestinationComposable(
  navigateToSearchScreen: (shouldClearBackstack: Boolean) -> Unit,
  sharedViewModel: SharedViewModel
) {
  composable(
    route = SELECT_FROM_DESTINATION_SCREEN
  ) {
    SelectFromDestinationScreen(
      sharedViewModel = sharedViewModel,
      navigateToSearchScreen = navigateToSearchScreen
    )
  }
}