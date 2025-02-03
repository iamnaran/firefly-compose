package com.iamnaran.firefly.ui.main.profile.component

import androidx.compose.foundation.background
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.iamnaran.firefly.utils.helper.Language

@Composable
fun LanguageDropDown(
    languagesList: List<Language>,
    selectedLanguage: String,
    onSelectedLanguageChange: (String) -> Unit,
) {

    var isDropDownOpened by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(languagesList.first { it.code == selectedLanguage }) }


    DropdownMenu(expanded = isDropDownOpened, onDismissRequest = {
        isDropDownOpened = false
    }) {

        repeat(languagesList.size) {
            val item = languagesList[it]

            DropdownMenuItem(text = {
                LanguageItem(languagesList[it].displayLanguage)

            }, onClick = {
                selectedItem = item
                isDropDownOpened = !isDropDownOpened
                onSelectedLanguageChange(selectedItem.code)
            })

        }

    }

}

@Composable
fun LanguageItem(selectedLanguage: String) {

    Text(
        text = selectedLanguage,
        modifier = Modifier.background(Color.LightGray),
        color = Color.Black
    )

}