package com.emotional.companionship.data.model

import java.io.Serializable

data class DigitalHuman(
    val id: String,
    val name: String,
    val relation: String,
    val personality: String,
    val avatarUrl: String,
    val lastChatTime: String
) : Serializable 