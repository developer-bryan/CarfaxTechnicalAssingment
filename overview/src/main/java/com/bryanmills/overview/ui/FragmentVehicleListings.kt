package com.bryanmills.overview.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bryanmills.core.viewmodel.SelectedItemViewModel
import com.bryanmills.overview.R
import com.bryanmills.overview.viewmodel.VehicleListingsViewmodel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentVehicleListings : Fragment(R.layout.fragment_vehicle_listings) {
    @Inject
    lateinit var adapter: VehicleListingAdapter
    private lateinit var viewmodel: VehicleListingsViewmodel
    private val selectedViewModel: SelectedItemViewModel by activityViewModels {
        SavedStateViewModelFactory(requireActivity().application, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProvider(this)[VehicleListingsViewmodel::class.java].apply {
            vehicleLiveData.observe(this@FragmentVehicleListings) {
                adapter.setData(it?.listings ?: ArrayList())
            }
            getListedVehicles()
        }
        view.findViewById<RecyclerView>(R.id.list).adapter = adapter
        adapter.clickListener = {
            selectedViewModel.selected.postValue(it)
            Navigation.findNavController(view).navigate(R.id.navigateToDetails)
        }
    }
}