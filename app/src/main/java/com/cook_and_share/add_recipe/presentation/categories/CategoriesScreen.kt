package com.cook_and_share.add_recipe.presentation.screens.add_recipe.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cook_and_share.R
import com.cook_and_share.core.presentation.ui.components.ButtonTextField
import com.cook_and_share.core.presentation.ui.components.TopAppBarBackIcon
import com.cook_and_share.core.presentation.ui.components.TopBar
import com.cook_and_share.core.presentation.util.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    navController: NavHostController,
    onValueChange: MutableState<String>
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                text = R.string.categories,
                scrollBehavior = scrollBehavior,
                iconButton = {
                    TopAppBarBackIcon(
                        navController = navController,
                        navigate = Screens.AddRecipeScreen.route,
                        popUpTo = Screens.CategoriesScreen.route
                    )
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
            NestedScrolling(onValueChange)
        }
    }
}

@Composable
private fun NestedScrolling(onValueChange: MutableState<String>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.padding(top = 16.dp))

        ButtonTextField(
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
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }

        Spacer(modifier = Modifier.padding(top = 16.dp))

        Text(
            text = stringResource(id = R.string.popular_categories),
            style = MaterialTheme.typography.titleMedium,
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
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = "category",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}