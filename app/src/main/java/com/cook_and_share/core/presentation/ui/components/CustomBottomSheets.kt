package com.cook_and_share.core.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cook_and_share.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeBottomSheet(
    sheetState: SheetState,
    isSheetExpanded: MutableState<Boolean>
){
    if(isSheetExpanded.value) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                isSheetExpanded.value = false
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.image1),
                contentDescription = "Logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(500.dp))
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
){
    if(isSheetExpanded.value) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                isSheetExpanded.value = false
            }
        ) {
            TertiaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSettingsClick() }
                    .height(60.dp)
                    .padding(horizontal = 16.dp),
                label = R.string.settings,
                icon = Icons.Outlined.Settings,
                isArrow = true
            )

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
                isArrow = true
            )

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
                isArrow = true
            )

            Spacer(modifier = Modifier.height(100.dp))

        }
    }
}