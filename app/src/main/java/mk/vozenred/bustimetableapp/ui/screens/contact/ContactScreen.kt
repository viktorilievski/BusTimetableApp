package mk.vozenred.bustimetableapp.ui.screens.contact

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import mk.vozenred.bustimetableapp.R
import mk.vozenred.bustimetableapp.components.topbars.DrawerContent
import mk.vozenred.bustimetableapp.components.topbars.GeneralTopAppBar
import mk.vozenred.bustimetableapp.ui.theme.BusTimetableAppTheme
import mk.vozenred.bustimetableapp.ui.theme.LARGEST_PADDING
import mk.vozenred.bustimetableapp.ui.theme.MEDIUM_PADDING
import mk.vozenred.bustimetableapp.ui.theme.POINT_ROW_ITEM_HEIGHT


@Composable
fun ContactScreen(
  navigateToReportScreen: () -> Unit,
  navigateToSearchScreen: () -> Unit
) {
  val scaffoldState = rememberScaffoldState()
  val coroutineScope = rememberCoroutineScope()

  Scaffold(
    modifier = Modifier
      .fillMaxSize(),
    scaffoldState = scaffoldState,
    topBar = {
      GeneralTopAppBar(
        title = stringResource(R.string.contact_screen_title),
        onDrawerIconClick = {
          coroutineScope.launch {
            scaffoldState.drawerState.open()
          }
        })
    },
    drawerContent = {
      DrawerContent(
        title = stringResource(id = R.string.general_top_app_bar_title),
        onCloseDrawerClick = {
          coroutineScope.launch {
            scaffoldState.drawerState.close()
          }
        },
        navigateToSearchScreen = {
          navigateToSearchScreen()
          coroutineScope.launch {
            scaffoldState.drawerState.close()
          }
        },
        navigateToContactScreen = {
          coroutineScope.launch {
            scaffoldState.drawerState.close()
          }
        },
        navigateToReportScreen = {
          navigateToReportScreen()
          coroutineScope.launch {
            scaffoldState.drawerState.close()
          }
        }
      )
    }
  ) { paddingValue ->
    ContactScreenContent(paddingValue)
  }
}

@Composable
fun ContactScreenContent(paddingValue: PaddingValues) {
  val context = LocalContext.current as Activity

  Column(
    modifier = Modifier
      .padding(paddingValue)
      .fillMaxHeight(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Text(
      text = stringResource(R.string.contact_info),
      textAlign = TextAlign.Center,
      modifier = Modifier.padding(bottom = LARGEST_PADDING)
    )
    ContactScreenRowButton(
      icon = Icons.Outlined.Email,
      iconBackgroundColor = Color.Cyan,
      title = "Испрати e-mail",
      onRowItemClick = {
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.type = "message/rfc822"
        context.startActivity(Intent.createChooser(emailIntent, "Choose an Email client: "))
      }
    )
    ContactScreenRowButton(
      icon = Icons.Outlined.Call,
      iconBackgroundColor = Color.Black,
      title = "Јави се",
      onRowItemClick = {
        val uri = Uri.parse("tel:070510928")
        val intent = Intent(Intent.ACTION_DIAL, uri)
        context.startActivity(intent, null)
      }
    )
  }
}

@Composable
fun ContactScreenRowButton(
  icon: ImageVector,
  iconBackgroundColor: Color,
  title: String,
  onRowItemClick: () -> Unit
) {
  Row(modifier = Modifier
    .fillMaxWidth()
    .height(POINT_ROW_ITEM_HEIGHT)
    .padding(
      horizontal = LARGEST_PADDING,
      vertical = MEDIUM_PADDING
    )
    .clip(RoundedCornerShape(10.dp))
    .clickable {
      onRowItemClick()
    }
    .border(width = 2.dp, color = iconBackgroundColor, shape = RoundedCornerShape(10.dp)),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Surface(
      modifier = Modifier
        .height(POINT_ROW_ITEM_HEIGHT)
        .background(color = iconBackgroundColor)
        .weight(1f),
      color = iconBackgroundColor
    ) {
      Icon(
        imageVector = icon,
        contentDescription = "Icon Button",
        tint = Color.White
      )
    }
    Text(
      text = title,
      modifier = Modifier.weight(4f),
      textAlign = TextAlign.Center
    )
  }
}

@Preview
@Composable
fun ContactScreenPreview() {
  BusTimetableAppTheme {
    ContactScreen(
      navigateToReportScreen = {},
      navigateToSearchScreen = {}
    )
  }
}
