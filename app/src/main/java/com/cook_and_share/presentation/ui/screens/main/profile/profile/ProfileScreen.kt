package com.cook_and_share.presentation.ui.screens.main.profile.profile

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.cook_and_share.R
import com.cook_and_share.domain.model.Profile
import com.cook_and_share.domain.model.Recipe
import com.cook_and_share.presentation.ui.components.RecipeBottomSheet
import com.cook_and_share.presentation.ui.components.RecipeItem
import com.cook_and_share.presentation.ui.components.SettingsBottomSheet
import com.cook_and_share.presentation.ui.components.TopAppBarAction
import com.cook_and_share.presentation.ui.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navigate: (String) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val profile = viewModel.profile
    val recipes = viewModel.recipes.collectAsState(emptyList())
    val recipe = remember { mutableStateOf(Recipe()) }

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        if (it != null) {
            viewModel.setImage(it)
        }
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val sheetState = rememberModalBottomSheetState()
    val isRecipeSheetExpanded = rememberSaveable { mutableStateOf(false) }
    val isSettingsSheetExpanded = rememberSaveable { mutableStateOf(false) }

    RecipeBottomSheet(
        recipe = recipe.value,
        sheetState = sheetState,
        isSheetExpanded = isRecipeSheetExpanded
    )

    SettingsBottomSheet(
        sheetState = sheetState,
        isSheetExpanded = isSettingsSheetExpanded,
        onSettingsClick = {
            isSettingsSheetExpanded.value = false
            viewModel.onSettingsClick(navigate)
        },
        onLikedClick = {
            isSettingsSheetExpanded.value = false
            viewModel.onLikedClick(navigate)
        },
        onInfoClick = {
            isSettingsSheetExpanded.value = false
            viewModel.onInfoClick(navigate)
        }
    )

    ProfileScreenContent(
        recipe = recipe,
        singlePhotoPicker = singlePhotoPicker,
        profile = profile.value,
        isRecipeLiked = viewModel::isRecipeLiked,
        scrollBehavior = scrollBehavior,
        recipes = recipes.value,
        onRecipeLikeClick = viewModel::onRecipeLikeClick,
        isSettingsSheetExpanded = isSettingsSheetExpanded,
        isRecipeSheetExpanded = isRecipeSheetExpanded
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileScreenContent(
    singlePhotoPicker: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
    profile: Profile,
    isRecipeLiked: (Recipe) -> Boolean,
    scrollBehavior: TopAppBarScrollBehavior,
    isSettingsSheetExpanded: MutableState<Boolean>,
    recipe: MutableState<Recipe>,
    isRecipeSheetExpanded: MutableState<Boolean>,
    recipes: List<Recipe>,
    onRecipeLikeClick: (Recipe) -> Unit
) {
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                text = R.string.profile,
                scrollBehavior = scrollBehavior,
                actions = {
                    TopAppBarAction(icon = Icons.Default.Menu) {
                        isSettingsSheetExpanded.value = true
                    }
                }
            )
        }
    ) { values ->
        Box(
            modifier = Modifier
                .padding(values)
                .fillMaxSize()
                .background(colorScheme.background)
        ) {
            NestedScrolling(
                singlePhotoPicker = singlePhotoPicker,
                profile = profile,
                isRecipeLiked = isRecipeLiked,
                isRecipeSheetExpanded = isRecipeSheetExpanded,
                recipes = recipes,
                recipe = recipe,
                onRecipeLikeClick = onRecipeLikeClick
            )
        }
    }
}

@Composable
private fun NestedScrolling(
    singlePhotoPicker: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
    profile: Profile,
    isRecipeLiked: (Recipe) -> Boolean,
    isRecipeSheetExpanded: MutableState<Boolean>,
    recipes: List<Recipe>,
    recipe: MutableState<Recipe>,
    onRecipeLikeClick: (Recipe) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))

            ProfileContent(
                onClick = {
                    singlePhotoPicker.launch(PickVisualMediaRequest())
                },
                modifier = Modifier
                    .height(220.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                profile = profile,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = R.string.your_recipes),
                style = typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        subColumn(
            isRecipeLiked = isRecipeLiked,
            onRecipeLikeClick = onRecipeLikeClick,
            recipes = recipes,
            recipe = recipe,
            isSheetExpanded = isRecipeSheetExpanded,
            Modifier.padding(horizontal = 16.dp)
        )
    }
}

private fun LazyListScope.subColumn(
    isRecipeLiked: (Recipe) -> Boolean,
    onRecipeLikeClick: (Recipe) -> Unit,
    recipes: List<Recipe>,
    recipe: MutableState<Recipe>,
    isSheetExpanded: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    items(recipes, key = { it.id }) { recipeItem ->
        val isLiked = isRecipeLiked(recipeItem)
        RecipeItem(
            image = recipeItem.imageUrl,
            isLiked = isLiked,
            onRecipeLikeClick = onRecipeLikeClick,
            recipe = recipeItem,
            onClick = {
                recipe.value = recipeItem
                isSheetExpanded.value = true
            },
            modifier = modifier,
            isPreview = false
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun ProfileContent(
    modifier: Modifier,
    profile: Profile,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .shadow(1.dp, RoundedCornerShape(16.dp), clip = true)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .background(colorScheme.secondary),
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.profile_default),
                contentDescription = "profile image",
                modifier = Modifier
                    .padding(16.dp)
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            AsyncImage(
                modifier = Modifier
                    .padding(16.dp)
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                model = profile.profileImage,
                contentScale = ContentScale.Crop,
                clipToBounds = true,
                contentDescription = "profile image",
            )
        }
        Text(
            buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontStyle = typography.titleMedium.fontStyle,
                        color = colorScheme.tertiary,
                        fontWeight = typography.titleMedium.fontWeight
                    )
                ) {
                    append(stringResource(id = R.string.username) + ":\n")
                }
                append(profile.username + "\n")
                withStyle(
                    SpanStyle(
                        fontStyle = typography.titleMedium.fontStyle,
                        color = colorScheme.tertiary,
                        fontWeight = typography.titleMedium.fontWeight
                    )
                ) {
                    append(stringResource(id = R.string.recipes) + ":\n")
                }
                append(profile.myRecipes.size.toString() + "\n")
            },
            Modifier.padding(top = 16.dp, start = 156.dp)
        )

        Text(
            modifier = Modifier
                .padding(top = 142.dp, start = 16.dp),
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontStyle = typography.titleMedium.fontStyle,
                        color = colorScheme.tertiary,
                        fontWeight = typography.titleMedium.fontWeight
                    )
                ) {
                    append(stringResource(id = R.string.bio) + ": ")
                }
                append(profile.bio)
            }
        )
    }
}