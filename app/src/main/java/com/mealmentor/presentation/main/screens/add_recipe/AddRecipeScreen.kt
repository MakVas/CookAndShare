package com.mealmentor.presentation.main.screens.add_recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mealmentor.R
import com.mealmentor.presentation.custom.CustomTextField
import com.mealmentor.presentation.custom.RecipeItem
import com.mealmentor.ui.theme.ButtonText

@Composable
fun AddRecipeScreen() {
    var emailText by remember { mutableStateOf("") }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ){
        Spacer(modifier = Modifier.padding(top = 16.dp))

        RecipeItem(text = "Попередній перегляд")

        Spacer(modifier = Modifier.padding(top = 16.dp))

        Box()

        Spacer(modifier = Modifier.padding(top = 16.dp))

        ElevatedButton(
            onClick = {

            },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = colorScheme.secondary,
                contentColor = colorScheme.onSecondary
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 1.dp,
                pressedElevation = 0.dp
            ),
            modifier = Modifier
                .height(65.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.login),
                style = ButtonText
            )
        }

        Spacer(modifier = Modifier.padding(top = 16.dp))

        CustomTextField(
            icon = Icons.Default.Email,
            fieldLabel = stringResource(id = R.string.email),
            text = emailText,
            onValueChange = { emailText = it }
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

        ElevatedButton(
            onClick = {

            },
            shape = ButtonDefaults.elevatedShape,
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = colorScheme.tertiary,
                contentColor = colorScheme.onTertiary
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 3.dp,
                pressedElevation = 0.dp
            ),
            modifier = Modifier
                .height(65.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.login),
                style = ButtonText
            )
        }
    }
}

@Composable
fun Box(){
    var emailText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .background(colorScheme.secondary)
    ) {
        Column {
            CustomTextField(
                icon = Icons.Default.Email,
                fieldLabel = stringResource(id = R.string.email),
                text = emailText,
                onValueChange = { emailText = it }
            )

            Divider(
                modifier = Modifier.padding(horizontal = 16.dp),
                thickness = 1.dp,
                color = colorScheme.onSecondary
            )

            Button(
                onClick = {

                },
                shape = RectangleShape,
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = colorScheme.secondary,
                    contentColor = colorScheme.onSecondary
                ),
                modifier = Modifier
                    .height(65.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    style = ButtonText
                )
            }
        }
    }
}
