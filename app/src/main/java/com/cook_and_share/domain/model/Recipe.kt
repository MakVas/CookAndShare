package com.cook_and_share.domain.model

import java.util.Date

data class Recipe(
    val id: String = "",
    val createdAt: Date = Date(),
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
