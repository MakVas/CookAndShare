package com.mealmentor.domain.model

data class User(
    var name: String = "",
    var email: String = "",
    var userID: String = "",
    var password: String = "",
    var imageUrl: String = "",
    var following: List<String> = emptyList(),
    val totalPosts: String = "",
    val bio: String = "",
    var url: String = ""
)
