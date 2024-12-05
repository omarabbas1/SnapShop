package com.myproject.project_279

data class Item(
    val name: String,
    val price: String,
    val imageUrl: String,
    var isFavorite: Boolean = false // Boolean to track favorite status
)
