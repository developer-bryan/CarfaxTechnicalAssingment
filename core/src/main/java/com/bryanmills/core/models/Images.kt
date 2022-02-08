package com.bryanmills.core.models

import androidx.room.Embedded
import androidx.room.Entity

@Entity
data class Images(@Embedded val firstPhoto: FirstPhoto? = null)
