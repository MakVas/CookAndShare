package com.mealmentor.data

sealed class Resource<out R> {
    data class Success<out R>(val data: R) : Resource<R>()
    data class Error(val exception: Exception) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
}