package com.example.pagingcompose.data.remote

data class BreweryDto(
    val id: String,
    val name: String,
    val brewery_type: String,
    val street: String?,
    val city: String,
    val state: String,
    val country: String,
    val phone: String?,
    val website_url: String?
)
