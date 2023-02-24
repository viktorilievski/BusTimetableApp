package mk.vozenred.bustimetableapp.ui.screens.relations

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import mk.vozenred.bustimetableapp.R
import mk.vozenred.bustimetableapp.components.topbars.DrawerContent
import mk.vozenred.bustimetableapp.components.topbars.FilterDropdownMenu
import mk.vozenred.bustimetableapp.components.topbars.RelationsTopAppBar
import mk.vozenred.bustimetableapp.data.model.Relation
import mk.vozenred.bustimetableapp.ui.screens.relations.composables.RelationListRowItem
import mk.vozenred.bustimetableapp.ui.theme.BusTimetableAppTheme
import mk.vozenred.bustimetableapp.ui.theme.ICON_BUTTON_PADDING
import mk.vozenred.bustimetableapp.ui.theme.LARGEST_PADDING
import mk.vozenred.bustimetableapp.ui.theme.MEDIUM_PADDING
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel
import mk.vozenred.bustimetableapp.util.Constants.LIVE_RELATION_PREFERENCE_KEY


@Composable
fun RelationsScreen(
  sharedViewModel: SharedViewModel,
  navigateToSearchScreen: () -> Unit,
  navigateToContactScreen: () -> Unit,
  navigateToReportScreen: (Int) -> Unit,
  navigateToFavoriteRelationsScreen: () -> Unit
) {
  val liveRelationState by sharedViewModel.liveRelation.collectAsState()
  val relations by sharedViewModel.relations.collectAsState(initial = mutableListOf())
  val selectedFromRelation = sharedViewModel.startPointSelected.value
  val selectedToRelation = sharedViewModel.endPointSelected.value
  val companiesList = sharedViewModel.companiesForRelation.value
  val selectedCompany = sharedViewModel.selectedCompany.value

  var toggleLiveRelation by remember {
    mutableStateOf(liveRelationState)
  }

  var filterExpanded by remember {
    mutableStateOf(false)
  }
  val scaffoldState = rememberScaffoldState()
  val coroutineScope = rememberCoroutineScope()

  LaunchedEffect(key1 = toggleLiveRelation) {
    sharedViewModel.readLiveRelationDataStore()
  }

  LaunchedEffect(Unit) {
    sharedViewModel.getRelations(null, toggleLiveRelation)
    sharedViewModel.getCompaniesForSelectedRelation()
  }

  Scaffold(
    scaffoldState = scaffoldState,
    modifier = Modifier
      .fillMaxSize(),
    topBar = {
      Row {
        val isFilterEnabled = companiesList.size != 1
        RelationsTopAppBar(
          title = "$selectedFromRelation - $selectedToRelation",
          filterButtonEnabled = isFilterEnabled,
          onFilterClicked = {
            filterExpanded = true
          },
          onDrawerIconClick = {
            coroutineScope.launch {
              scaffoldState.drawerState.open()
            }
          }
        )
        if (isFilterEnabled) {
          FilterDropdownMenu(
            expanded = filterExpanded,
            onDismissRequested = { filterExpanded = false },
            onFilterIconClicked = { companyNameClicked ->
              sharedViewModel.getRelations(companyNameClicked, toggleLiveRelation)
              filterExpanded = false
            },
            onShowAllRelationsClicked = {
              sharedViewModel.getRelations(null, toggleLiveRelation)
              filterExpanded = false
            },
            companies = companiesList,
            selectedCompany = selectedCompany
          )
        }
      }
    },
    drawerContent = {
      DrawerContent(
        title = "$selectedFromRelation - $selectedToRelation",
        onCloseDrawerClick = {
          coroutineScope.launch {
            scaffoldState.drawerState.close()
          }
        },
        navigateToSearchScreen = {
          coroutineScope.launch {
            scaffoldState.drawerState.close()
          }
          navigateToSearchScreen()
        },
        navigateToContactScreen = {
          coroutineScope.launch {
            scaffoldState.drawerState.close()
          }
          navigateToContactScreen()
        },
        navigateToFavoriteRelationsScreen = {
          navigateToFavoriteRelationsScreen()
          coroutineScope.launch {
            scaffoldState.drawerState.close()
          }
        }
      )
    }
  ) {
    RelationScreenContent(
      relations = relations,
      isLiveRelationToggled = toggleLiveRelation,
      onSwitchClicked = {
        sharedViewModel.saveToDataStore(LIVE_RELATION_PREFERENCE_KEY, !toggleLiveRelation)
        toggleLiveRelation = it
        if (selectedCompany != "Сите") {
          sharedViewModel.getRelations(selectedCompany, toggleLiveRelation)
        } else {
          sharedViewModel.getRelations(null, toggleLiveRelation)
        }
      },
      onReportRelationClicked = {
        navigateToReportScreen(it)
      },
      onFavoriteClicked = { relationId, favoriteStatus ->
        sharedViewModel.setRelationFavoriteStatus(relationId, favoriteStatus)
      }
    )
  }
}

