package com.bryanmills.overview.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bryanmills.core.models.VehicleListings
import com.bryanmills.overview.repository.VehicleListingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class VehicleListingsViewmodel @Inject constructor(
    private val repository: VehicleListingRepository
): ViewModel() {
    val vehicleLiveData = MutableLiveData<VehicleListings?>()

    fun getListedVehicles() {
        with(repository) {
           _request
               .observeOn(Schedulers.io())
               .subscribeOn(AndroidSchedulers.mainThread())
               .subscribe {
                   vehicleLiveData.postValue(it) }
           getVehicleListingsData()
        }
    }

    override fun onCleared() {
        super.onCleared()
        repository.clear()
        repository._request.unsubscribeOn(Schedulers.io())
    }
}