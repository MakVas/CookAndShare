package com.cook_and_share.core.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.cook_and_share.core.presentation.ui.theme.Typography

@Composable
fun RecipeItem(
    onClick: () -> Unit,
    image: Int,
    name: String,
    title: String,
    likes: String,
    isPreview: Boolean,
    modifier: Modifier = Modifier
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier
            .height(220.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorScheme.secondary
        ),
        elevation = ButtonDefaults.buttonElevation(1.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box {

            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = image),
                contentDescription = "recipe image",
                contentScale = Crop,
            )
            Row(
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth()
                    .background(colorScheme.onSurface)
                    .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 16.dp, bottom = 10.dp, top = 10.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = title,
                        modifier = Modifier.padding(top = 6.dp),
                        style = Typography.titleSmall
                    )

                    Text(
                        text = name,
                        style = Typography.labelMedium,
                    )
                }
                if (!isPreview) {
                   LikeButton(
                       likes = likes,
                       modifier = Modifier
                           .fillMaxHeight()
                           .padding(end = 16.dp, bottom = 10.dp, top = 10.dp)
                       )
                }
            }
        }
    }
}

@Composable
private fun LikeButton(
    likes: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        IconButton(
            modifier = Modifier
                .size(30.dp),
            onClick = { },
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Like Btn"
            )
        }
        Text(
            text = likes,
            style = typography.labelMedium,
        )
    }
}