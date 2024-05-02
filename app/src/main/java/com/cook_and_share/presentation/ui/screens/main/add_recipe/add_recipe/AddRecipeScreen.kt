package com.cook_and_share.presentation.ui.screens.main.add_recipe.add_recipe

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cook_and_share.R
import com.cook_and_share.domain.model.Recipe
import com.cook_and_share.presentation.ui.components.CustomTextField
import com.cook_and_share.presentation.ui.components.PrimaryButton
import com.cook_and_share.presentation.ui.components.RecipeItem
import com.cook_and_share.presentation.ui.components.RecipeTextField
import com.cook_and_share.presentation.ui.components.SecondaryButton
import com.cook_and_share.presentation.ui.components.TopBar

@Composable
fun AddRecipeScreen(
    viewModel: AddRecipeViewModel = hiltViewModel(),
    navigate: (String) -> Unit,
    popUp: () -> Unit
) {
    val recipe by viewModel.recipe

    AddRecipeScreenContent(
        recipe = recipe,
        onPublishClick = { viewModel.onPublishClick(popUp) },
        onTitleChange = viewModel::onTitleChange,
        onUrlChange = viewModel::onUrlChange,
        onTagsChange = viewModel::onTagsChange,
        onIngredientsChange = viewModel::onIngredientsChange,
        onRecipeChange = viewModel::onRecipeChange,
        onIngredientsClick = { viewModel.onIngredientsClick(navigate) },
        onCategoryClick = { viewModel.onCategoryClick(navigate) },
        onRecipeClick = { viewModel.onRecipeClick(navigate) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddRecipeScreenContent(
    recipe: Recipe,
    onPublishClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    onUrlChange: (String) -> Unit,
    onTagsChange: (List<String>) -> Unit,
    onIngredientsChange: (List<Map<String, Int>>) -> Unit,
    onRecipeChange: (String) -> Unit,
    onIngredientsClick: () -> Unit,
    onCategoryClick:() -> Unit,
    onRecipeClick: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                text = R.string.add,
                scrollBehavior = scrollBehavior,
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
                recipe = recipe,
                onPublishClick = onPublishClick,
                onTitleChange = onTitleChange,
                onUrlChange = onUrlChange,
                onTagsChange = onTagsChange,
                onIngredientsChange = onIngredientsChange,
                onRecipeChange = onRecipeChange,
                onIngredientsClick = onIngredientsClick,
                onCategoryClick = onCategoryClick,
                onRecipeClick = onRecipeClick
            )
        }
    }
}

@Composable
private fun NestedScrolling(
    recipe: Recipe,
    onPublishClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    onUrlChange: (String) -> Unit,
    onTagsChange: (List<String>) -> Unit,
    onIngredientsChange: (List<Map<String, Int>>) -> Unit,
    onIngredientsClick: () -> Unit,
    onCategoryClick:() -> Unit,
    onRecipeClick: () -> Unit,
    onRecipeChange: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

    ) {
        Spacer(modifier = Modifier.padding(top = 16.dp))

        RecipeItem(
            recipe = recipe,
            onClick = onRecipeClick,
            modifier = Modifier.padding(horizontal = 16.dp),
            isPreview = true
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

        DoubleButton(
            recipe = recipe,
            modifier = Modifier.padding(horizontal = 16.dp),
            onNewValue = onTitleChange,
            onClick = onCategoryClick
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

        PrimaryButton(
            modifier = Modifier
                .height(60.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            label = R.string.ingredients,
            icon = Icons.Default.Restaurant,
            onClick = {
                onIngredientsClick()
            }
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

        RecipeTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(300.dp),
            fieldLabel = stringResource(id = R.string.cooking_method),
            text = recipe.recipe,
            onNewValue = { onRecipeChange(it) }
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

        SecondaryButton(
            modifier = Modifier
                .height(65.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            label = R.string.publish_recipe,
            onClick = onPublishClick
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

    }
}

@Composable
private fun DoubleButton(
    modifier: Modifier,
    recipe: Recipe,
    onNewValue: (String) -> Unit,
    onClick: () -> Unit
) {

    ElevatedCard(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(containerColor = colorScheme.secondary)
    ) {
        Column {
            CustomTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                icon = Icons.Default.DriveFileRenameOutline,
                fieldLabel = stringResource(id = R.string.recipe_name),
                value = recipe.title,
                onValueChange = { onNewValue(it) }
            )

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 56.dp),
                thickness = 1.dp,
                color = colorScheme.onSecondary
            )

            CategoriesButton(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                onClick = onClick
            )
        }
    }
}

@Composable
private fun CategoriesButton(
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = {
            onClick()
        },
        shape = RectangleShape,
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = colorScheme.secondary,
            contentColor = colorScheme.onSecondary
        ),
        contentPadding = PaddingValues(horizontal = 12.dp),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
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
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "categories"
            )
        }
    }
}