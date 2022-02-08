package com.bryanmills.core.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.annotation.StringRes
import com.bryanmills.core.R
import com.bryanmills.core.models.Listings
import java.lang.Exception
import java.lang.NumberFormatException
import java.text.NumberFormat

fun Listings.createLocation(context: Context): String {

    val city = dealer?.city
    val state = dealer?.state

    val hasCityData = !city.isNullOrBlank()
    val hasStateData = !state.isNullOrBlank()

    return when {
        !hasCityData && hasStateData -> state!!
        hasCityData && !hasStateData -> city!!
        hasCityData && hasStateData -> String.format(context.getString(R.string.location_format), city ?: "", state ?: "")
        else -> ""
    }
}

fun Listings.createTitle() = "${year?.toString() ?: ""} ${make ?: ""} ${model ?: ""} ${trim ?: ""}"

fun Listings.headerPhoto(): String? = images?.firstPhoto?.large

fun Listings.formattedPrice(context: Context): String = try {
    val currency = NumberFormat.getInstance().format(currentPrice)
    String.format(context.getString(R.string.price_format), currency)
} catch (e: Exception) {
    ""
}

fun Listings.formattedMileage(context: Context): String = try {
    val currency = NumberFormat.getInstance().format(mileage)
    @StringRes
    val formatRes = if (mileage!! >= 1000) R.string.mileage_format_thousandths else R.string.mileage_format
    String.format(context.getString(formatRes, currency))
} catch (e: Exception) {
    ""
}

fun Listings.phoneNumber() = dealer?.phone

fun Listings.createEngineAndDisplacement() = "$engine $displacement"

fun Context.dialNumber(phoneNumber: String) {
    if (packageManager.hasSystemFeature(PackageManager.FEATURE_TELEPHONY)) {
        startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber")))
    }
}