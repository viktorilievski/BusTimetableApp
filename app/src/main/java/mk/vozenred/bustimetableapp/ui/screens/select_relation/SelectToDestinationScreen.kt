package mk.vozenred.bustimetableapp.ui.screens.select_relation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import mk.vozenred.bustimetableapp.R
import mk.vozenred.bustimetableapp.components.topbars.SearchAppBar
import mk.vozenred.bustimetableapp.components.topbars.SelectDestinationTopAppBar
import mk.vozenred.bustimetableapp.ui.screens.select_relation.composables.PointListRowItemComposable
import mk.vozenred.bustimetableapp.ui.theme.accentColor
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel
import mk.vozenred.bustimetableapp.util.SearchAppBarState

@Composable
fun SelectToDestinationScreen(
  sharedViewModel: SharedViewModel,
  navigateToSearchScreen: (shouldClearBackstack: Boolean) -> Unit
) {

  val filteredEndPoints by sharedViewModel.filteredEndPoints
  val searchAppBarState by sharedViewModel.searchAppBarState
  var searchAppBarText by remember {
    mutableStateOf("")
  }

  LaunchedEffect(key1 = true) {
    sharedViewModel.getEndPointsForSelectedStartPoint()
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
              sharedViewModel.getEndPointsForEnteredText(it)
              searchAppBarText = it
            },
            onCloseClicked = {
              sharedViewModel.searchAppBarOnCloseClick()
              searchAppBarText = ""
            }
          )
        }
        SearchAppBarState.CLOSED -> {
          SelectDestinationTopAppBar(
            title = stringResource(R.string.top_app_bar_title_select_to_relation),
            onSearchIconClick = {
              sharedViewModel.topAppBarOnSearchClick()
            },
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
      LazyColumn(
        modifier = Modifier.padding(it)
      ) {
        items(filteredEndPoints) { endPoint ->
          PointListRowItemComposable(pointName = endPoint) {
            sharedViewModel.setEndPoint(endPoint)
            navigateToSearchScreen(true)
          }
          Divider(modifier = Modifier.background(MaterialTheme.colors.accentColor))
        }
      }
    },
    bottomBar = {}
  )
}