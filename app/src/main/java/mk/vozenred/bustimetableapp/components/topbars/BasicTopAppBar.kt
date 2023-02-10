package mk.vozenred.bustimetableapp.components.topbars

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import mk.vozenred.bustimetableapp.R

@Composable
fun BasicTopAppBar(
  title: String,
  onBackArrowClick: () -> Unit,
) {
  TopAppBar(
    title = {
      Text(text = title)
    },
    navigationIcon = {
      IconButton(
        onClick = { onBackArrowClick() }
      ) {
        Icon(
          imageVector = Icons.Filled.ArrowBack,
          contentDescription = stringResource(R.string.back_arrow_icon)
        )
      }
    }
  )
}