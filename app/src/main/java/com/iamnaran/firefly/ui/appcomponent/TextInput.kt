package com.iamnaran.firefly.ui.appcomponent

import android.text.InputType
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import com.iamnaran.firefly.ui.auth.components.FireflyInputType
import com.iamnaran.firefly.ui.theme.Shapes

@Composable
fun TextInput(
    label: String,
    icon: ImageVector,
    currentValue: String,
    inputType: Int,
    focusRequester: FocusRequester? = null,
    keyboardActions: KeyboardActions,
    onValueChange: (String) -> Unit
) {

    var passwordVisible by remember { mutableStateOf(false) }

    TextField(
        value = currentValue,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester ?: FocusRequester()),
        leadingIcon = { Icon(imageVector = icon, contentDescription = "Username Icon") },
        trailingIcon = {
            if (inputType == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                val passwordIcon = if (passwordVisible) {
                    Icons.Filled.Lock
                } else {
                    Icons.Default.Lock
                }
                val description = if (passwordVisible) {
                    "Hide Password"
                } else {
                    "Show Password"
                }
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = passwordIcon, contentDescription = description)
                }
            }
        },
        label = { Text(text = label) },
        shape = Shapes.medium,
        singleLine = true,
        keyboardActions = keyboardActions,

        )


}