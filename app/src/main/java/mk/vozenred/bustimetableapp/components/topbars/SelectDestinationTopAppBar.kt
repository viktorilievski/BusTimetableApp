package mk.vozenred.bustimetableapp.components.topbars

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mk.vozenred.bustimetableapp.R
import mk.vozenred.bustimetableapp.components.topbars.utils.SortOption

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
fun SortDropdownMenu(
  expanded: Boolean,
  onDismissClicked: () -> Unit,
  onOptionChosen: (SortOption) -> Unit,
) {
  DropdownMenu(
    expanded = expanded,
    onDismissRequest = { onDismissClicked() },
    modifier = Modifier.fillMaxWidth(),
  ) {
    SortDropDownMenuItem(onItemClicked = onOptionChosen, sortOption = SortOption.ALPHABETICAL)
    SortDropDownMenuItem(
      onItemClicked = onOptionChosen,
      sortOption = SortOption.ALPHABETICAL_INVERTED
    )
    SortDropDownMenuItem(onItemClicked = onOptionChosen, sortOption = SortOption.MAX_RELATIONS)
    SortDropDownMenuItem(onItemClicked = onOptionChosen, sortOption = SortOption.MIN_RELATIONS)
  }
}

@Composable
fun SortDropDownMenuItem(
  onItemClicked: (SortOption) -> Unit,
  sortOption: SortOption
) {
  Column(modifier = Modifier.fillMaxWidth()) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clickable { onItemClicked(SortOption.valueOf(sortOption.name)) }
        .padding(15.dp)
    ) {
      Text(text = sortOption.description)
    }
  }
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

