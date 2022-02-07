package com.bryanmills.core.models

data class Listings(
    val bodytype: String? = null,
    val currentPrice: Double? = null,
    val dealer: Dealer? = null,
    val displacement: String? = null,
    val drivetype: String? = null,
    val engine: String? = null,
    val fuel: String? = null,
    val interiorColor: String? = null,
    val exteriorColor: String? = null,
    val images: Images? = null,
    val make: String? = null,
    val mileage: Int? = null,
    val model: String? = null,
    val transmission: String? = null,
    val trim: String? = null,
    val year: Int? = null,
)
