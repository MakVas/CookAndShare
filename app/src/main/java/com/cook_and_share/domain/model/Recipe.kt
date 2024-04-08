package com.cook_and_share.domain.model

import com.google.firebase.firestore.DocumentId

data class Recipe(
    @DocumentId val id: String = "",
    val title: String = "",
    val author: String = "",
    val imageUrl: String = "",
    val tags: List<String> = emptyList(),
    val ingredients: List<Map<String, Int>> = emptyList(),
    val recipe : String = "",
    val likes: Int = 0,
    val userID: String = ""
)
