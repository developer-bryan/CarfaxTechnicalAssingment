package com.bryanmills.overview.di

import com.bryanmills.overview.ui.CarListAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class OverviewModule {
    @Provides
    fun provideListAdapter() = CarListAdapter()
}