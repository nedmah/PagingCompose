package com.example.pagingcompose.data.remote

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.pagingcompose.data.local.BreweryDb
import com.example.pagingcompose.data.local.BreweryEntity
import com.example.pagingcompose.data.mappers.toBreweryEntity
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class BreweryRemoteMediator(
    private val breweryDb: BreweryDb,
    private val breweryApi: BreweryApi
) : RemoteMediator<Int, BreweryEntity>(){


    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BreweryEntity>
    ): MediatorResult {

        return try {
            val loadKey = when(loadType){
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null){
                        1
                    }else{
                        var count = 0
                        coroutineScope {
                            count = breweryDb.getBreweryDao().getCount()
                        }

                        (count / state.config.pageSize) +1
                    }
//                    state.pages.lastOrNull(){
//                        it.data.isNotEmpty()
//                    }?.nextKey ?: (state.pages.size+1)

                }
            }

            delay(2000)
            val breweries = breweryApi.getBrewery(
                page = loadKey,
                pageCount = state.config.pageSize
            )

            breweryDb.withTransaction {
                if(loadType == LoadType.REFRESH){
                    breweryDb.getBreweryDao().clearAllData()
                }
                val breweryEntities = breweries.map { it.toBreweryEntity() }
                breweryDb.getBreweryDao().upsertAll(breweryEntities)
            }

            MediatorResult.Success(breweries.isEmpty())

        } catch (e : IOException){
            MediatorResult.Error(e)
        } catch (e : HttpException){
            MediatorResult.Error(e)
        }
    }

}