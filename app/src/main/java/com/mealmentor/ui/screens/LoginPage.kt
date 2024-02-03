package com.mealmentor.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mealmentor.ui.screens.elements.EmailField
import com.mealmentor.ui.screens.elements.PasswordField

// LoginPage це функція, яка містить розмітку сторінки входу в додаток
@Composable
fun LoginPage() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Дві функції remember для збереження стану текстового поля
        val emailText = remember {
            mutableStateOf("")
        }

        val passwordText = remember {
            mutableStateOf("")
        }

        // Виклик функцій для відображення текстових полів
        EmailField(
            text = emailText.value,
            onValueChange = {
                emailText.value = it
            }
        )

        Spacer(modifier = Modifier.padding(10.dp))

        PasswordField(
            text = passwordText.value,
            onValueChange = {
                passwordText.value = it
            }
        )

    }

    // Відображення тексту "Meal Mentor"
    Text(
        text = "Meal Mentor",
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onPrimary,
        style = MaterialTheme.typography.titleLarge
    )
}