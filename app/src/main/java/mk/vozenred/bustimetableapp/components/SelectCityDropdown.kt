package mk.vozenred.bustimetableapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import kotlin.math.exp

@Composable
fun SelectCityDropdown(
    point: String,
    allPoints: List<String>,
    onPointSelected: (String) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var selectedPoint by remember { mutableStateOf("") }
    var textFieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    OutlinedTextField(
        value = selectedPoint,
        onValueChange = { selectedPoint = it },
        modifier = Modifier
            .width(150.dp)
            .onGloballyPositioned { coordinates ->
                // This value is used to assign to
                // the DropDown the same width
                textFieldSize = coordinates.size.toSize()
            }
            .clickable { expanded = !expanded },
        label = {Text("Select a city")},

        trailingIcon = {
            Icon(icon,"contentDescription",
                Modifier.clickable { expanded = !expanded })
        }
    )
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier
            .width(with(LocalDensity.current){textFieldSize.width.toDp()})
    ) {
        allPoints.forEach { label ->
            DropdownMenuItem(onClick = {
                selectedPoint = label
                expanded = false
            }) {
                Text(text = label)
            }
        }
    }
}

@Composable
@Preview
fun SelectCityDropdownPreview() {
    SelectCityDropdown(point = "", onPointSelected = {}, allPoints = listOf())
}