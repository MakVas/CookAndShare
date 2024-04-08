package com.cook_and_share.domain.model

data class Profile(
    val userId: String = "",
    val name: String = "",
    val email: String = "",
    val profileImage: String = "",
    val bio: String = "",
    val followers: Int = 0,
    val following: Int = 0,
    val recipes: Int = 0,
)
