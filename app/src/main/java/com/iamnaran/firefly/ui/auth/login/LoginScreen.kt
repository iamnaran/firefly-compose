package com.iamnaran.firefly.ui.auth.login

import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.iamnaran.firefly.R
import com.iamnaran.firefly.ui.appcomponent.common.EmailInput
import com.iamnaran.firefly.ui.appcomponent.common.PasswordInput
import com.iamnaran.firefly.ui.appcomponent.snackbar.SnackEvent
import com.iamnaran.firefly.ui.appcomponent.snackbar.SnackbarManager
import com.iamnaran.firefly.ui.theme.AppIcons
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme
import com.iamnaran.firefly.ui.theme.dimens
import com.iamnaran.firefly.utils.AppLog
import com.iamnaran.firefly.utils.effects.AppCircularProgressBar
import com.iamnaran.firefly.utils.effects.ProgressType
import com.iamnaran.firefly.utils.effects.disableMutipleTouchEvents
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
) {
    val loginState by viewModel.loginState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    // Launcher for Google Sign-In
    val googleLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == RESULT_OK) {
                coroutineScope.launch {
                    viewModel.handleGoogleSignInResult(result.data)
                }
            } else {
                AppLog.showLog("Google Sign-In canceled or failed")
            }
        }
    )

    // Set launcher in ViewModel
    LaunchedEffect(Unit) {
        viewModel.setGoogleSignInLauncher(googleLauncher)
    }

    LaunchedEffect(loginState) {
        if (loginState.isLoginSuccessful) navigateToHome()

        val errorMsg = loginState.loginErrorState.serverErrorState.serverErrorMsg
        if (loginState.loginErrorState.serverErrorState.hasError && errorMsg.isNotEmpty()) {
            SnackbarManager.sendEvent(SnackEvent(errorMsg))
        }
    }

    LoginContent(
        email = loginState.email,
        password = loginState.password,
        onEmailChange = { viewModel.handleLoginUIEvent(LoginUIEvent.EmailChanged(it)) },
        onPasswordChange = { viewModel.handleLoginUIEvent(LoginUIEvent.PasswordChanged(it)) },
        onLoginClick = { viewModel.handleLoginUIEvent(LoginUIEvent.OnSubmit) },
        onGoogleLogin = { viewModel.signInWithGoogle() },
        onSignUpClick = navigateToSignUp,
        isLoginProgress = loginState.isLoading
    )
}

@Composable
fun LoginContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onGoogleLogin: () -> Unit,
    onSignUpClick: () -> Unit,
    isLoginProgress: Boolean
) {
    val passwordFocusRequester = remember { FocusRequester() }
    val focusManager: FocusManager = LocalFocusManager.current

    Column(
        Modifier
            .padding(MaterialTheme.dimens.extraLarge)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Logo
        Image(
            painter = painterResource(id = R.drawable.ic_app_logo),
            contentDescription = "logo",
            modifier = Modifier.padding(10.dp)
        )

        // Input fields
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EmailInput(
                currentValue = email,
                keyboardActions = KeyboardActions(onNext = { passwordFocusRequester.requestFocus() }),
                onValueChange = onEmailChange,
                icon = AppIcons.Email,
                label = stringResource(id = R.string.label_email),
            )

            PasswordInput(
                currentValue = password,
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                focusRequester = passwordFocusRequester,
                onValueChange = onPasswordChange,
                icon = AppIcons.Password,
                label = stringResource(id = R.string.label_password),
            )

            // Login button
            Button(
                onClick = onLoginClick,
                Modifier
                    .fillMaxWidth()
                    .disableMutipleTouchEvents()
            ) {
                if (isLoginProgress) {
                    AppCircularProgressBar(progressType = ProgressType.SMALL)
                } else {
                    Text(text = "Sign In", Modifier.padding(8.dp))
                }
            }

            // Google Sign-in button
            Button(
                onClick = onGoogleLogin,
                Modifier
                    .fillMaxWidth()
                    .disableMutipleTouchEvents()
            ) {
                Text(text = "Sign In with Google", Modifier.padding(8.dp))
            }
        }

        // Sign-up link
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Don't have an account?", color = Color.Black)
            TextButton(onClick = onSignUpClick) {
                Text(text = "Sign Up")
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FireflyComposeTheme {

    }
}

