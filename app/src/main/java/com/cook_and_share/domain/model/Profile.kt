package com.cook_and_share.domain.model

data class Profile(
    val userID: String = "",
    val username: String = "",
    val email: String = "",
    val profileImage: String = "",
    val bio: String = "",
    val likedRecipes: List<String> = emptyList(),
    val myRecipes: List<String> = emptyList()
)
