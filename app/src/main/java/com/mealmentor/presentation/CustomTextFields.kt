package com.mealmentor.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.mealmentor.R

// Функція, що створює кастомне текстове поле для введення електронної пошти
@Composable
fun CustomTextField(
    icon: ImageVector,
    fieldLabel: String,
    text: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .shadow(1.dp, RoundedCornerShape(16.dp), clip = true),
        label = {
            Text(fieldLabel)
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "email_img",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        },
        trailingIcon = {
            if (text.isNotEmpty()) {
                Icon(
                    painter = painterResource(R.drawable.close),
                    contentDescription = "close",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.clickable {
                        onValueChange("")
                    }
                )

            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.secondary,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.onSecondary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = MaterialTheme.colorScheme.onSecondary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSecondary,
            selectionColors = TextSelectionColors(
                handleColor = MaterialTheme.colorScheme.onSecondary,
                backgroundColor = MaterialTheme.colorScheme.onSecondary
            )

        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true
    )
}

// Функція, що створює кастомне текстове поле для введення паролю
@Composable
fun PasswordField(
    fieldLabel: String,
    text: String,
    onValueChange: (String) -> Unit
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(
        value = text,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .shadow(1.dp, RoundedCornerShape(16.dp), clip = true),
        label = {
            Text(fieldLabel)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "password_img",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        },
        trailingIcon = {
            if (text.isNotEmpty()) {
                val visibility = if (passwordVisibility) {
                    painterResource(id = R.drawable.visibility)
                } else {
                    painterResource(id = R.drawable.visibility_off)
                }
                Icon(
                    painter = visibility,
                    contentDescription = if (passwordVisibility) "Show password" else "Hide password",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.clickable {
                        passwordVisibility = !passwordVisibility
                    }
                )
            }
        },
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.secondary,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.onSecondary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = MaterialTheme.colorScheme.onSecondary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSecondary,
            selectionColors = TextSelectionColors(
                handleColor = MaterialTheme.colorScheme.onSecondary,
                backgroundColor = MaterialTheme.colorScheme.onSecondary
            )
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        singleLine = true
    )
}