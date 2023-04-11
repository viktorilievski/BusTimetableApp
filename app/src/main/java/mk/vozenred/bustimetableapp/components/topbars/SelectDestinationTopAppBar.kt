package mk.vozenred.bustimetableapp.components.topbars

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import mk.vozenred.bustimetableapp.R
import mk.vozenred.bustimetableapp.components.topbars.utils.SortOption
import mk.vozenred.bustimetableapp.ui.screens.select_relation.composables.SortDropdownMenu

@Composable
fun SelectDestinationTopAppBar(
  title: String,
  onSearchIconClick: () -> Unit,
  onBackArrowClick: () -> Unit,
  onSortIconClick: () -> Unit,
  onDismissedSortDropdown: () -> Unit,
  sortDropDownMenuExpanded: Boolean,
  closeSortDropdownMenu: (SortOption) -> Unit
) {
  TopAppBar(
    title = {
      Text(text = title)
    },
    navigationIcon = {
      IconButton(onClick = {
        onBackArrowClick()
      }) {
        Icon(
          imageVector = Icons.Filled.ArrowBack,
          contentDescription = stringResource(R.string.back_arrow_icon)
        )
      }
    },
    actions = {
      IconButton(onClick = { onSearchIconClick() }) {
        Icon(
          imageVector = Icons.Filled.Search,
          contentDescription = stringResource(R.string.search_icon)
        )
      }
      Box {
        IconButton(onClick = { onSortIconClick() }) {
          Icon(
            painter = painterResource(id = R.drawable.ic_sort),
            contentDescription = stringResource(R.string.sort_icon)
          )
        }
        SortDropdownMenu(
          onDismissClicked = { onDismissedSortDropdown() },
          expanded = sortDropDownMenuExpanded,
          onOptionChosen = { sortOption ->
            closeSortDropdownMenu(sortOption)
          }
        )
      }
    }
  )
}

@Composable
@Preview
fun GeneralTopAppBarPreview() {
  SelectDestinationTopAppBar(
    title = "Select destination",
    onSearchIconClick = {},
    onBackArrowClick = {},
    onSortIconClick = {},
    onDismissedSortDropdown = {},
    sortDropDownMenuExpanded = false,
    closeSortDropdownMenu = {}
  )
}

@Composable
@Preview
fun SortDropDownPreview() {
  SortDropdownMenu(
    expanded = true,
    onDismissClicked = { },
    onOptionChosen = {}
  )
}

