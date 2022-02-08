package com.bryanmills.core.models

import androidx.room.Embedded
import androidx.room.Entity

@Entity
data class Listings(
    val bodytype: String? = null,
    val currentPrice: Double? = null,
    @Embedded val dealer: Dealer? = null,
    val displacement: String? = null,
    val drivetype: String? = null,
    val engine: String? = null,
    val fuel: String? = null,
    val interiorColor: String? = null,
    val exteriorColor: String? = null,
    @Embedded val images: Images? = null,
    val make: String? = null,
    val mileage: Int? = null,
    val model: String? = null,
    val transmission: String? = null,
    val trim: String? = null,
    val year: Int? = null,
)
