package mk.vozenred.bustimetableapp.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import mk.vozenred.bustimetableapp.ui.screens.select_relation.SelectToDestinationScreen
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel
import mk.vozenred.bustimetableapp.util.Constants.SELECT_TO_DESTINATION_SCREEN

fun NavGraphBuilder.selectToDestinationComposable(
    navigateToSearchScreen: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = SELECT_TO_DESTINATION_SCREEN
    ) {
        SelectToDestinationScreen(sharedViewModel = sharedViewModel, navigateToSearchScreen = navigateToSearchScreen)
    }
}