package com.cook_and_share.presentation.ui.screens.main.profile.about_me

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cook_and_share.R
import com.cook_and_share.domain.model.Profile
import com.cook_and_share.presentation.ui.components.CustomTextField
import com.cook_and_share.presentation.ui.components.CustomTitle
import com.cook_and_share.presentation.ui.components.ProfileItem
import com.cook_and_share.presentation.ui.components.RecipeTextField
import com.cook_and_share.presentation.ui.components.SecondaryButton
import com.cook_and_share.presentation.ui.components.TopAppBarBackIcon
import com.cook_and_share.presentation.ui.components.TopBar

@Composable
fun AboutMeScreen(
    popUp: () -> Unit,
    viewModel: AboutMeViewModel = hiltViewModel(),
) {
    val profile by viewModel.profile

    val uri by viewModel.profileImage
    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        if (it != null) {
            viewModel.getProfileImage(it)
        }
    }

    AboutMeScreenContent(
        popUp = popUp,
        profile = profile,
        onEditClick = { viewModel.onEditClick(popUp) },
        onUsernameChange = viewModel::onUsernameChange,
        onBioChange = viewModel::onBioChange,
        singlePhotoPicker = singlePhotoPicker,
        uri = uri
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AboutMeScreenContent(
    popUp: () -> Unit,
    profile: Profile,
    onEditClick: () -> Unit,
    onUsernameChange: (String) -> Unit,
    onBioChange: (String) -> Unit,
    singlePhotoPicker: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
    uri: Uri?
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopBar(
                title = {
                    CustomTitle(text = stringResource(id = R.string.about_me))
                },
                scrollBehavior = scrollBehavior,
                iconButton = {
                    TopAppBarBackIcon(popUp)
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
            NestedScrolling(
                profile = profile,
                onPublishClick = onEditClick,
                onUsernameChange = onUsernameChange,
                onBioChange = onBioChange,
                singlePhotoPicker = singlePhotoPicker,
                uri = uri
            )
        }
    }
}

@Composable
private fun NestedScrolling(
    profile: Profile,
    onPublishClick: () -> Unit,
    onUsernameChange: (String) -> Unit,
    singlePhotoPicker: ManagedActivityResultLauncher<PickVisualMediaRequest, Uri?>,
    onBioChange: (String) -> Unit,
    uri: Uri?
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

    ) {
        Spacer(modifier = Modifier.padding(top = 16.dp))

        ProfileItem(
            image = uri,
            profile = profile,
            onClick = {
                singlePhotoPicker.launch(PickVisualMediaRequest())
            },
            modifier = Modifier
                .height(220.dp)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

        CustomTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .shadow(1.dp, shape = RoundedCornerShape(16.dp)),
            icon = Icons.Outlined.AccountCircle,
            fieldLabel = stringResource(id = R.string.username),
            value = profile.username,
            onValueChange = { onUsernameChange(it) }
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

        RecipeTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(100.dp),
            fieldLabel = stringResource(id = R.string.bio),
            text = profile.bio,
            onNewValue = { onBioChange(it) }
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

        SecondaryButton(
            modifier = Modifier
                .height(65.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            label = R.string.save_changes,
            onClick = { onPublishClick() }
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

    }
}