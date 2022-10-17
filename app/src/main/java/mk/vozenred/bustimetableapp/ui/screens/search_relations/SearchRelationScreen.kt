package mk.vozenred.bustimetableapp.ui.screens.search_relations

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import mk.vozenred.bustimetableapp.R
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
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(R.string.search_relations_screen_topbar_title))
            })
        },
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