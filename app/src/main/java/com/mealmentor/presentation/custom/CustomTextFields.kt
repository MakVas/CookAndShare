package com.mealmentor.presentation.custom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
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
    isShadow: Boolean,
    icon: ImageVector,
    fieldLabel: String,
    text: String,
    onValueChange: ((String) -> Unit)
) {
    val modifier = if (isShadow) {
        Modifier.fillMaxWidth()
            .height(65.dp)
            .shadow(1.dp, RoundedCornerShape(16.dp), clip = true)
    } else {
        Modifier.fillMaxWidth()
    }
    TextField(
        value = text,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier,
        label = {
            Text(fieldLabel)
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "email_img",
                tint = colorScheme.onPrimary
            )
        },
        trailingIcon = {
            if (text.isNotEmpty()) {
                Icon(
                    painter = painterResource(R.drawable.close),
                    contentDescription = "close",
                    tint = colorScheme.onPrimary,
                    modifier = Modifier.clickable {
                        onValueChange("")
                    }
                )

            }
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = colorScheme.onSecondary,
            unfocusedTextColor= colorScheme.onSecondary,
            focusedContainerColor = colorScheme.secondary,
            unfocusedContainerColor = colorScheme.secondary,
            disabledContainerColor = colorScheme.secondary,
            cursorColor = colorScheme.onSecondary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = colorScheme.onSecondary,
            unfocusedLabelColor = colorScheme.onSecondary,
            selectionColors = TextSelectionColors(
                handleColor = colorScheme.onSecondary,
                backgroundColor = colorScheme.onSecondary
            )

        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        singleLine = true,
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
                tint = colorScheme.onPrimary
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
                    tint = colorScheme.onPrimary,
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
            focusedTextColor = colorScheme.onSecondary,
            unfocusedTextColor= colorScheme.onSecondary,
            focusedContainerColor = colorScheme.secondary,
            unfocusedContainerColor = colorScheme.secondary,
            disabledContainerColor = colorScheme.secondary,
            cursorColor = colorScheme.onSecondary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = colorScheme.onSecondary,
            unfocusedLabelColor = colorScheme.onSecondary,
            selectionColors = TextSelectionColors(
                handleColor = colorScheme.onSecondary,
                backgroundColor = colorScheme.onSecondary
            )
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        singleLine = true
    )
}


@Composable
fun RecipeTextField(
    fieldLabel: String,
    text: String,
    onValueChange: ((String) -> Unit)
){
    TextField(
        value = text,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .shadow(1.dp, RoundedCornerShape(16.dp), clip = true),
        label = {
            Text(fieldLabel)
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = colorScheme.onSecondary,
            unfocusedTextColor= colorScheme.onSecondary,
            focusedContainerColor = colorScheme.secondary,
            unfocusedContainerColor = colorScheme.secondary,
            disabledContainerColor = colorScheme.secondary,
            cursorColor = colorScheme.onSecondary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = colorScheme.onSecondary,
            unfocusedLabelColor = colorScheme.onSecondary,
            selectionColors = TextSelectionColors(
                handleColor = colorScheme.onSecondary,
                backgroundColor = colorScheme.onSecondary
            )

        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Default)
    )
}