package com.bryanmills.core.models

import androidx.room.Entity

@Entity
data class Dealer(
    val city: String? = null,
    val phone: String? = null,
    val state: String? = null
)
