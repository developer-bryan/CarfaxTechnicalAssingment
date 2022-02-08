package com.bryanmills.overview.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bryanmills.core.models.VehicleListings

@Database(version = 1, entities = [VehicleListings::class])
@TypeConverters(ListingConverters::class)
abstract class VehicleDatabase: RoomDatabase() {
    abstract fun getVehicleDao(): VehicleLocalDao
}