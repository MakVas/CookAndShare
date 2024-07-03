package com.cook_and_share.presentation.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.cook_and_share.R

// Функція, що створює кастомне текстове поле для введення електронної пошти
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    fieldLabel: String,
    value: String,
    onValueChange: ((String) -> Unit),
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .border(
                width = 1.dp,
                color = colorScheme.tertiary,
                shape = RoundedCornerShape(16.dp)
            )
        .fillMaxWidth(),
    placeholder = {
        Text(fieldLabel)
    },
    colors = TextFieldDefaults.colors(
        focusedContainerColor = colorScheme.secondary,
        unfocusedContainerColor = colorScheme.secondary,
        focusedTextColor = colorScheme.onSecondary,
        unfocusedTextColor = colorScheme.onSecondary,
        cursorColor = colorScheme.tertiary,
        focusedPlaceholderColor = colorScheme.tertiary,
        unfocusedPlaceholderColor = colorScheme.tertiary,
        selectionColors = TextSelectionColors(
            handleColor = colorScheme.tertiary,
            backgroundColor = colorScheme.inverseOnSurface
        ),
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
    ),
    leadingIcon = {
        Icon(
            imageVector = icon,
            contentDescription = "icon",
            tint = colorScheme.tertiary
        )
    },
    trailingIcon = {
        if (value.isNotEmpty()) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "close",
                tint = colorScheme.tertiary,
                modifier = Modifier.clickable {
                    onValueChange("")
                }
            )

        }
    },
    singleLine = true,
    )
}

@Composable
fun EmailField(value: String, onNewValue: (String) -> Unit) {
    CustomTextField(
        modifier = Modifier
            .height(65.dp)
            .shadow(1.dp, RoundedCornerShape(16.dp), clip = true),
        fieldLabel = stringResource(id = R.string.email),
        value = value,
        icon = Icons.Default.Email,
        onValueChange = onNewValue
    )
}

// Функція, що створює кастомне текстове поле для введення паролю
@Composable
fun PasswordField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    var passwordVisibility by remember { mutableStateOf(false) }
    TextField(
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(65.dp)
            .shadow(1.dp, RoundedCornerShape(16.dp), clip = true),
        label = {
            Text(label)
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "password_img",
                tint = colorScheme.onPrimary
            )
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
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
            unfocusedTextColor = colorScheme.onSecondary,
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
    modifier: Modifier = Modifier,
    fieldLabel: String,
    text: String,
    onNewValue: (String) -> Unit,
    isNumber: Boolean = false,
    isSingleLine: Boolean = false
) {
    TextField(
        value = text,
        onValueChange = onNewValue,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier,
        label = {
            Text(fieldLabel)
        },
        colors = if (!isNumber) TextFieldDefaults.colors(
            focusedTextColor = colorScheme.onSecondary,
            unfocusedTextColor = colorScheme.onSecondary,
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
        ) else TextFieldDefaults.colors(
            focusedTextColor = colorScheme.onSecondary,
            unfocusedTextColor = colorScheme.onSecondary,
            focusedContainerColor = colorScheme.primary,
            unfocusedContainerColor = colorScheme.primary,
            disabledContainerColor = colorScheme.primary,
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
        keyboardOptions =
        if (!isNumber) KeyboardOptions(imeAction = ImeAction.Default)
        else KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = isSingleLine
    )
}