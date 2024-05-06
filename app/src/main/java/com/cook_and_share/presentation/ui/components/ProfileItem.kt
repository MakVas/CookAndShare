package com.cook_and_share.presentation.ui.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cook_and_share.R
import com.cook_and_share.domain.model.Profile


@Composable
fun ProfileItem(
    modifier: Modifier,
    image: Uri? = null,
    profile: Profile,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .shadow(1.dp, RoundedCornerShape(16.dp), clip = true)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .background(MaterialTheme.colorScheme.secondary),
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.profile_default),
                contentDescription = "profile image",
                modifier = Modifier
                    .padding(16.dp)
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            AsyncImage(
                modifier = Modifier
                    .padding(16.dp)
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp)),
                model = image ?: profile.profileImage,
                contentScale = ContentScale.Crop,
                clipToBounds = true,
                contentDescription = "profile image",
            )
        }
        Text(
            buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                        color = MaterialTheme.colorScheme.tertiary,
                        fontWeight = MaterialTheme.typography.titleMedium.fontWeight
                    )
                ) {
                    append(stringResource(id = R.string.username) + ":\n")
                }
                append(profile.username + "\n")
                withStyle(
                    SpanStyle(
                        fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                        color = MaterialTheme.colorScheme.tertiary,
                        fontWeight = MaterialTheme.typography.titleMedium.fontWeight
                    )
                ) {
                    append(stringResource(id = R.string.recipes) + ":\n")
                }
                append(profile.myRecipes.size.toString() + "\n")
            },
            Modifier.padding(top = 16.dp, start = 156.dp)
        )

        Text(
            modifier = Modifier
                .padding(top = 142.dp, start = 16.dp),
            text = buildAnnotatedString {
                withStyle(
                    SpanStyle(
                        fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                        color = MaterialTheme.colorScheme.tertiary,
                        fontWeight = MaterialTheme.typography.titleMedium.fontWeight
                    )
                ) {
                    append(stringResource(id = R.string.bio) + ": ")
                }
                append(profile.bio)
            }
        )
    }
}