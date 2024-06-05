package com.example.pagingcompose.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.pagingcompose.data.local.BreweryDb
import com.example.pagingcompose.data.local.BreweryEntity
import com.example.pagingcompose.data.remote.BreweryApi
import com.example.pagingcompose.data.remote.BreweryRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context) : BreweryDb{
        return Room.databaseBuilder(
            context,
            BreweryDb::class.java,
            "brewery.db"
        ).build()
    }


    @Provides
    @Singleton
    fun provideBeerApi(): BreweryApi {
        return Retrofit.Builder()
            .baseUrl(BreweryApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideBeerPager(breweryDb: BreweryDb, breweryApi: BreweryApi): Pager<Int, BreweryEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = BreweryRemoteMediator(
                breweryApi = breweryApi,
                breweryDb = breweryDb
            ),
            pagingSourceFactory = {
                breweryDb.getBreweryDao().pagingSource()
            }
        )
    }

}