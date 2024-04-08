package com.cook_and_share.presentation.ui.screens.main.profile.profile

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.cook_and_share.R
import com.cook_and_share.domain.model.Recipe
import com.cook_and_share.presentation.ui.components.RecipeBottomSheet
import com.cook_and_share.presentation.ui.components.RecipeItem
import com.cook_and_share.presentation.ui.components.SettingsBottomSheet
import com.cook_and_share.presentation.ui.components.TopAppBarAction
import com.cook_and_share.presentation.ui.components.TopBar
import com.cook_and_share.presentation.util.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navController: NavHostController,
) {
    val recipes = viewModel.recipes.collectAsState(emptyList())
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val sheetState = rememberModalBottomSheetState()
    val isSheetExpanded = rememberSaveable { mutableStateOf(false) }

    RecipeBottomSheet(
        sheetState = sheetState,
        isSheetExpanded = isSheetExpanded
    )
    ProfileScreenContent(
        name = viewModel.userId,
        email = viewModel.userEmail,
        navController = navController,
        scrollBehavior = scrollBehavior,
        isSheetExpanded = isSheetExpanded,
        recipes = recipes.value,
        onRecipeLikeClick = viewModel::onRecipeLikeClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProfileScreenContent(
    name: String,
    email: String,
    navController: NavHostController,
    scrollBehavior: TopAppBarScrollBehavior,
    isSheetExpanded: MutableState<Boolean>,
    recipes: List<Recipe>,
    onRecipeLikeClick: (Recipe) -> Unit
){
    val sheetState = rememberModalBottomSheetState()

    SettingsBottomSheet(
        sheetState = sheetState,
        isSheetExpanded = isSheetExpanded,
        onSettingsClick = {
            isSheetExpanded.value = false
            navController.navigate(Screens.Settings.route) {
                popUpTo(Screens.Settings.route) {
                    inclusive = false
                }
            }
        },
        onLikedClick = {
            isSheetExpanded.value = false
            navController.navigate(Screens.Liked.route) {
                popUpTo(Screens.Liked.route) {
                    inclusive = false
                }
            }
        },
        onInfoClick = {
            isSheetExpanded.value = false
            navController.navigate(Screens.Info.route) {
                popUpTo(Screens.Info.route) {
                    inclusive = false
                }
            }
        }
    )

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                text = R.string.profile,
                scrollBehavior = scrollBehavior,
                actions = {
                    TopAppBarAction(icon = Icons.Default.Menu) {
                        isSheetExpanded.value = true
                    }
                }
            )
        }
    ) { values ->
        Box(
            modifier = Modifier
                .padding(values)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            NestedScrolling(
                name = name,
                email = email,
                isSheetExpanded = isSheetExpanded,
                recipes = recipes,
                onRecipeLikeClick = onRecipeLikeClick
            )
        }
    }
}

@Composable
private fun NestedScrolling(
    name: String,
    email: String,
    isSheetExpanded: MutableState<Boolean>,
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
                name = name,
                email = email
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = R.string.your_recipes),
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        subColumn(
            onRecipeLikeClick = onRecipeLikeClick,
            recipes = recipes,
            isSheetExpanded = isSheetExpanded,
            Modifier.padding(horizontal = 16.dp)
        )
    }
}

private fun LazyListScope.subColumn(
    onRecipeLikeClick: (Recipe) -> Unit,
    recipes: List<Recipe>,
    isSheetExpanded: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {
    items(recipes, key = { it.id }) {recipeItem ->
        RecipeItem(
            onRecipeLikeClick = onRecipeLikeClick,
            recipe = recipeItem,
            onClick = {
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
    name: String,
    email: String
) {
    ElevatedCard(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 16.dp)
            )
            Text(
                text = email,
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 8.dp)
            )
        }
    }
}