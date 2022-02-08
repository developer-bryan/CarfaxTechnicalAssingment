package com.bryanmills.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bryanmills.core.models.Listings
import com.bryanmills.core.utils.*
import com.bryanmills.core.viewmodel.SelectedItemViewModel
import com.bryanmills.details.R
import com.bryanmills.details.databinding.FragmentVehicleDetailsBinding
import com.bryanmills.details.databinding.LayoutVehicleBreakdownBinding
import com.squareup.picasso.Picasso

class FragmentVehicleDetails : Fragment(R.layout.fragment_vehicle_details) {

    private var binding: FragmentVehicleDetailsBinding? = null
    private var breakdownBinding: LayoutVehicleBreakdownBinding? = null
    private val selectedViewModel: SelectedItemViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentVehicleDetailsBinding.bind(view)
        breakdownBinding = binding?.vehicleBreakdown
        selectedViewModel.selected.value?.let { bindVehicleData(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        breakdownBinding = null
    }

    fun bindVehicleData(listings: Listings) {
        with(binding!!) {
            title.text = listings.createTitle()
            price.text = listings.formattedPrice(requireContext())
            mileage.text = listings.formattedMileage(requireContext())
            Picasso.get().load(listings.headerPhoto()).into(image)
            binding?.button?.setOnClickListener {
                listings.phoneNumber()?.let { requireContext().dialNumber(it) }
            }
        }

        with(breakdownBinding!!) {
            locationBreakdown.setValue(listings.createLocation(requireContext()))
            exteriorColor.setValue(listings.exteriorColor ?: "")
            interiorColor.setValue(listings.interiorColor ?: "")
            driveType.setValue(listings.drivetype ?: "")
            trans.setValue(listings.transmission ?: "")
            body.setValue(listings.bodytype ?: "")
            engine.setValue(listings.createEngineAndDisplacement())
            fuel.setValue(listings.fuel ?: "")
        }
    }
}