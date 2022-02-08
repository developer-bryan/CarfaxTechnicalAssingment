package com.bryanmills.overview.data

import com.bryanmills.core.models.VehicleListings
import io.reactivex.rxjava3.core.Observable

interface LocalDao {
    fun fetchListings(): Observable<VehicleListings>
    fun insert(model: VehicleListings)
}