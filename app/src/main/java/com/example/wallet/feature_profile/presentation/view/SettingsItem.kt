package com.example.wallet.feature_profile.presentation.view

sealed class SettingsItem(val name: String) {

    class StringSetting(
        name: String,
        val label: String
    ) : SettingsItem(name)

    class BooleanSetting(
        name: String,
        val checked: Boolean,
        val onCheckChange: (Boolean) -> Unit
    ) : SettingsItem(name)

    class OptionPickerSetting(
        name: String,
        val label: String,
        val onClick: () -> Unit
    ) : SettingsItem(name)

}