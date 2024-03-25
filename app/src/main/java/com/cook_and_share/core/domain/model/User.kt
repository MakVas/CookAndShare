package com.cook_and_share.core.domain.model

data class User (
    var name: String = "",
    var email: String = "",
    var userID: String = "",
    var imageUrl: String = "",
    var totalPosts: String = "",
    var bio: String = ""
)