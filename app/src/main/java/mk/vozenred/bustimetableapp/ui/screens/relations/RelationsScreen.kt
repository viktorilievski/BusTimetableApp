package mk.vozenred.bustimetableapp.ui.screens.relations

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
import kotlinx.coroutines.launch
import mk.vozenred.bustimetableapp.R
import mk.vozenred.bustimetableapp.components.topbars.DrawerContent
import mk.vozenred.bustimetableapp.components.topbars.FilterDropdownMenu
import mk.vozenred.bustimetableapp.components.topbars.RelationsTopAppBar
import mk.vozenred.bustimetableapp.data.model.Relation
import mk.vozenred.bustimetableapp.ui.screens.relations.composables.RelationListRowItem
import mk.vozenred.bustimetableapp.ui.theme.ICON_BUTTON_PADDING
import mk.vozenred.bustimetableapp.ui.theme.LARGEST_PADDING
import mk.vozenred.bustimetableapp.ui.theme.MEDIUM_PADDING
import mk.vozenred.bustimetableapp.ui.theme.TOP_APP_BAR_HEIGHT
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel
import mk.vozenred.bustimetableapp.util.Constants.LIVE_RELATION_PREFERENCE_KEY

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RelationsScreen(
  sharedViewModel: SharedViewModel,
  navigateToSearchScreen: () -> Unit,
  navigateToContactScreen: () -> Unit,
  navigateToReportScreen: (Int) -> Unit,
  navigateToFavoriteRelationsScreen: () -> Unit,
  navigateToSettingsScreen: () -> Unit
) {
  val relations by sharedViewModel.relations.collectAsState(initial = mutableListOf())
  val selectedFromRelation = sharedViewModel.startPointSelected.value
  val selectedToRelation = sharedViewModel.endPointSelected.value
  val companiesList = sharedViewModel.companiesForRelation.value
  val selectedCompany = sharedViewModel.selectedCompany.value
  val liveRelationState by sharedViewModel.liveRelation.collectAsState()

  var toggleLiveRelation by remember {
    mutableStateOf(liveRelationState)
  }

  var filterExpanded by remember {
    mutableStateOf(false)
  }
  val scaffoldState = rememberScaffoldState()
  val coroutineScope = rememberCoroutineScope()

  LaunchedEffect(Unit) {
    sharedViewModel.getRelations(null)
    sharedViewModel.getCompaniesForSelectedRelation()
  }

  Scaffold(
    scaffoldState = scaffoldState,
    modifier = Modifier
      .fillMaxSize(),
    topBar = {
      Row {
        if (companiesList.size == 1) {
          RelationsTopAppBar(
            title = "$selectedFromRelation - $selectedToRelation",
            filterButtonEnabled = false,
            onFilterClicked = {},
            onDrawerIconClick = {
              coroutineScope.launch {
                scaffoldState.drawerState.open()
              }
            }
          )
        } else {
          RelationsTopAppBar(
            title = "$selectedFromRelation - $selectedToRelation",
            filterButtonEnabled = true,
            onFilterClicked = {
              filterExpanded = true
            },
            onDrawerIconClick = {
              coroutineScope.launch {
                scaffoldState.drawerState.open()
              }
            }
          )
          FilterDropdownMenu(
            expanded = filterExpanded,
            onDismissRequested = { filterExpanded = false },
            onFilterIconClicked = { companyNameClicked ->
              sharedViewModel.getRelations(companyNameClicked)
              filterExpanded = false
            },
            onShowAllRelationsClicked = {
              sharedViewModel.getRelations(null)
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
        },
        navigateToSettingsScreen = {
          navigateToSettingsScreen()
          coroutineScope.launch {
            scaffoldState.drawerState.close()
          }
        }
      )
    }
  ) {
    Column(modifier = Modifier.fillMaxSize()) {
      Column() {
        Row(modifier = Modifier
          .fillMaxWidth()
          .clickable {
            toggleLiveRelation = !toggleLiveRelation
            sharedViewModel.saveToDataStore(
              LIVE_RELATION_PREFERENCE_KEY,
              !toggleLiveRelation
            )
            sharedViewModel.getRelations(selectedCompany)
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
            Switch(checked = toggleLiveRelation, onCheckedChange = {
              toggleLiveRelation = !toggleLiveRelation
              sharedViewModel.saveToDataStore(LIVE_RELATION_PREFERENCE_KEY, !toggleLiveRelation)
              sharedViewModel.getRelations(selectedCompany)
            })
          }
        }
      }
      Divider()
      Column(modifier = Modifier.fillMaxHeight()) {
        LazyColumn()
        {
          items(items = relations, key = { item: Relation -> item.id }) { relation ->
            RelationListRowItem(
              relation = relation,
              onReportRelationClicked = { relationId ->
                navigateToReportScreen(relationId)
              },
              onFavoriteClicked = { relationId, isRelationFavorite ->
                sharedViewModel.setRelationFavoriteStatus(relationId, isRelationFavorite)
              }
            )
          }
        }
      }
    }

  }
}