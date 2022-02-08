package com.bryanmills.core.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bryanmills.core.models.Listings

class SelectedItemViewModel: ViewModel() {
    val selected = MutableLiveData<Listings>()
}