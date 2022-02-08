package com.bryanmills.core.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import com.bryanmills.core.R
import com.bryanmills.core.models.Listings
import java.lang.NumberFormatException
import java.text.NumberFormat

fun Listings.createLocation(context: Context): String {
    val location = String.format(context.getString(R.string.location_format), dealer?.city ?: "", dealer?.state ?: "")
    return location
}

fun Listings.createTitle() = "${year?.toString() ?: ""} ${make ?: ""} ${model ?: ""} ${trim ?: ""}"

fun Listings.headerPhoto(): String? = images?.firstPhoto?.large

fun Listings.formattedPrice(context: Context): String = try {
    val currency = NumberFormat.getInstance().format(currentPrice)
    String.format(context.getString(R.string.price_format), currency)
} catch (e: NumberFormatException) {
    ""
}

fun Listings.formattedMileage(context: Context): String = try {
    val currency = NumberFormat.getInstance().format(mileage)
    String.format(context.getString(R.string.price_format), currency)
} catch (e: NumberFormatException) {
    ""
}

fun Listings.phoneNumber() = dealer?.phone

fun Context.dialNumber(phoneNumber: String) {
    if (packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY)) {
        startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber")))
    }
}