package com.bryanmills.core.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TABLE_VEHICLES")
data class VehicleListings(@PrimaryKey val primaryKey: Int = 1, val listings: ArrayList<Listings>)

