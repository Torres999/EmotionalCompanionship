package com.emotional.companionship.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String,
    val userId: String,
    val name: String,
    val description: String,
    val avatar: String,
    val email: String
) : Parcelable 