@Composable
fun RelationScreenContent(
  relations: List<Relation>,
  isLiveRelationToggled: Boolean,
  onSwitchClicked: (Boolean) -> Unit,
  onReportRelationClicked: (Int) -> Unit,
  onFavoriteClicked: (Int, Boolean) -> Unit,
) {
  Column(modifier = Modifier.fillMaxSize()) {
    Column() {
      LiveRelationToggleRow(
        isLiveRelationToggled = isLiveRelationToggled,
        onSwitchClicked = onSwitchClicked
      )
    }
    Divider()
    Column(modifier = Modifier.fillMaxHeight()) {
      LazyColumn()
      {
        items(items = relations, key = { item: Relation -> item.id }) { relation ->
          RelationListRowItem(
            relation = relation,
            onReportRelationClicked = { relationId ->
              onReportRelationClicked(relationId)
            },
            onFavoriteClicked = { relationId, isRelationFavorite ->
              onFavoriteClicked(relationId, isRelationFavorite)
            }
          )
        }
      }
    }
  }
}

@Composable
fun LiveRelationToggleRow(
  isLiveRelationToggled: Boolean,
  onSwitchClicked: (Boolean) -> Unit
) {
  Row(modifier = Modifier
    .fillMaxWidth()
    .clickable {
      onSwitchClicked(!isLiveRelationToggled)
    }
    .padding(start = MEDIUM_PADDING),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Row(modifier = Modifier.weight(5f)) {
      Icon(
        modifier = Modifier.padding(horizontal = ICON_BUTTON_PADDING),
        imageVector = Icons.Filled.CheckCircle,
        contentDescription = stringResource(id = R.string.live_relation_toggle),
      )
      Text(
        modifier = Modifier
          .padding(start = LARGEST_PADDING)
          .align(Alignment.CenterVertically),
        text = stringResource(id = R.string.live_relation_toggle)
      )
    }
    Row(modifier = Modifier.weight(1f)) {
      Switch(checked = isLiveRelationToggled, onCheckedChange = { newValue ->
        onSwitchClicked(newValue)
      })
    }
  }
}

@Composable
@Preview
fun LiveRelationToggleRowPreview() {
  var toggle by remember {
    mutableStateOf(false)
  }
  LiveRelationToggleRow(isLiveRelationToggled = toggle, onSwitchClicked = {
    toggle = it
  })
}

@Composable
@Preview
fun RelationScreenContentPreview() {
  BusTimetableAppTheme {
    RelationScreenContent(
      relations = listOf(
        Relation(
          id = 0,
          companyName = "test",
          startPoint = "test",
          endPoint = "test",
          departureTime = "test",
          arrivalTime = "test",
          note = "test",
          isRelationFavorite = true
        )
      ),
      isLiveRelationToggled = true,
      onSwitchClicked = {},
      onReportRelationClicked = {},
      onFavoriteClicked = { _, _ -> }
    )
  }
}
