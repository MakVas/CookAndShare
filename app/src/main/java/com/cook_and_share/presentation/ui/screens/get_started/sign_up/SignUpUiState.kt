package com.cook_and_share.presentation.ui.screens.get_started.sign_up

data class SignUpUiState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = ""
)