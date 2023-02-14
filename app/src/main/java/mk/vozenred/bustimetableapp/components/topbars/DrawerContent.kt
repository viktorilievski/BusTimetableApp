package mk.vozenred.bustimetableapp.components.topbars

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import mk.vozenred.bustimetableapp.R
import mk.vozenred.bustimetableapp.ui.theme.*

@Composable
fun DrawerContent(
  title: String,
  onCloseDrawerClick: () -> Unit,
  navigateToSearchScreen: () -> Unit,
  navigateToContactScreen: () -> Unit,
  navigateToFavoriteRelationsScreen: () -> Unit,
) {

  Column(modifier = Modifier.fillMaxSize()) {
    DrawerHeader(
      title = title,
      onCloseDrawerClick = onCloseDrawerClick
    )
    Column(
      modifier = Modifier
        .fillMaxHeight()
        .background(MaterialTheme.colors.drawerBackgroundColor),
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      DrawerBody(
        navigateToSearchScreen = navigateToSearchScreen,
        navigateToContactScreen = navigateToContactScreen,
        navigateToFavoriteRelationsScreen = navigateToFavoriteRelationsScreen
      )
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
  navigateToSearchScreen: () -> Unit,
  navigateToContactScreen: () -> Unit,
  navigateToFavoriteRelationsScreen: () -> Unit
) {
  Column(
    modifier = Modifier
      .background(MaterialTheme.colors.drawerBackgroundColor)
  ) {
    DrawerNavigationItem(
      icon = Icons.Filled.Search,
      title = stringResource(R.string.search_new_relation),
      onItemClick = navigateToSearchScreen
    )
    DrawerNavigationItem(
      icon = Icons.Filled.Favorite,
      title = stringResource(R.string.favorite_relations_top_app_bar_title),
      onItemClick = navigateToFavoriteRelationsScreen
    )
    DrawerNavigationItem(
      icon = Icons.Filled.Call,
      title = stringResource(R.string.contact),
      onItemClick = navigateToContactScreen
    )
  }
}

@Composable
fun DrawerNavigationItem(
  icon: ImageVector,
  title: String,
  onItemClick: () -> Unit
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(TOP_APP_BAR_HEIGHT)
      .clickable { onItemClick() }
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
    navigateToSearchScreen = {},
    navigateToContactScreen = {},
    navigateToFavoriteRelationsScreen = {}
  )
}