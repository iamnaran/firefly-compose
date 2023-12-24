package com.iamnaran.firefly.ui.auth.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.iamnaran.firefly.ui.theme.Shapes

sealed class FireflyInputType(
    val label: String,
    val icon: ImageVector,
    val keyboardOptions: KeyboardOptions,
    val visualTransformation: VisualTransformation
) {
    object Name : FireflyInputType(
        label = "Username or Email",
        icon = Icons.Default.Person,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        visualTransformation = VisualTransformation.None
    )

    object Password : FireflyInputType(
        label = "Password",
        icon = Icons.Default.Lock,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        visualTransformation = PasswordVisualTransformation()
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FireflyTextInput(
    currentValue: String,
    inputType: FireflyInputType,
    focusRequester: FocusRequester? = null,
    keyboardActions: KeyboardActions,
    onValueChange : (String) -> Unit
) {

    var passwordVisible by remember { mutableStateOf(false) }

    TextField(
        value = currentValue,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester ?: FocusRequester()),
        leadingIcon = { Icon(imageVector = inputType.icon, contentDescription = "Username Icon") },
        trailingIcon = {
            if (inputType == FireflyInputType.Password){
                val passwordIcon = if (passwordVisible) {
                    Icons.Filled.Lock
                } else {
                    Icons.Default.Lock
                }
                val description = if (passwordVisible){
                    "Hide Password"
                }else{
                    "Show Password"
                }
                IconButton(onClick = {passwordVisible = !passwordVisible}) {
                    Icon(imageVector = passwordIcon, contentDescription =description )
                }
            }
        },
        label = { Text(text = inputType.label) },
        shape = Shapes.medium,
        singleLine = true,
        keyboardActions = keyboardActions,
        visualTransformation = if (passwordVisible){
            VisualTransformation.None}else{inputType.visualTransformation},

        )


}