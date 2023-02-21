package mk.vozenred.bustimetableapp.ui.screens.settings

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import mk.vozenred.bustimetableapp.R
import mk.vozenred.bustimetableapp.components.topbars.BasicTopAppBar
import mk.vozenred.bustimetableapp.ui.theme.ICON_BUTTON_PADDING
import mk.vozenred.bustimetableapp.ui.theme.LARGEST_PADDING
import mk.vozenred.bustimetableapp.ui.theme.MEDIUM_PADDING
import mk.vozenred.bustimetableapp.ui.theme.TOP_APP_BAR_HEIGHT
import mk.vozenred.bustimetableapp.ui.viewmodels.SettingsScreenViewModel
import mk.vozenred.bustimetableapp.util.Constants.LIVE_RELATION_PREFERENCE_KEY


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
  settingsScreenViewModel: SettingsScreenViewModel,
  onBackArrowClick: () -> Unit,
) {

  val liveRelationToggle by settingsScreenViewModel.liveRelationToggle.collectAsState()
  var liveRelationToggleState by remember {
    mutableStateOf(liveRelationToggle)
  }
  Scaffold(
    topBar = {
      BasicTopAppBar(
        title = stringResource(id = R.string.settings),
        onBackArrowClick = { onBackArrowClick() }
      )
    },
    content = {
      SettingsScreenContent(
        liveRelationToggle = liveRelationToggleState,
        onLiveRelationToggleClick = {
          settingsScreenViewModel.saveToDataStore(LIVE_RELATION_PREFERENCE_KEY, !liveRelationToggleState)
          liveRelationToggleState = !liveRelationToggleState
          Log.d("SettingsScreen", "DataStore value: ${liveRelationToggleState}")
        }
      )
    }
  )
}

@Composable
fun SettingsScreenContent(
  liveRelationToggle: Boolean,
  onLiveRelationToggleClick: () -> Unit
) {
  Column(modifier = Modifier
    .fillMaxSize()
  ) {
    SettingsScreenRowItem(
      icon = Icons.Filled.CheckCircle,
      title = stringResource(id = R.string.live_relation_toggle),
      isEnabled = liveRelationToggle,
      onItemClick = onLiveRelationToggleClick
    )
  }
}

@Composable
fun SettingsScreenRowItem(
  icon: ImageVector,
  title: String,
  isEnabled: Boolean,
  onItemClick: () -> Unit
) {
  Row(modifier = Modifier
    .fillMaxWidth()
    .height(TOP_APP_BAR_HEIGHT)
    .clickable { onItemClick() }
    .padding(start = MEDIUM_PADDING),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Row(modifier = Modifier.weight(5f)) {
      Icon(
        modifier = Modifier.padding(horizontal = ICON_BUTTON_PADDING),
        imageVector = icon,
        contentDescription = title,
      )
      Text(
        modifier = Modifier
          .padding(start = LARGEST_PADDING)
          .align(Alignment.CenterVertically),
        text = title
      )
    }
    Row(modifier = Modifier.weight(1f)) {
      Switch(checked = isEnabled, onCheckedChange = { onItemClick() })
    }
  }
  Divider()
}

@Preview
@Composable
fun SettingsScreenContentPreview() {
  SettingsScreenContent(
    liveRelationToggle = true,
    onLiveRelationToggleClick = {}
  )
}

