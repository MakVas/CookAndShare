package com.cook_and_share.presentation.ui.screens.main.profile.profile

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cook_and_share.R
import com.cook_and_share.domain.model.Profile
import com.cook_and_share.domain.model.Recipe
import com.cook_and_share.presentation.ui.components.ProfileItem
import com.cook_and_share.presentation.ui.components.RecipeBottomSheet
import com.cook_and_share.presentation.ui.components.RecipeItem
import com.cook_and_share.presentation.ui.components.SettingsBottomSheet
import com.cook_and_share.presentation.ui.components.TopAppBarAction
import com.cook_and_share.presentation.ui.components.TopBar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    isTranslation: Boolean,
    navigate: (String) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val profile = viewModel.profile
    val recipes = viewModel.recipes.collectAsState(emptyList())
    val recipe = remember { mutableStateOf(Recipe()) }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val sheetState = rememberModalBottomSheetState()
    val isRecipeSheetExpanded = rememberSaveable { mutableStateOf(false) }
    val isSettingsSheetExpanded = rememberSaveable { mutableStateOf(false) }

    val localLanguage = Locale.getDefault().language

    RecipeBottomSheet(
        isTranslation = isTranslation,
        localLanguage = localLanguage,
        identifyLanguage = viewModel::identifyLanguage,
        translateText = viewModel::translateText,
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
        onProfileClick = {
            viewModel.onProfileClick(navigate)
        },
        recipe = recipe,
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
    onProfileClick: () -> Unit,
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
                onProfileClick = onProfileClick,
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
    onProfileClick: () -> Unit,
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

            ProfileItem(
                onClick = {
                    onProfileClick()
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