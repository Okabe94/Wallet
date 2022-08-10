package com.example.wallet.feature_profile.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wallet.core.presentation.component.AppBaseSurface

@Composable
fun SettingsList(title: String, items: List<SettingsItem>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 10.dp),
            text = title,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSurface
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(50.dp, 400.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            SettingsListItems(items)
        }
    }
}

@Composable
private fun SettingsListItems(items: List<SettingsItem>) = LazyColumn {
    itemsIndexed(items) { index, item ->
        when (item) {
            is SettingsItem.BooleanSetting -> SettingsBoolean(
                name = item.name,
                checked = item.checked,
                onCheckChange = item.onCheckChange
            )
            is SettingsItem.OptionPickerSetting -> SettingsOptionPicker(
                name = item.name,
                label = item.label,
                onClick = item.onClick
            )
            is SettingsItem.StringSetting -> SettingsString(
                name = item.name,
                label = item.label
            )
        }
        if (index != items.lastIndex) Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2F),
        )
    }
}

@Composable
fun SettingsBoolean(name: String, checked: Boolean, onCheckChange: (Boolean) -> Unit) =
    SettingsCard {
        Text(text = name)
        Switch(checked = checked, onCheckedChange = onCheckChange)
    }

@Composable
fun SettingsString(
    name: String,
    label: String,
    labelColor: Color = Color.Gray,
    onClick: () -> Unit = {}
) = SettingsCard(modifier = Modifier.clickable(onClick = onClick)) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = name)
        Text(text = label, style = MaterialTheme.typography.body2, color = labelColor)
    }
}

@Composable
fun SettingsOptionPicker(name: String, label: String, onClick: () -> Unit = {}) = SettingsString(
    name = name,
    label = label,
    labelColor = MaterialTheme.colors.onSurface,
    onClick = onClick
)

@Composable
private fun SettingsCard(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit = {}
) = Row(
    modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 6.dp, horizontal = 10.dp),
    content = content,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
)

@Composable
@Preview
private fun SettingsListPreview() {
    AppBaseSurface {
        SettingsList(
            title = "Prueba",
            items = listOf(
                SettingsItem.StringSetting("Prueba", "label"),
                SettingsItem.BooleanSetting("Boolean", false) {},
                SettingsItem.BooleanSetting("Boolean", true) {},
                SettingsItem.StringSetting("Prueba", "label"),
                SettingsItem.StringSetting("Prueba", "label"),
                SettingsItem.OptionPickerSetting("Prueba", "label") {}
            )
        )
    }
}

@Composable
@Preview
private fun SettingsCardPreview() = SettingsCard { Text(text = "Prueba") }

@Composable
@Preview
private fun SettingsBooleanPreview() = SettingsBoolean(
    name = "Prueba",
    checked = true,
    onCheckChange = {}
)

@Composable
@Preview
private fun SettingsStringPreview() = SettingsString(
    name = "Prueba",
    label = "Label de prueba"
)

@Composable
@Preview
private fun SettingsOptionPickerPreview() = SettingsOptionPicker(
    name = "Prueba",
    label = "Label de prueba",
    onClick = {}
)


