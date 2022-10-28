package mk.vozenred.bustimetableapp.components.topbars

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mk.vozenred.bustimetableapp.R
import mk.vozenred.bustimetableapp.components.SelectRelationField
import mk.vozenred.bustimetableapp.ui.theme.*

@Composable
fun DrawerContent(
    selectedFromRelation: String,
    selectedToRelation: String,
    onCloseDrawerClick: () -> Unit,
    navigateToSearchScreen: () -> Unit,
    navigateToContactScreen: () -> Unit,
    navigateToReportScreen: () -> Unit
) {
    Column() {
        DrawerHeader(
            title = "$selectedFromRelation - $selectedToRelation",
            onCloseDrawerClick = onCloseDrawerClick
        )
        DrawerBody(
            navigateToSearchScreen = navigateToSearchScreen,
            navigateToContactScreen = navigateToContactScreen,
            navigateToReportScreen = navigateToReportScreen
        )
    }
}

@Composable
fun DrawerHeader(
    title: String,
    onCloseDrawerClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT)
            .background(MaterialTheme.colors.topAppBarBackgroundColor)
            .padding(horizontal = MEDIUM_PADDING),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onCloseDrawerClick() }) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "",
                tint = MaterialTheme.colors.topAppBarContentColor
            )
        }
        Text(
            modifier = Modifier
                .padding(start = LARGEST_PADDING),
            text = title,
            color = MaterialTheme.colors.topAppBarContentColor,
            style = MaterialTheme.typography.h6

        )
    }
}


@Composable
fun DrawerBody(
    navigateToSearchScreen: () -> Unit,
    navigateToContactScreen: () -> Unit,
    navigateToReportScreen: () -> Unit
) {
    Column() {
        DrawerNavigationItem(
            icon = Icons.Filled.Search,
            title = stringResource(R.string.search_new_relation),
            onItemClick = navigateToSearchScreen
        )
        DrawerNavigationItem(
            icon = Icons.Filled.Call,
            title = stringResource(R.string.contact),
            onItemClick = navigateToContactScreen
        )
        DrawerNavigationItem(
            icon = Icons.Filled.Warning,
            title = stringResource(R.string.send_a_report),
            onItemClick = navigateToReportScreen
        )
    }
}

@Composable
fun DrawerNavigationItem(
    icon: ImageVector,
    title: String,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable { onItemClick() }
            .padding(start = LARGEST_PADDING),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = MaterialTheme.colors.drawerIconsColor
        )
        Text(
            modifier = Modifier
                .padding(start = LARGEST_PADDING),
            text = title
        )
    }
    Divider()
}

@Composable
@Preview
fun DrawerContentPreview() {
    DrawerContent(
        selectedFromRelation = "Скопје",
        selectedToRelation = "Битола",
        onCloseDrawerClick = {},
        navigateToSearchScreen = {},
        navigateToContactScreen = {},
        navigateToReportScreen = {}
    )
}