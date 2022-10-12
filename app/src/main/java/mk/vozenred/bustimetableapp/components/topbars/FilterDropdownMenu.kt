package mk.vozenred.bustimetableapp.components.topbars

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import mk.vozenred.bustimetableapp.R
import mk.vozenred.bustimetableapp.ui.theme.MEDIUM_PADDING

@Composable
fun FilterDropdownMenu(
    expanded: Boolean,
    onDismissRequested: () -> Unit,
    onMenuItemClicked: (String) -> Unit,
    onShowAllRelationsClicked: () -> Unit,
    companies: List<String>,
    selectedCompany: String
) {
    DropdownMenu(
        modifier = Modifier.fillMaxWidth(),
        expanded = expanded,
        onDismissRequest = { onDismissRequested() }
    ) {
        if (companies.size > 1) {
            DropdownMenuItem(onClick = { onShowAllRelationsClicked() }) {
                if (selectedCompany == stringResource(R.string.all_relations_text)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_end_point_relation),
                        contentDescription = "Selected option icon"
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_start_point_relation2),
                        contentDescription = "Selected option icon"
                    )
                }
                Text(
                    stringResource(R.string.all_relations_text),
                    modifier = Modifier.padding(start = MEDIUM_PADDING)
                )
            }
            Divider()
        }
        companies.forEach { companyName ->
            FilterDropdownMenuItem(
                companyName = companyName,
                onDropdownMenuItemClick = {
                    onMenuItemClicked(companyName)
                },
                selectedCompany = selectedCompany
            )
        }
    }
}

@Composable
fun FilterDropdownMenuItem(
    companyName: String,
    onDropdownMenuItemClick: () -> Unit,
    selectedCompany: String
) {
    DropdownMenuItem(onClick = { onDropdownMenuItemClick() }) {
        if (selectedCompany == companyName) {
            Image(
                painter = painterResource(id = R.drawable.ic_end_point_relation),
                contentDescription = "Selected option icon"
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.ic_start_point_relation2),
                contentDescription = "Selected option icon"
            )
        }
        Text(
            text = companyName,
            modifier = Modifier.padding(start = MEDIUM_PADDING)
        )
    }
}