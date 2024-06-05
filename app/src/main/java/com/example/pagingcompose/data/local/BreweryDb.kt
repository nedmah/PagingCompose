package com.example.pagingcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        BreweryEntity::class
    ],
    version = 1
)
abstract class BreweryDb : RoomDatabase() {

    abstract fun getBreweryDao() : BreweryDao
}