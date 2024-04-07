package com.cook_and_share.domain.model

data class Recipe(
    var userID: String = "",
    var author: String = "",
    var title: String = "",
    var imageUrl: String = "",
    var tags: List<String> = emptyList(),
    var ingredients: List<String> = emptyList(),
    var recipe : String = ""
)
