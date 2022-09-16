package mk.vozenred.bustimetableapp.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import mk.vozenred.bustimetableapp.ui.screens.search_relations.SearchRelationsScreen
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel
import mk.vozenred.bustimetableapp.util.Constants.SEARCH_SCREEN

fun NavGraphBuilder.searchComposable(
    navigateToListRelations: () -> Unit,
    navigateToStartDestination: () -> Unit,
    navigateToEndDestination: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = SEARCH_SCREEN
    ) {
        SearchRelationsScreen(
            navigateToRelationsScreen = navigateToListRelations,
            navigateToStartDestinationScreen = navigateToStartDestination,
            navigateToEndDestinationScreen = navigateToEndDestination,
            sharedViewModel = sharedViewModel
        )
    }
}