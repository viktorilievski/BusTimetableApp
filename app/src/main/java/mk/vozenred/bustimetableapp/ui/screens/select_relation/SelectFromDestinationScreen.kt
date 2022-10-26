package mk.vozenred.bustimetableapp.ui.screens.select_relation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import mk.vozenred.bustimetableapp.components.topbars.SearchAppBar
import mk.vozenred.bustimetableapp.components.topbars.SelectDestinationTopAppBar
import mk.vozenred.bustimetableapp.ui.screens.select_relation.composables.PointListRowItemComposable
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel
import mk.vozenred.bustimetableapp.util.SearchAppBarState

@Composable
fun SelectFromDestinationScreen(
    sharedViewModel: SharedViewModel,
    navigateToSearchScreen: (shouldClearBackstack: Boolean) -> Unit
) {
    val filteredStartPoints by sharedViewModel.filteredStartPoints
    val searchAppBarState by sharedViewModel.searchAppBarState

    var searchAppBarText by remember {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllStartPoints()
        sharedViewModel.closeSearchTopAppbar()
        sharedViewModel.clearSearchAppBarText()
    }
    Scaffold(
        topBar = {
            when (searchAppBarState) {
                SearchAppBarState.OPENED -> {
                    SearchAppBar(
                        text = searchAppBarText,
                        onTextChange = {
                            sharedViewModel.getStartPointsForEnteredText(it)
                            searchAppBarText = it
                        },
                        onCloseClicked = {
                            sharedViewModel.searchAppBarOnCloseClick()
                            searchAppBarText = ""
                            sharedViewModel.getEndPointsForSelectedStartPoint()
                        }
                    )
                }
                SearchAppBarState.CLOSED -> {
                    SelectDestinationTopAppBar(
                        title = "Start destination",
                        onSearchIconClick = { sharedViewModel.topAppBarOnSearchClick() },
                        onBackArrowClick = {
                            navigateToSearchScreen(true)
                        }
                    )
                }
            }

        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        content = {
            LazyColumn(modifier = Modifier.padding(it)) {
                items(filteredStartPoints) { startPoint ->
                    PointListRowItemComposable(pointName = startPoint) {
                        sharedViewModel.setStartPoint(startPoint)
                        sharedViewModel.clearEndPoint()
                        navigateToSearchScreen(true)
                    }
                    Divider()
                }
            }
        },
        bottomBar = {}
    )
}