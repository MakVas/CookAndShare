package com.cook_and_share.presentation.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cook_and_share.R
import com.cook_and_share.domain.model.Recipe

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeBottomSheet(
    isTranslation: Boolean,
    localLanguage: String,
    identifyLanguage: (String) -> String,
    translateText: (String, String, String, (String) -> Unit) -> Unit,
    recipe: Recipe,
    sheetState: SheetState,
    isSheetExpanded: MutableState<Boolean>
) {

    val identifiedLanguage = identifyLanguage(recipe.title)
    val translatedTitle = remember { mutableStateOf("") }
    val translatedRecipe = remember { mutableStateOf("") }

    if (isTranslation) {
        translateText(recipe.title, identifiedLanguage, localLanguage) {
            translatedTitle.value = it
        }
        translateText(recipe.recipe, identifiedLanguage, localLanguage) {
            translatedRecipe.value = it
        }
    } else {
        translatedTitle.value = recipe.title
        translatedRecipe.value = recipe.recipe
    }

    if (isSheetExpanded.value) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                isSheetExpanded.value = false
                translatedTitle.value = ""
                translatedRecipe.value = ""
            }
        ) {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.no_image),
                    contentDescription = "placeholder image",
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )
                AsyncImage(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                        .height(250.dp),
                    model = recipe.imageUrl,
                    contentDescription = "recipe image",
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = recipe.author,
                style = typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = translatedTitle.value,
                style = typography.labelLarge,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {

                item {
                    LazyRow {
                        items(recipe.tags.size) {
                            CategoryItem(
                                modifier = Modifier.padding(start = 16.dp),
                                isClicked = true,
                                category = recipe.tags[it]
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }

                items(recipe.ingredients.size) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .shadow(1.dp, shape = RoundedCornerShape(16.dp), clip = true)
                            .height(45.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(color = MaterialTheme.colorScheme.secondary),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = recipe.ingredients[it].name,
                                style = typography.titleMedium,
                                modifier = Modifier.padding(start = 16.dp)
                            )

                            Text(
                                text = recipe.ingredients[it].quantity.toString() + " " + recipe.ingredients[it].unit,
                                style = typography.bodyMedium,
                                modifier = Modifier.padding(end = 16.dp)
                            )

                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(id = R.string.cooking_method),
                        style = typography.headlineSmall,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )


                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = translatedRecipe.value,
                        style = typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    Spacer(modifier = Modifier.height(50.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsBottomSheet(
    sheetState: SheetState,
    isSheetExpanded: MutableState<Boolean>,
    onSettingsClick: () -> Unit,
    onLikedClick: () -> Unit,
    onInfoClick: () -> Unit
) {
    if (isSheetExpanded.value) {
        ModalBottomSheet(sheetState = sheetState, onDismissRequest = {
            isSheetExpanded.value = false
        }) {
            TertiaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSettingsClick() }
                    .height(60.dp)
                    .padding(horizontal = 16.dp),
                label = R.string.settings,
                icon = Icons.Outlined.Settings,
                isArrow = true)

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 56.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSecondary
            )

            TertiaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onLikedClick() }
                    .height(60.dp)
                    .padding(horizontal = 16.dp),
                label = R.string.liked,
                icon = Icons.Default.FavoriteBorder,
                isArrow = true)

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 56.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSecondary
            )

            TertiaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onInfoClick() }
                    .height(60.dp)
                    .padding(horizontal = 16.dp),
                label = R.string.info,
                icon = Icons.Outlined.Info,
                isArrow = true)

            Spacer(modifier = Modifier.height(100.dp))

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun IngredientsBottomSheet(
    onValueChange: MutableState<String>,
    selectedIngredients: MutableState<List<String>>,
    ingredients: List<String>,
    sheetState: SheetState,
    isSheetExpanded: MutableState<Boolean>,
    onIngredientClick: (String, MutableState<List<String>>) -> Unit
) {
    if (isSheetExpanded.value) {
        ModalBottomSheet(sheetState = sheetState, onDismissRequest = {
            isSheetExpanded.value = false
        }) {
            CustomTextField(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                icon = Icons.Default.Search,
                fieldLabel = stringResource(id = R.string.search),
                value = onValueChange.value,
                onValueChange = { onValueChange.value = it }
            )

            LazyColumn {
                item {
                    FlowRow(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 8.dp)
                    ) {
                        repeat(ingredients.size) {
                            CategoryItem(
                                modifier = Modifier
                                    .padding(bottom = 8.dp, end = 8.dp),
                                isClicked = selectedIngredients.value.contains(ingredients[it]),
                                category = ingredients[it],
                                onClick = {
                                    onIngredientClick(ingredients[it], selectedIngredients)
                                    isSheetExpanded.value = false
                                }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(200.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FilterBottomSheet(
    onValueChange: MutableState<String>,
    onIngredientValueChange: MutableState<String>,
    selectedCategories: MutableState<List<String>>,
    selectedIngredients: MutableState<List<String>>,
    categories: List<String>,
    ingredients: List<String>,
    sheetState: SheetState,
    isSheetExpanded: MutableState<Boolean>,
    onIngredientClick: (String, MutableState<List<String>>) -> Unit
) {
    if (isSheetExpanded.value) {
        ModalBottomSheet(sheetState = sheetState, onDismissRequest = {
            isSheetExpanded.value = false
        }) {
            Text(
                text = stringResource(id = R.string.categories),
                style = typography.headlineSmall,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            CustomTextField(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                icon = Icons.Default.Search,
                fieldLabel = stringResource(id = R.string.search),
                value = onValueChange.value,
                onValueChange = { onValueChange.value = it }
            )

            LazyRow {
                item {
                    FlowRow(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 8.dp)
                    ) {
                        repeat(categories.size) {
                            CategoryItem(
                                modifier = Modifier
                                    .padding(bottom = 8.dp, end = 8.dp),
                                isClicked = selectedCategories.value.contains(categories[it]),
                                category = categories[it],
                                onClick = {
                                    onIngredientClick(categories[it], selectedCategories)
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.ingredients),
                style = typography.headlineSmall,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            CustomTextField(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
                icon = Icons.Default.Search,
                fieldLabel = stringResource(id = R.string.search),
                value = onIngredientValueChange.value,
                onValueChange = { onIngredientValueChange.value = it }
            )

            LazyRow {
                item {
                    FlowRow(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 8.dp)
                    ) {
                        repeat(ingredients.size) {
                            CategoryItem(
                                modifier = Modifier
                                    .padding(bottom = 8.dp, end = 8.dp),
                                isClicked = selectedIngredients.value.contains(ingredients[it]),
                                category = ingredients[it],
                                onClick = {
                                    onIngredientClick(ingredients[it], selectedIngredients)
                                }
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(70.dp))
        }
    }
}
