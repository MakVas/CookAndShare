package com.mealmentor.presentation.main

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            ElevatedCard(
                modifier = Modifier
                    .height(220.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Row {
//                    if (userData?.profilePictureUrl != null) {
//                        AsyncImage(
//                            model = userData.profilePictureUrl,
//                            contentDescription = "Profile Picture",
//                            modifier = Modifier
//                                .size(150.dp)
//                                .padding(16.dp)
//                                .clip(RoundedCornerShape(8.dp)),
//                            contentScale = ContentScale.Crop
//                        )
//                    }
//
//                    if (userData?.username != null) {
//                        Text(
//                            text = userData.username,
//                            style = MaterialTheme.typography.titleLarge,
//                            textAlign = TextAlign.Center,
//                            modifier = Modifier
//                                .padding(top = 16.dp)
//                        )
//                    }
                }
            }
        }
    }
}
