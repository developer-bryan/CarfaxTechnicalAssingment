package com.bryanmills.overview.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bryanmills.core.models.Listings
import com.bryanmills.core.utils.*
import com.bryanmills.overview.databinding.ListItemCarListingBinding
import com.squareup.picasso.Picasso
import javax.inject.Inject

class VehicleListingAdapter @Inject constructor() :
    RecyclerView.Adapter<VehicleListingAdapter.CarViewHolder>() {

    private val dataset = ArrayList<Listings>()

    private var inflater: LayoutInflater? = null

    var clickListener: ((Listings) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.context)
        }

        val itemBinding = ListItemCarListingBinding.inflate(inflater!!)
        return CarViewHolder(itemBinding.root).apply { binding = itemBinding }
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val data = dataset[position]
        with(holder) {
            binding?.button?.setOnClickListener {
                data.phoneNumber()?.let { phoneNumber -> holder.itemView.context.dialNumber(phoneNumber) }
            }
            binding?.container?.setOnClickListener {
                clickListener?.invoke(data)
            }
            bind(data)
        }
    }

    override fun getItemCount() = dataset.size

    fun setData(data: List<Listings>) {
        this.dataset.clear()
        this.dataset.addAll(data)
        notifyDataSetChanged()
    }

    class CarViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var binding: ListItemCarListingBinding? = null

        fun bind(vehicleListing: Listings) {
            val context = itemView.context

            val title = vehicleListing.createTitle()
            val location = vehicleListing.createLocation(context)
            val price = vehicleListing.formattedPrice(context)
            val mileage = vehicleListing.formattedMileage(context)
            val headerImage = vehicleListing.headerPhoto()

            with(binding!!) {
                this.title.text = title
                this.location.text = location
                this.price.text = price
                this.mileage.text = mileage
                Picasso.get().load(headerImage).into(image)
            }
        }
    }

}