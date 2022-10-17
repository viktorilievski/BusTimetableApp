package mk.vozenred.bustimetableapp.components.topbars

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import mk.vozenred.bustimetableapp.R

@Composable
fun RelationsTopAppBar(
    title: String,
    onFilterClicked: () -> Unit,
    onDrawerIconClick: () -> Unit,
    filterButtonEnabled: Boolean
) {
    TopAppBar(
        title = {
            Text(
                text = title
            )
        },
        actions = {
            if (filterButtonEnabled) {
                IconButton(
                    onClick = { onFilterClicked() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_outline_filter),
                        contentDescription = stringResource(R.string.filter_icon),
                        tint = Color.White
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(
                onClick = { onDrawerIconClick() }
            ) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = stringResource(R.string.drawer_icon))
            }
        }
    )
}



@Composable
@Preview
fun RelationsTopAppBarPreview() {
    RelationsTopAppBar(
        title = "Скопје - Куманово",
        onFilterClicked = {},
        filterButtonEnabled = false,
        onDrawerIconClick = {}
    )
}