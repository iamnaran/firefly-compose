package com.iamnaran.firefly.ui.main.profile.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme
import com.iamnaran.firefly.utils.helper.Language

@Composable
fun LanguageDropDown(
    languagesList: List<Language>,
    selectedLanguage: String,
    onAppLanguageChanged: (String) -> Unit,
) {

    var isDropDownExpanded by remember { mutableStateOf(false) }
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
                    isDropDownExpanded = !isDropDownExpanded
                }
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LanguageItem(selectedItem.displayLanguage)
        }

        DropdownMenu(expanded = isDropDownExpanded, onDismissRequest = {
            isDropDownExpanded = false
        }) {

            repeat(languagesList.size) {
                val item = languagesList[it]

                DropdownMenuItem(text = {
                    LanguageItem(languagesList[it].displayLanguage)

                }, onClick = {
                    selectedItem = item
                    isDropDownExpanded = !isDropDownExpanded
                    onAppLanguageChanged(selectedItem.code)
                })
            }
        }
    }

}

@Composable
fun LanguageItem(selectedLanguage: String) {
    Text(
        modifier = Modifier,
        textAlign = TextAlign.Center,
        text = selectedLanguage,
        color = MaterialTheme.colorScheme.onSecondaryContainer
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultLanguagePreview() {
    FireflyComposeTheme {
        LanguageItem("English")
    }
}