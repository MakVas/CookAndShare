package com.mealmentor.presentation.main.screens.add_recipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mealmentor.R
import com.mealmentor.presentation.custom.CustomTextField
import com.mealmentor.presentation.custom.RecipeItem
import com.mealmentor.presentation.custom.RecipeTextField
import com.mealmentor.ui.theme.ButtonText

@Composable
fun AddRecipeScreen() {
    val recipeName = remember { mutableStateOf("") }
    var cookingMethod by remember { mutableStateOf("") }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ){
        Spacer(modifier = Modifier.padding(top = 16.dp))

        RecipeItem(
            onClick = {

            },
            name = "username",
            title = recipeName.value,
            likes = "123"
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

        Card(recipeName)

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
                .height(60.dp)
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 12.dp),
        ) {
            Box (
                modifier = Modifier.fillMaxSize(),
            ){
                Icon(
                    modifier = Modifier.align(Alignment.CenterStart),
                    imageVector = Icons.Default.Restaurant,
                    contentDescription = "ingredients"
                )
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 40.dp),
                    text = stringResource(id = R.string.ingredients),
                    style = typography.bodyLarge
                )
                Icon(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "ingredients"
                )
            }
        }

        Spacer(modifier = Modifier.padding(top = 16.dp))

        RecipeTextField(
            fieldLabel = stringResource(id = R.string.cooking_method),
            text = cookingMethod,
            onValueChange = { cookingMethod = it }
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
                text = stringResource(id = R.string.publish_recipe),
                style = ButtonText
            )
        }
        Spacer(modifier = Modifier.padding(top = 16.dp))
    }
}

@Composable
fun Card(recipeName: MutableState<String>){

    ElevatedCard(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(containerColor = colorScheme.secondary)
    ) {
        Column {
            CustomTextField(
                isShadow = false,
                icon = Icons.Default.DriveFileRenameOutline,
                fieldLabel = stringResource(id = R.string.recipe_name),
                text = recipeName.value,
                onValueChange = {
                    if (it.length <= 17) {
                        recipeName.value = it
                    } else {
                        recipeName.value = it.substring(0, 17)
                    }
                }
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
                contentPadding = PaddingValues(horizontal = 12.dp),
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth()
            ) {
                Box (
                    modifier = Modifier.fillMaxSize(),
                ){
                    Icon(
                        modifier = Modifier.align(Alignment.CenterStart),
                        imageVector = Icons.Default.Tag,
                        contentDescription = "categories"
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 40.dp),
                        text = stringResource(id = R.string.categories),
                        style = typography.bodyLarge
                    )
                    Icon(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "categories"
                    )
                }
            }
        }
    }
}