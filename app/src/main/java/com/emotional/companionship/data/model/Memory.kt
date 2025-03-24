package com.emotional.companionship.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Memory(
    val id: String,
    val title: String,
    val dateTime: String,
    val content: String
) : Parcelable 