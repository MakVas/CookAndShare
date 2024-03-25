package com.cook_and_share.core.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
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
fun BottomSheet(
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