package com.bryanmills.overview.repository

import com.bryanmills.core.models.VehicleListings
import com.bryanmills.overview.data.LocalDao
import com.bryanmills.overview.data.RemoteDao
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

class VehicleListingRepository @Inject constructor(
    private val localDao: LocalDao,
    private val remoteDao: RemoteDao,
) {
    private val disposable = CompositeDisposable()
    val _request by lazy { BehaviorSubject.create<VehicleListings?>() }

    fun getVehicleListingsData() {
        disposable.add(
            remoteDao.fetchListings()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    localDao.insert(it)
                    _request.onNext(it)
                }, {
                    fetchVehicleListingsFromCache()
                })
        )
    }

    private fun fetchVehicleListingsFromCache() {
        disposable.add(localDao.fetchListings()
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .subscribe { _request.onNext(it) })
    }

    fun clear() {
        disposable.clear()
    }
}