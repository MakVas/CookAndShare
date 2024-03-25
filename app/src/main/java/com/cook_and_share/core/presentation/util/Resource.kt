package com.cook_and_share.core.presentation.util

sealed class Resource<out R> {
    data class Success<out R>(val data: R) : Resource<R>()
    data class Error(val exception: Exception) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
}