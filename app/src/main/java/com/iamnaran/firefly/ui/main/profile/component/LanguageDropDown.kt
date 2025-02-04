package com.iamnaran.firefly.ui.main.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.iamnaran.firefly.utils.helper.Language

@Composable
fun LanguageDropDown(
    languagesList: List<Language>,
    selectedLanguage: String,
    onAppLanguageChanged: (String) -> Unit,
) {

    var isDropDownOpened by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(languagesList.first { it.code == selectedLanguage }) }


    Box(
        modifier = Modifier
            .padding(end = 16.dp)
            .wrapContentSize(Alignment.TopEnd)
    ) {
        Row(
            modifier = Modifier
                .height(24.dp)
                .clickable {
                    isDropDownOpened = !isDropDownOpened
                }
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LanguageItem(selectedItem.displayLanguage)
        }

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
                    onAppLanguageChanged(selectedItem.code)
                })

            }

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