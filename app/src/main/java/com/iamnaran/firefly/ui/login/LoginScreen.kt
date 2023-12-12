package com.iamnaran.firefly.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.iamnaran.firefly.R
import com.iamnaran.firefly.ui.login.components.FireflyInputType
import com.iamnaran.firefly.ui.login.components.FireflyTextInput
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme

@Composable
fun LoginScreen(navController: NavHostController?) {

    val passwordFocusRequester = FocusRequester()
    val focusManager: FocusManager = LocalFocusManager.current


    Column(
        Modifier
            .background(Color.White)
            .padding(30.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            painter = painterResource(id = R.drawable.ic_person_search_24),
            contentDescription = "logo",
            Modifier
                .size(150.dp)
        )

        FireflyTextInput(
            inputType = FireflyInputType.Name,
            keyboardActions = KeyboardActions(onNext = { passwordFocusRequester.requestFocus() })
        )

//        FireflyTextInput(
//            inputType = FireflyInputType.Password,
//            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
//            focusRequester = passwordFocusRequester
//        )

        Button(onClick = { }, Modifier.fillMaxWidth()) {
            Text(text = "Sign In", Modifier.padding(vertical = 8.dp))
        }

        Divider(
            color = Color.White.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.padding(50.dp)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Don't have an account?", color = Color.Black)
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Sign Up")
            }

        }

    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FireflyComposeTheme {
        LoginScreen(null)
    }
}

