package com.cook_and_share.presentation.ui.screens.main.profile.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cook_and_share.R
import com.cook_and_share.domain.model.Profile
import com.cook_and_share.domain.model.Recipe
import com.cook_and_share.presentation.ui.components.RecipeBottomSheet
import com.cook_and_share.presentation.ui.components.RecipeItem
import com.cook_and_share.presentation.ui.components.SettingsBottomSheet
import com.cook_and_share.presentation.ui.components.TopAppBarAction
import com.cook_and_share.presentation.ui.components.TopBar
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navigate: (String) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val profile = viewModel.profile
    val recipes = viewModel.recipes.collectAsState(emptyList())

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val sheetState = rememberModalBottomSheetState()
    val isRecipeSheetExpanded = rememberSaveable { mutableStateOf(false) }
    val isSettingsSheetExpanded = rememberSaveable { mutableStateOf(false) }

    RecipeBottomSheet(
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
    profile: Profile,
    isRecipeLiked: (Recipe) -> Flow<Boolean>,
    scrollBehavior: TopAppBarScrollBehavior,
    isSettingsSheetExpanded: MutableState<Boolean>,
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
                profile = profile,
                isRecipeLiked = isRecipeLiked,
                isRecipeSheetExpanded = isRecipeSheetExpanded,
                recipes = recipes,
                onRecipeLikeClick = onRecipeLikeClick
            )
        }
    }
}

@Composable
private fun NestedScrolling(
    profile: Profile,
    isRecipeLiked: (Recipe) -> Flow<Boolean>,
    isRecipeSheetExpanded: MutableState<Boolean>,
    recipes: List<Recipe>,
    onRecipeLikeClick: (Recipe) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))

            ProfileContent(
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
            isSheetExpanded = isRecipeSheetExpanded,
            Modifier.padding(horizontal = 16.dp)
        )
    }
}

private fun LazyListScope.subColumn(
    isRecipeLiked: (Recipe) -> Flow<Boolean>,
    onRecipeLikeClick: (Recipe) -> Unit,
    recipes: List<Recipe>,
    isSheetExpanded: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    items(recipes, key = { it.id }) { recipeItem ->
        val isLiked = isRecipeLiked(recipeItem).collectAsState(initial = false)
        RecipeItem(
            isLiked = isLiked.value,
            onRecipeLikeClick = onRecipeLikeClick,
            recipe = recipeItem,
            onClick = {
                isSheetExpanded.value = true
            },
            modifier = modifier,
            isPreview = true
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun ProfileContent(
    modifier: Modifier,
    profile: Profile
) {
    Box(
        modifier = modifier
            .shadow(1.dp, RoundedCornerShape(16.dp), clip = true)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                //TODO: Implement profile image click
            }
            .background(colorScheme.secondary),
    ) {
        Image(
            modifier = Modifier
                .padding(16.dp)
                .size(120.dp)
                .clip(RoundedCornerShape(8.dp)),
            painter = painterResource(id = R.drawable.profile_default),
            contentDescription = "profile image",
        )
        Column(
            modifier = Modifier.padding(top = 16.dp, start = 156.dp),
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontStyle = typography.titleMedium.fontStyle,
                            color = colorScheme.tertiary,
                            fontWeight = typography.titleMedium.fontWeight
                        )
                    ) {
                        append(stringResource(id = R.string.username) + ": ")
                    }
                    append(profile.username + "\n")
                    withStyle(
                        SpanStyle(
                            fontStyle = typography.titleMedium.fontStyle,
                            color = colorScheme.tertiary,
                            fontWeight = typography.titleMedium.fontWeight
                        )
                    ) {
                        append(stringResource(id = R.string.email) + ": ")
                    }
                    append(profile.email + "\n")
                    withStyle(
                        SpanStyle(
                            fontStyle = typography.titleMedium.fontStyle,
                            color = colorScheme.tertiary,
                            fontWeight = typography.titleMedium.fontWeight
                        )
                    ) {
                        append(stringResource(id = R.string.followers) + ": ")
                    }
                    append(profile.followers.toString() + "\n")
                    withStyle(
                        SpanStyle(
                            fontStyle = typography.titleMedium.fontStyle,
                            color = colorScheme.tertiary,
                            fontWeight = typography.titleMedium.fontWeight
                        )
                    ) {
                        append(stringResource(id = R.string.following) + ": ")
                    }
                    append(profile.following.toString() + "\n")
                    withStyle(
                        SpanStyle(
                            fontStyle = typography.titleMedium.fontStyle,
                            color = colorScheme.tertiary,
                            fontWeight = typography.titleMedium.fontWeight
                        )
                    ) {
                        append(stringResource(id = R.string.recipes) + ": ")
                    }
                    append(profile.recipes.toString() + "\n")
                }
            )
        }
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
            },
        )
    }
}