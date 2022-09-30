package mk.vozenred.bustimetableapp.ui.screens.search_relations

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel

@Composable
fun SearchRelationsScreen(
    navigateToRelationsScreen: () -> Unit,
    navigateToStartDestinationScreen: () -> Unit,
    navigateToEndDestinationScreen: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    val startPoint: String by sharedViewModel.startPointSelected
    val endPoint: String by sharedViewModel.endPointSelected

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        topBar = {},
        content = { padding ->
            SearchRelationsScreenContent(
                paddingValue = padding,
                startPoint = startPoint,
                endPoint = endPoint,
                navigateToStartDestinationScreen = { navigateToStartDestinationScreen() },
                navigateToEndDestinationScreen = { navigateToEndDestinationScreen() },
                navigateToRelationsScreen = { navigateToRelationsScreen() }
            )
        },
        bottomBar = {}
    )
}