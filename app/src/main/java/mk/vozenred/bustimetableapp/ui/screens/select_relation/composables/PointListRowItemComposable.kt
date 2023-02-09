package mk.vozenred.bustimetableapp.ui.screens.select_relation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mk.vozenred.bustimetableapp.ui.theme.MEDIUM_PADDING
import mk.vozenred.bustimetableapp.ui.theme.POINT_ROW_ITEM_HEIGHT

@Composable
fun PointListRowItemComposable(
  pointName: String,
  navigateToSearchScreen: () -> Unit
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(POINT_ROW_ITEM_HEIGHT)
      .clickable {
        navigateToSearchScreen()
      },
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      modifier = Modifier.padding(start = MEDIUM_PADDING),
      text = pointName
    )
  }
}

@Composable
@Preview
fun PointListRowItemComposablePreview() {
  PointListRowItemComposable(pointName = "Skopje", navigateToSearchScreen = {})
}