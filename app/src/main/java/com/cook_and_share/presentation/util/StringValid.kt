package com.cook_and_share.presentation.util

import android.util.Patterns
import java.util.regex.Pattern

private const val MIN_PASS_LENGTH = 6
private const val MIN_USERNAME_LENGTH = 4
private const val MIN_RECIPE_LENGTH = 2
private const val PASS_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"

fun String.isValidEmail(): Boolean {
    return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    return this.isNotBlank() &&
            this.length >= MIN_PASS_LENGTH &&
            Pattern.compile(PASS_PATTERN).matcher(this).matches()
}

fun String.isValidUsername(): Boolean{
    return this.isNotBlank() && this.length >= MIN_USERNAME_LENGTH
}

fun String.passwordMatches(repeated: String): Boolean {
    return this == repeated
}

fun String.idFromParameter(): String {
    return this.substring(1, this.length - 1)
}

fun String.isValidRecipe(): Boolean {
    return this.isNotBlank() && this.length >= MIN_RECIPE_LENGTH
}