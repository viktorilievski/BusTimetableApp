package mk.vozenred.bustimetableapp.components.topbars

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import mk.vozenred.bustimetableapp.R

@Composable
fun RelationsTopAppBar(
    title: String,
    onFilterClicked: () -> Unit,
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
                        contentDescription = "Filter Icon",
                        tint = Color.White
                    )
                }
            }
        }
    )
}



@Composable
@Preview
fun RelationsTopAppBarPreview() {
    RelationsTopAppBar(title = "Скопје - Куманово", onFilterClicked = {}, filterButtonEnabled = false)
}