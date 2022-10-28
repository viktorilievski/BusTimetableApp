package mk.vozenred.bustimetableapp.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import mk.vozenred.bustimetableapp.ui.screens.relations.RelationsScreen
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel
import mk.vozenred.bustimetableapp.util.Constants.RELATIONS_SCREEN

fun NavGraphBuilder.relationsComposable(
    sharedViewModel: SharedViewModel,
    navigateToSearchScreen: () -> Unit,
    navigateToContactScreen: () -> Unit,
    navigateToReportScreen: () -> Unit
) {
    composable(
        route = RELATIONS_SCREEN
    ) {
        RelationsScreen(
            sharedViewModel = sharedViewModel,
            navigateToSearchScreen = navigateToSearchScreen,
            navigateToContactScreen = navigateToContactScreen,
            navigateToReportScreen = navigateToReportScreen
        )
    }
}