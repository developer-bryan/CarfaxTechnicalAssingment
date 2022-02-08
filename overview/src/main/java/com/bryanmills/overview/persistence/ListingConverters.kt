package com.bryanmills.overview.persistence

import androidx.room.TypeConverter
import com.bryanmills.core.models.Listings
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListingConverters {
    @TypeConverter
    fun fromListing(listings: ArrayList<Listings>): String = Gson().toJson(listings)

    @TypeConverter
    fun toListing(listings: String): ArrayList<Listings> =
        Gson().fromJson(listings, object : TypeToken<ArrayList<Listings>>() {}.type)
}