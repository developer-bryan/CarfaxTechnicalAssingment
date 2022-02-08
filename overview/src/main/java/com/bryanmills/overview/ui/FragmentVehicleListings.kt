package com.bryanmills.overview.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bryanmills.overview.R
import com.bryanmills.overview.viewmodel.VehicleListingsViewmodel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentVehicleListings : Fragment(R.layout.fragment_vehicle_listings) {
    @Inject
    lateinit var adapter: VehicleListingAdapter
    lateinit var viewmodel: VehicleListingsViewmodel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel = ViewModelProvider(this)[VehicleListingsViewmodel::class.java].apply {
            vehicleLiveData.observe(this@FragmentVehicleListings) {
                adapter.setData(it?.listings ?: ArrayList())
            }
            getListedVehicles()
            view.findViewById<RecyclerView>(R.id.list).adapter = adapter
        }
    }
}