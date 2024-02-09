package com.mealmentor.ui.pages.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mealmentor.R
import com.mealmentor.ui.pages.screens.elements.RecipeColumnItem
import com.mealmentor.ui.pages.screens.elements.RecipeRowItem

@Composable
fun HomeScreen() {
    Scaffold { values ->
        Box(
            modifier = Modifier
                .padding(values)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            NestedScrolling()
        }
    }
}

@Composable
fun NestedScrolling() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        //contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = R.string.top_of_the_day),
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            SubRow()

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = stringResource(id = R.string.subscribed_recipes),
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
        subColumn()
    }
}

@Composable
fun SubRow() {
    LazyRow {
        items(50) {
            Spacer(modifier = Modifier.width(16.dp))
            RecipeRowItem(text = "Item $it")
        }
    }
}

fun LazyListScope.subColumn() {
    items(50) {
        RecipeColumnItem(text = "Item $it")
        Spacer(modifier = Modifier.height(16.dp))
    }
}