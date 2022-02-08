package com.bryanmills.core.models

import androidx.room.Entity

@Entity
data class FirstPhoto(val large: String? = null, val medium: String? = null, val small: String? = null)
