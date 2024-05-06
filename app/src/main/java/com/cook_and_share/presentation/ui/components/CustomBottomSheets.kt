package com.cook_and_share.presentation.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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

            LazyRow {
                items(recipe.tags.size) {
                    CategoryItem(
                        modifier = Modifier.padding(start = 16.dp),
                        isClicked = true,
                        category = recipe.tags[it]
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "**There will be ingredients**",
                style = typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = translatedRecipe.value,
                style = typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(50.dp))
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