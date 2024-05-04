package com.cook_and_share.presentation.ui.components

import androidx.compose.foundation.Image
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cook_and_share.presentation.ui.theme.Typography

@Composable
fun SearchItem(
    onClick: () -> Unit,
    defaultImage: Int,
    image: String,
    title: String,
    text: String,
    modifier: Modifier = Modifier
) {
    ElevatedButton(
        onClick = onClick,
        modifier = modifier
            .height(80.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary
        ),
        elevation = ButtonDefaults.buttonElevation(1.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Box {
            Row(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (image == "") {
                    Image(
                        modifier = Modifier
                            .size(80.dp),
                        painter = painterResource(id = defaultImage),
                        contentDescription = "profile image",
                        contentScale = ContentScale.Crop,
                    )
                } else {
                    AsyncImage(
                        modifier = Modifier
                            .size(80.dp),
                        model = image,
                        contentDescription = "profile image",
                        contentScale = ContentScale.Crop,
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 16.dp, bottom = 10.dp, top = 10.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Text(
                        text = title,
                        style = Typography.titleSmall
                    )

                    Text(
                        text = text,
                        style = Typography.labelMedium,
                    )
                }
            }
        }
    }
}