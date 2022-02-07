package com.bryanmills.overview.ui

import androidx.fragment.app.Fragment
import com.bryanmills.overview.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentOverview : Fragment(R.layout.fragment_overview) {
    @Inject
    lateinit var adapter: CarListAdapter
}