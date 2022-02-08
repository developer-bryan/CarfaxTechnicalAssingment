package com.bryanmills.overview.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bryanmills.core.models.VehicleListings
import com.bryanmills.overview.data.LocalDao
import io.reactivex.rxjava3.core.Observable

@Dao
interface VehicleLocalDao: LocalDao {

    @Query("SELECT * FROM TABLE_VEHICLES")
    override fun fetchListings(): Observable<VehicleListings>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(model: VehicleListings)
}