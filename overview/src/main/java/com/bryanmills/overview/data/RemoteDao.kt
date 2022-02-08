package com.bryanmills.overview.data

import com.bryanmills.core.models.VehicleListings
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface RemoteDao {
    @GET("assignment.json")
    fun fetchListings(): Single<VehicleListings>
}