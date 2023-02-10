package mk.vozenred.bustimetableapp.ui.screens.search_relations

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch
import mk.vozenred.bustimetableapp.R
import mk.vozenred.bustimetableapp.components.topbars.DrawerContent
import mk.vozenred.bustimetableapp.components.topbars.TopAppBarWithDrawer
import mk.vozenred.bustimetableapp.ui.viewmodels.SharedViewModel

@Composable
fun SearchRelationsScreen(
  navigateToRelationsScreen: () -> Unit,
  navigateToStartDestinationScreen: () -> Unit,
  navigateToEndDestinationScreen: () -> Unit,
  navigateToContactScreen: () -> Unit,
  sharedViewModel: SharedViewModel
) {
  val startPoint: String by sharedViewModel.startPointSelected
  val endPoint: String by sharedViewModel.endPointSelected

  val scaffoldState = rememberScaffoldState()
  val coroutineScope = rememberCoroutineScope()

  Scaffold(
    scaffoldState = scaffoldState,
    topBar = {
      TopAppBarWithDrawer(
        title = stringResource(id = R.string.search_relations_screen_topbar_title),
        onDrawerIconClick = {
          coroutineScope.launch {
            scaffoldState.drawerState.open()
            Log.d("RelationsScreen", "Drawer Open")
          }
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
    drawerContent = {
      DrawerContent(
        title = stringResource(id = R.string.general_top_app_bar_title),
        onCloseDrawerClick = {
          coroutineScope.launch {
            scaffoldState.drawerState.close()
            Log.d("RelationsScreen", "Drawer Close")
          }
        },
        navigateToSearchScreen = {
          coroutineScope.launch {
            scaffoldState.drawerState.close()
            Log.d("RelationsScreen", "Drawer Close")
          }
        },
        navigateToContactScreen = {
          coroutineScope.launch {
            scaffoldState.drawerState.close()
            Log.d("RelationsScreen", "Drawer Close")
          }
          navigateToContactScreen()
        }
      )
    }
  )
}