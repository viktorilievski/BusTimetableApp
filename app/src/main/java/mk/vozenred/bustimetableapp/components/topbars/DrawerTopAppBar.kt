package mk.vozenred.bustimetableapp.components.topbars

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import mk.vozenred.bustimetableapp.R

@Composable
fun DrawerTopAppBar(
  title: String,
  onDrawerIconClick: () -> Unit,
) {
  TopAppBar(
    title = {
      Text(text = title)
    },
    navigationIcon = {
      IconButton(
        onClick = { onDrawerIconClick() }
      ) {
        Icon(
          imageVector = Icons.Filled.Menu,
          contentDescription = stringResource(R.string.drawer_icon)
        )
      }
    }
  )
}