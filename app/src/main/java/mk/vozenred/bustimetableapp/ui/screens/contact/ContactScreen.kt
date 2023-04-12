package mk.vozenred.bustimetableapp.ui.screens.contact

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.rememberScaffoldState
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
import mk.vozenred.bustimetableapp.components.drawer.DrawerContent
import mk.vozenred.bustimetableapp.components.topbars.DrawerTopAppBar
import mk.vozenred.bustimetableapp.ui.theme.BusTimetableAppTheme
import mk.vozenred.bustimetableapp.ui.theme.LARGEST_PADDING
import mk.vozenred.bustimetableapp.ui.theme.MEDIUM_PADDING
import mk.vozenred.bustimetableapp.ui.theme.POINT_ROW_ITEM_HEIGHT
import mk.vozenred.bustimetableapp.util.Constants.CONTACT_NUMBER

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ContactScreen(
  navigateFromDrawerTo: (String) -> Unit
) {
  val context = LocalContext.current as Activity

  val scaffoldState = rememberScaffoldState()
  val coroutineScope = rememberCoroutineScope()

  Scaffold(
    modifier = Modifier
      .fillMaxSize(),
    scaffoldState = scaffoldState,
    topBar = {
      DrawerTopAppBar(
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
        navigateFromDrawerTo = { navigateFromDrawerTo(it) }
      )
    }
  ) {
    ContactScreenContent(
      onSendEmailClick = {
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.type = "message/rfc822"
        context.startActivity(Intent.createChooser(emailIntent, "Choose an Email client: "))
      },
      onCallButtonClick = {
        val uri = Uri.parse(CONTACT_NUMBER)
        val intent = Intent(Intent.ACTION_DIAL, uri)
        context.startActivity(intent, null)
      }
    )
  }
}

@Composable
fun ContactScreenContent(
  onSendEmailClick: () -> Unit,
  onCallButtonClick: () -> Unit
) {
  Column(
    modifier = Modifier
      .padding(LARGEST_PADDING)
      .fillMaxSize()
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .weight(2f),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Text(
        text = stringResource(R.string.contact_info),
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(bottom = LARGEST_PADDING)
      )
    }
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .weight(1f),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      ContactButton(
        icon = Icons.Filled.Email,
        iconBackgroundColor = Color.Red,
        title = stringResource(R.string.send_email_button_text),
        onRowItemClick = {
          onSendEmailClick()
        }
      )
      ContactButton(
        icon = Icons.Outlined.Call,
        iconBackgroundColor = Color.Green,
        title = stringResource(R.string.call_button_text),
        onRowItemClick = {
          onCallButtonClick()
        }
      )
    }
  }
}

@Composable
fun ContactButton(
  icon: ImageVector,
  iconBackgroundColor: Color,
  title: String,
  onRowItemClick: () -> Unit
) {
  Row(modifier = Modifier
    .fillMaxWidth()
    .height(POINT_ROW_ITEM_HEIGHT)
    .padding(
      vertical = MEDIUM_PADDING
    )
    .clip(RoundedCornerShape(10.dp))
    .clickable {
      onRowItemClick()
    }
    .border(
      width = 2.dp,
      color = iconBackgroundColor,
      shape = RoundedCornerShape(10.dp)
    ),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Row(
      modifier = Modifier
        .weight(1f)
        .background(iconBackgroundColor)
        .height(POINT_ROW_ITEM_HEIGHT),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Icon(
        imageVector = icon,
        contentDescription = stringResource(R.string.icon_button_content_description),
        tint = Color.White,
        modifier = Modifier.size(35.dp)
      )
    }
    Row(
      modifier = Modifier.weight(5f),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = title,
        modifier = Modifier.weight(4f),
        textAlign = TextAlign.Center
      )
    }
  }
}

@Preview
@Composable
fun ContactScreenContentPreview() {
  BusTimetableAppTheme {
    ContactScreenContent(
      onSendEmailClick = {},
      onCallButtonClick = {},
    )
  }
}

@Preview
@Composable
fun ContactButtonPreview() {
  ContactButton(
    icon = Icons.Filled.Email,
    iconBackgroundColor = Color.Red,
    title = "Испрати e-mail",
    onRowItemClick = {}
  )
}
