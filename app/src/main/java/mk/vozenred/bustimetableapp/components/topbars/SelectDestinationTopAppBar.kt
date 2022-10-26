package mk.vozenred.bustimetableapp.components.topbars

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import mk.vozenred.bustimetableapp.R

@Composable
fun SelectDestinationTopAppBar(
    title: String,
    onSearchIconClick: () -> Unit,
    onBackArrowClick: () -> Unit,
) {
    TopAppBar(
        title = {
                Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = {
                onBackArrowClick()
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(R.string.back_arrow_icon))
            }
        },
        actions = {
            IconButton(onClick = { onSearchIconClick() }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = stringResource(R.string.search_icon) )
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
        onBackArrowClick = {}
    )
}