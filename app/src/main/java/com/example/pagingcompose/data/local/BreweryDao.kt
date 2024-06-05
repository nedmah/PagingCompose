package com.example.pagingcompose.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.pagingcompose.domain.Brewery

@Dao
interface BreweryDao {

    @Upsert
    suspend fun upsertAll(brewery: List<BreweryEntity>)

    @Query("SELECT * FROM breweryentity")
    fun pagingSource() : PagingSource<Int, BreweryEntity>

    @Query("DELETE FROM breweryentity")
    suspend fun clearAllData()

}