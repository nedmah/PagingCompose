package com.example.pagingcompose.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BreweryEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val breweryType: String,
    val street: String?,
    val city: String,
    val state: String,
    val country: String,
    val phone: String?,
    val websiteUrl: String?
)
