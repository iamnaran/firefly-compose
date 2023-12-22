package com.iamnaran.firefly.ui.login

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.compose.rememberNavController
import com.iamnaran.firefly.R
import com.iamnaran.firefly.ui.login.components.FireflyInputType
import com.iamnaran.firefly.ui.login.components.FireflyTextInput
import com.iamnaran.firefly.ui.theme.FireflyComposeTheme

@Composable
fun LoginScreen(navHostController: NavHostController) {

    val passwordFocusRequester = FocusRequester()
    val focusManager: FocusManager = LocalFocusManager.current


    Column(
        Modifier
            .background(Color.White)
            .padding(30.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .weight(2f)
                .padding(10.dp), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_google_logo),
                contentDescription = "logo",
                Modifier
                    .padding(10.dp)
            )
        }

        Box(
            modifier = Modifier.weight(3f),
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Column(verticalArrangement = Arrangement.Center) {
                FireflyTextInput(
                    inputType = FireflyInputType.Name,
                    keyboardActions = KeyboardActions(onNext = { passwordFocusRequester.requestFocus() })
                )

                Spacer(modifier = Modifier.height(20.dp))

                FireflyTextInput(
                    inputType = FireflyInputType.Password,
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    focusRequester = passwordFocusRequester
                )

                Spacer(modifier = Modifier.height(30.dp))

                Button(onClick = {

                }, Modifier.fillMaxWidth()) {
                    Text(text = "Sign In", Modifier.padding(vertical = 8.dp))
                }
            }
        }

        Box(
            modifier = Modifier.weight(0.5f)
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Don't have an account?", color = Color.Black)
                    TextButton(onClick = { /*TODO*/

                    }) {
                        Text(text = "Sign Up")
                    }

                }
            }

        }


    }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FireflyComposeTheme {
        LoginScreen(navHostController = rememberNavController())
    }
}

