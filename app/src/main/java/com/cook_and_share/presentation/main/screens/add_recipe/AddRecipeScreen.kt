package com.cook_and_share.presentation.main.screens.add_recipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.material3.Surface
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.cook_and_share.R
import com.cook_and_share.presentation.custom.CustomTextField
import com.cook_and_share.presentation.custom.DialogTextField
import com.cook_and_share.presentation.custom.RecipeItem
import com.cook_and_share.presentation.custom.RecipeTextField
import com.cook_and_share.ui.theme.ButtonText

@Composable
fun AddRecipeScreen() {
    val recipeName = remember { mutableStateOf("") }
    var cookingMethod by remember { mutableStateOf("") }
    val categories = remember { mutableStateOf("") }
    val openCategoriesDialog = remember { mutableStateOf(false) }

    if(openCategoriesDialog.value)
        CategoriesDialog(
            setShowDialog = { openCategoriesDialog.value = it },
            onValueChange = categories
        )

    Column(
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
            likes = "123",
            isPreview = true
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

        DoubleButton(recipeName, openCategoriesDialog)

        Spacer(modifier = Modifier.padding(top = 16.dp))

        ElevatedButton(
            onClick = {
                //
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
fun DoubleButton(recipeName: MutableState<String>, openDialog: MutableState<Boolean>){

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
                    openDialog.value = true
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

@Composable
fun CategoriesDialog(setShowDialog: (Boolean) -> Unit, onValueChange: MutableState<String>) {

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.categories),
                    style = typography.headlineSmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )

                Spacer(modifier = Modifier.padding(top = 16.dp))

                DialogTextField(
                    isShadow = true,
                    onClick = {},
                    fieldLabel = stringResource(id = R.string.your_categories),
                    text = onValueChange.value,
                    buttonText = stringResource(id = R.string.add),
                    onValueChange = { onValueChange.value = it }
                )

                Spacer(modifier = Modifier.padding(top = 16.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(5) {
                        Text(
                            text = "category",
                            style = typography.bodyMedium,
                        )
                    }
                }

                Spacer(modifier = Modifier.padding(top = 16.dp))

                Text(
                    text = stringResource(id = R.string.popular_categories),
                    style = typography.titleMedium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                )

                Spacer(modifier = Modifier.padding(top = 8.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(5) {
                        Button(
                            shape = RoundedCornerShape(8.dp),
                            onClick = {  },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorScheme.tertiary,
                                contentColor = colorScheme.primary
                            )
                        ) {
                            Text(
                                text = "category",
                                style = typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}