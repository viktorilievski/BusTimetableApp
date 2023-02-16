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
import mk.vozenred.bustimetableapp.components.topbars.utils.PointType
import mk.vozenred.bustimetableapp.components.topbars.utils.SortOption
import mk.vozenred.bustimetableapp.ui.screens.select_relation.composables.PointListRowItemComposable
import mk.vozenred.bustimetableapp.ui.theme.accentColor
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel
import mk.vozenred.bustimetableapp.util.SearchAppBarState

@Composable
fun SelectFromDestinationScreen(
  sharedViewModel: SharedViewModel,
  navigateToSearchScreen: () -> Unit
) {
  val filteredStartPoints by sharedViewModel.filteredStartPoints
  val searchAppBarState by sharedViewModel.searchAppBarState
  var sortDropDownMenuExpanded by remember {
    mutableStateOf(false)
  }

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
            title = stringResource(R.string.top_app_bar_title_select_from_relation),
            onSearchIconClick = {
              sharedViewModel.topAppBarOnSearchClick()
            },
            onBackArrowClick = {
              navigateToSearchScreen()
            },
            onSortIconClick = {
              sortDropDownMenuExpanded = !sortDropDownMenuExpanded
            },
            onDismissedSortDropdown = {
              sortDropDownMenuExpanded = false
            },
            sortDropDownMenuExpanded = sortDropDownMenuExpanded,
            closeSortDropdownMenu = { sortOption ->
              sortDropDownMenuExpanded = false
              when (sortOption) {
                SortOption.ALPHABETICAL -> {
                  sharedViewModel.sortPoints(SortOption.ALPHABETICAL, PointType.START_POINT)
                }
                SortOption.ALPHABETICAL_INVERTED -> {
                  sharedViewModel.sortPoints(SortOption.ALPHABETICAL_INVERTED, PointType.START_POINT)
                }
                SortOption.MAX_RELATIONS -> {
                  sharedViewModel.sortPoints(SortOption.MAX_RELATIONS, PointType.START_POINT)
                }
                SortOption.MIN_RELATIONS -> {
                  sharedViewModel.sortPoints(SortOption.MIN_RELATIONS, PointType.START_POINT)
                }
              }
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
            navigateToSearchScreen()
          }
          Divider(modifier = Modifier.background(MaterialTheme.colors.accentColor))
        }
      }
    }
  )
}
