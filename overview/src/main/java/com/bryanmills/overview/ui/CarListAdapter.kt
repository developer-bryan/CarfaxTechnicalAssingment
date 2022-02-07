package com.bryanmills.overview.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bryanmills.overview.R

class CarListAdapter : RecyclerView.Adapter<CarListAdapter.CarViewHolder>() {

    var dataset = ArrayList<Any>()
        get() = ArrayList<Any>(field)
        set(value) {
            field.clear()
            field.addAll(value)
        }

    private var inflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.context)
        }
        val contentView = inflater!!.inflate(R.layout.list_item_car_listing, parent, false)
        return CarViewHolder(contentView)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val data = dataset[position]
        holder.bind(data)
    }

    override fun getItemCount() = dataset.size

    class CarViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        fun bind(a: Any) {

        }
    }

}