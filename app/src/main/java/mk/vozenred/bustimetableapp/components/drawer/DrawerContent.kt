package mk.vozenred.bustimetableapp.components.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import mk.vozenred.bustimetableapp.R
import mk.vozenred.bustimetableapp.ui.theme.*
import mk.vozenred.bustimetableapp.util.Constants.CONTACT_SCREEN
import mk.vozenred.bustimetableapp.util.Constants.FAVORITES_SCREEN
import mk.vozenred.bustimetableapp.util.Constants.INFO_SCREEN
import mk.vozenred.bustimetableapp.util.Constants.SEARCH_SCREEN

@Composable
fun DrawerContent(
  title: String,
  onCloseDrawerClick: () -> Unit,
  navigateFromDrawerTo: (String) -> Unit
) {
  Column(modifier = Modifier.fillMaxSize()) {
    DrawerHeader(
      title = title,
      onCloseDrawerClick = onCloseDrawerClick
    )
    Column(
      modifier = Modifier
        .background(MaterialTheme.colors.drawerBackgroundColor)
        .fillMaxSize(),
      verticalArrangement = Arrangement.SpaceBetween,
    ) {
      Column {
        DrawerBody(
          navigateFromDrawerTo = { navigateFromDrawerTo(it) },
          onCloseDrawerClick = onCloseDrawerClick,
        )
      }
    }
  }
}

@Composable
fun DrawerHeader(
  title: String,
  onCloseDrawerClick: () -> Unit,
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(TOP_APP_BAR_HEIGHT)
      .background(MaterialTheme.colors.topAppBarBackgroundColor)
      .padding(horizontal = MEDIUM_PADDING),
    verticalAlignment = Alignment.CenterVertically
  ) {
    IconButton(onClick = { onCloseDrawerClick() }) {
      Icon(
        imageVector = Icons.Filled.Close,
        contentDescription = "",
        tint = MaterialTheme.colors.topAppBarContentColor
      )
    }
    Text(
      modifier = Modifier
        .padding(start = LARGEST_PADDING),
      text = title,
      color = MaterialTheme.colors.topAppBarContentColor,
      style = MaterialTheme.typography.h6
    )
  }
}


@Composable
fun DrawerBody(
  navigateFromDrawerTo: (String) -> Unit,
  onCloseDrawerClick: () -> Unit
) {
  val drawerItems: List<DrawerBodyItem> = listOf(
    DrawerBodyItem(
      icon = Icons.Filled.Search,
      title = stringResource(id = R.string.search_new_relation),
      onItemClick = { navigateFromDrawerTo(SEARCH_SCREEN) }
    ),
    DrawerBodyItem(
      icon = Icons.Filled.Favorite,
      title = stringResource(id = R.string.favorite_relations_top_app_bar_title),
      onItemClick = { navigateFromDrawerTo(FAVORITES_SCREEN) }
    ),
    DrawerBodyItem(
      icon = Icons.Filled.Call,
      title = stringResource(id = R.string.contact),
      onItemClick = { navigateFromDrawerTo(CONTACT_SCREEN) }
    ),
    DrawerBodyItem(
      icon = Icons.Outlined.Info,
      title = stringResource(id = R.string.info),
      onItemClick = { navigateFromDrawerTo(INFO_SCREEN) }
    )
  )
  Column(
    modifier = Modifier
      .background(MaterialTheme.colors.drawerBackgroundColor)
  ) {
    drawerItems.forEach { drawerItem ->
      DrawerNavigationItem(
        icon = drawerItem.icon,
        title = drawerItem.title,
        onItemClick = drawerItem.onItemClick,
        onCloseDrawerClick = onCloseDrawerClick,
      )
    }
  }
}

@Composable
fun DrawerNavigationItem(
  icon: ImageVector,
  title: String,
  onItemClick: () -> Unit,
  onCloseDrawerClick: () -> Unit
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(TOP_APP_BAR_HEIGHT)
      .clickable {
        onCloseDrawerClick()
        onItemClick()
      }
      .padding(start = MEDIUM_PADDING),
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Icon(
      modifier = Modifier.padding(horizontal = ICON_BUTTON_PADDING),
      imageVector = icon,
      contentDescription = title,
    )
    Text(
      modifier = Modifier
        .padding(start = LARGEST_PADDING),
      text = title
    )
  }
  Divider()
}

@Composable
@Preview
fun DrawerContentPreview() {
  DrawerContent(
    title = "Title",
    onCloseDrawerClick = {},
    navigateFromDrawerTo = {}
  )
}