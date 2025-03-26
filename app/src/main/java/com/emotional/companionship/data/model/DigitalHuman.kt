package com.emotional.companionship.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DigitalHuman(
    val id: String,
    val name: String,
    val relation: String,
    val personality: String,
    val avatarUrl: String,
    val lastChatTime: String
) : Parcelable 