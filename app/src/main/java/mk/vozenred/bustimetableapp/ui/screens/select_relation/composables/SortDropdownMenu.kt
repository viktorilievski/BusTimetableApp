package mk.vozenred.bustimetableapp.ui.screens.select_relation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mk.vozenred.bustimetableapp.components.topbars.utils.SortOption


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
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .clickable { onItemClicked(SortOption.valueOf(sortOption.name)) }
      .padding(15.dp)
  ) {
    Text(text = sortOption.description)
  }
  Divider()
}