package com.cook_and_share.presentation.main.screens.add_recipe

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
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
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
import com.cook_and_share.R
import com.cook_and_share.presentation.custom.CustomTextField
import com.cook_and_share.presentation.custom.RecipeItem
import com.cook_and_share.presentation.custom.RecipeTextField
import com.cook_and_share.presentation.main.TopBar
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipeScreen(
    scope: CoroutineScope,
    drawerState: DrawerState,
    scrollBehavior: TopAppBarScrollBehavior
) {
    Scaffold (topBar = {
        TopBar(
            text = R.string.add,
            scope = scope,
            drawerState = drawerState,
            scrollBehavior = scrollBehavior
        )
    }
    ){ values ->
        Box(
            modifier = Modifier
                .padding(values)
                .fillMaxSize()
                .background(colorScheme.background)
        ) {
            NestedScrolling()
        }
    }
}

@Composable
private fun NestedScrolling(){
    val recipeName = remember { mutableStateOf("") }
    var cookingMethod by remember { mutableStateOf("") }
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
            image = R.drawable.no_image,
            name = "username",
            title = recipeName.value,
            likes = "123",
            isPreview = true
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

        DoubleButton(recipeName)

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
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
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
               // style = ButtonText
            )
        }
        Spacer(modifier = Modifier.padding(top = 16.dp))
    }
}

@Composable
private fun DoubleButton(recipeName: MutableState<String>){

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

            HorizontalDivider(
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
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "categories"
                    )
                }
            }
        }
    }
}