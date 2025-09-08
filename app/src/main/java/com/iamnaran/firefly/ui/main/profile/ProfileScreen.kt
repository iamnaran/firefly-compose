package com.iamnaran.firefly.ui.main.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iamnaran.firefly.R
import com.iamnaran.firefly.data.local.entities.UserEntity
import com.iamnaran.firefly.ui.main.interest.components.dialogs.SimpleAlertDialog
import com.iamnaran.firefly.ui.main.profile.component.LanguageDropDown
import com.iamnaran.firefly.ui.main.profile.component.ProfileCard
import com.iamnaran.firefly.ui.theme.dimens
import com.iamnaran.firefly.utils.extension.collectAsStateLifecycleAware
import com.iamnaran.firefly.utils.helper.AppLocaleManager
import com.iamnaran.firefly.utils.helper.appLanguages

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
) {
    val context = LocalContext.current
    val appLocaleManager by remember {
        lazy {
            AppLocaleManager()
        }
    }
    val selectedLanguageCode = remember { appLocaleManager.getLanguageCode(context) }
    var selectedLanguage by remember { mutableStateOf(selectedLanguageCode) }

    val onAppLanguageChanged: (String) -> Unit = { newLanguage ->
        selectedLanguage = newLanguage
        appLocaleManager.changeLanguage(context, newLanguage)
    }

    val openAlertDialog = remember { mutableStateOf(false) }

    val userState by profileViewModel.profileState.collectAsStateLifecycleAware()
    val userEntity = userState.userEntityDetails

    userEntity?.let {
        ProfileContent(
            it, selectedLanguage, onAppLanguageChanged
        ) {
            openAlertDialog.value = true
        }
    }

    when {
        openAlertDialog.value -> {
            SimpleAlertDialog(
                dialogTitle = stringResource(id = R.string.prompt_logout_title),
                dialogSubTitle = stringResource(id = R.string.prompt_logout_description),
                onDismissRequest = {
                    openAlertDialog.value = false
                }) {
                openAlertDialog.value = false
                navigateToLogin()
                profileViewModel.doLogout()
            }
        }
    }
}

@Composable
fun ProfileContent(
    userEntity: UserEntity,
    selectedLanguage: String,
    onAppLanguageChanged: (String) -> Unit,
    onLogoutClick: () -> Unit
) {

    Column(
        Modifier
            .padding(MaterialTheme.dimens.large)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ProfileCard(userEntity)

        Button(
            onClick = {
                onLogoutClick()
            }, Modifier
                .padding(MaterialTheme.dimens.regular)
        ) {
            Text(text = "Logout")
        }
        Column  {
            Text(
                modifier = Modifier
                    .padding(4.dp),
                text = stringResource(R.string.app_language),
                color = MaterialTheme.colorScheme.primary,
            )

            LanguageDropDown(
                languagesList = appLanguages,
                selectedLanguage = selectedLanguage,
                onAppLanguageChanged
            )
        }
    }
}

