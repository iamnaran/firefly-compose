package com.iamnaran.firefly.ui.main.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.iamnaran.firefly.R
import com.iamnaran.firefly.ui.main.settings.components.LanguageRow
import com.iamnaran.firefly.ui.navigation.topappbar.ChildAppTopBar
import com.iamnaran.firefly.utils.extension.collectAsStateLifecycleAware
import com.iamnaran.firefly.utils.helper.appLanguages

@Composable
fun SettingScreen(
    settingViewModel: SettingViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    val settingState by settingViewModel.settingState.collectAsStateLifecycleAware()
    val onAppLanguageChanged: (String) -> Unit = { newLanguage ->
        settingViewModel.changeLanguage(newLanguage)
    }
    SettingContent(
        selectedLanguage = settingState.selectedLanguage,
        onAppLanguageChanged
    ) {
        navigateBack()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingContent(
    selectedLanguage: String,
    onAppLanguageChanged: (String) -> Unit,
    onNavigateBack: () -> Unit
) {
    val barScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        ChildAppTopBar(
            stringResource(R.string.settings),
            barScrollBehavior
        ) {
            onNavigateBack()
        }
    }) { paddingValues ->

        Box(modifier = Modifier.padding(paddingValues)) {
            LazyColumn {
                items(appLanguages.size) { index ->
                    LanguageRow(appLanguages[index], appLanguages[index].code == selectedLanguage) {
                        onAppLanguageChanged(it.code)
                    }
                }
            }
        }

    }
}

