package com.cook_and_share.domain.model

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Recipe(
    @DocumentId val id: String = "",
    @ServerTimestamp val createdAt: Date = Date(),
    val title: String = "",
    val author: String = "",
    val imageUrl: String = "",
    val tags: List<String> = emptyList(),
    val ingredients: List<Map<String, Int>> = emptyList(),
    val daily: Boolean = false,
    val recipe : String = "",
    val likes: Int = 0,
    val userID: String = ""
)
