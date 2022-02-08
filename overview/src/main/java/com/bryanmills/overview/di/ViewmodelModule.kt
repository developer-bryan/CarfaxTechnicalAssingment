package com.bryanmills.overview.di

import android.content.Context
import androidx.room.Room
import com.bryanmills.core.utils.LISTINGS_BASE_URL
import com.bryanmills.core.utils.LISTINGS_DATABASE
import com.bryanmills.overview.data.LocalDao
import com.bryanmills.overview.data.RemoteDao
import com.bryanmills.overview.persistence.VehicleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
class ViewmodelModule {

    @Provides
    fun provideRetrofitInstance() =
        Retrofit.Builder()
            .baseUrl(LISTINGS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()

    @Provides
    fun profideRemoveContract(retrofit: Retrofit): RemoteDao = retrofit.create(RemoteDao::class.java)

    @Provides
    fun provideRoomDatabase(@ApplicationContext ctx: Context): VehicleDatabase =
        Room.databaseBuilder(ctx, VehicleDatabase::class.java, LISTINGS_DATABASE).build()

    @Provides
    fun provideLocalDao(database: VehicleDatabase): LocalDao = database.getVehicleDao()

}
