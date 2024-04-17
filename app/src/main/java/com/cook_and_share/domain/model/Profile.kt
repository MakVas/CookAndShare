package com.cook_and_share.domain.model

import com.google.firebase.firestore.DocumentId

data class Profile(
    val userID: String = "",
    val username: String = "",
    val email: String = "",
    val profileImage: String = "",
    val bio: String = "",
    val followers: Int = 0,
    val following: Int = 0,
    val recipes: Int = 0,
    val likedRecipes: List<String> = emptyList()
)
