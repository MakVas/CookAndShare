package com.cook_and_share.presentation.ui.screens.main.add_recipe.add_recipe

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cook_and_share.R
import com.cook_and_share.domain.model.Recipe
import com.cook_and_share.presentation.ui.components.CustomLabel
import com.cook_and_share.presentation.ui.components.CustomSmallLabel
import com.cook_and_share.presentation.ui.components.CustomTextField
import com.cook_and_share.presentation.ui.components.CustomTitle
import com.cook_and_share.presentation.ui.components.PrimaryButton
import com.cook_and_share.presentation.ui.components.RecipeItem
import com.cook_and_share.presentation.ui.components.RecipeTextField
import com.cook_and_share.presentation.ui.components.SecondaryButton
import com.cook_and_share.presentation.ui.components.TopBar
import com.cook_and_share.presentation.ui.screens.main.add_recipe.AddRecipeViewModel
import com.cook_and_share.presentation.ui.theme.CustomChocolate

@Composable
fun AddRecipeScreen(
    viewModel: AddRecipeViewModel = hiltViewModel(),
    navigate: (String) -> Unit,
    popUp: () -> Unit
) {
    val recipe by viewModel.recipe

    val uri by viewModel.recipeImage
    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        if (it != null) {
            viewModel.getRecipeImage(it)
        }
    }

    AddRecipeScreenContent(
        recipe = recipe,
        onPublishClick = { viewModel.onPublishClick((popUp), viewModel.selectedCategories) },
        onTitleChange = viewModel::onTitleChange,
        onRecipeChange = viewModel::onRecipeChange,
        onIngredientsClick = { viewModel.onIngredientsClick(navigate) },
        onCategoryClick = { viewModel.onCategoryScreenClick(navigate) },
        singlePhotoPicker = singlePhotoPicker,
        uri = uri
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddRecipeScreenContent(
    recipe: Recipe,
    onPublishClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    onRecipeChange: (String) -> Unit,
    onIngredientsClick: () -> Unit,
    onCategoryClick: () -> Unit,
    singlePhotoPicker: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
    uri: Uri?
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                title = {
                    CustomTitle(text = stringResource(id = R.string.add_recipe))
                },
                scrollBehavior = scrollBehavior,
                actions = {
                    Text(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable { },
                        color = colorScheme.surfaceTint,
                        style = typography.bodyMedium,
                        text = stringResource(id = R.string.reset),
                    )
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
                recipe = recipe,
                onPublishClick = onPublishClick,
                onTitleChange = onTitleChange,
                onRecipeChange = onRecipeChange,
                onIngredientsClick = onIngredientsClick,
                onCategoryClick = onCategoryClick,
                singlePhotoPicker = singlePhotoPicker,
                uri = uri
            )
        }
    }
}

@Composable
private fun NestedScrolling(
    recipe: Recipe,
    onPublishClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    onIngredientsClick: () -> Unit,
    onCategoryClick: () -> Unit,
    singlePhotoPicker: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
    onRecipeChange: (String) -> Unit,
    uri: Uri?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

    ) {
        CustomLabel(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(id = R.string.recipe_name)
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))

        CustomTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            icon = Icons.Default.DriveFileRenameOutline,
            fieldLabel = stringResource(id = R.string.recipe_name),
            value = recipe.title,
            onValueChange = { onTitleChange(it) }
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

        CustomLabel(
            modifier = Modifier.padding(start = 16.dp),
            text = stringResource(id = R.string.upload_photo)
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))

        LazyRow {
            items(3) {
                AddPhotoButton()
            }

            item {
                Spacer(modifier = Modifier.width(16.dp))
            }
        }

        Spacer(modifier = Modifier.padding(top = 16.dp))

        RecipeItem(
            image = uri.toString(),
            recipe = recipe,
            onClick = {
                singlePhotoPicker.launch(PickVisualMediaRequest())
            },
            modifier = Modifier.padding(horizontal = 16.dp),
            isPreview = true
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

        DoubleButton(
            modifier = Modifier.padding(horizontal = 16.dp),
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
            onClick = { onPublishClick() }
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

    }
}

@Composable
private fun DoubleButton(
    modifier: Modifier,
    onClick: () -> Unit
) {

    ElevatedCard(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(1.dp),
        colors = CardDefaults.cardColors(containerColor = colorScheme.secondary)
    ) {
        Column {


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

@Composable
fun AddPhotoButton() {
    Box(
        modifier = Modifier
            .padding(start = 16.dp)
            .width(150.dp)
            .height(125.dp)
            .background(
                color = colorScheme.secondary,
                shape = RoundedCornerShape(size = 16.dp)
            )
            .drawWithContent {
                drawRoundRect(
                    color = CustomChocolate,
                    style = Stroke(
                        cap = StrokeCap.Round,
                        width = 3.5f,
                        pathEffect = PathEffect.dashPathEffect(
                            intervals = floatArrayOf(10.dp.toPx(), 10.dp.toPx()),
                            phase = 0f
                        )
                    ),
                    cornerRadius = CornerRadius(16.dp.toPx())
                )
                this.drawContent()
            }
            .clip(RoundedCornerShape(16.dp))
            .clickable {  }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier.size(46.dp),
                tint = colorScheme.tertiary,
                imageVector = Icons.Default.CloudUpload,
                contentDescription = "no photo"
            )
            CustomSmallLabel(
                text = stringResource(id = R.string.add)
            )
        }
    }
}