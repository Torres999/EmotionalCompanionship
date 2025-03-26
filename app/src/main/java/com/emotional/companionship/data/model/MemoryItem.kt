package com.emotional.companionship.data.model

data class MemoryItem(
    val id: String,
    val title: String,
    val content: String,
    val date: String,
    val imageUrl: String? = null
) 