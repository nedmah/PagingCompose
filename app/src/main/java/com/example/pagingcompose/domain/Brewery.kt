package com.example.pagingcompose.domain

data class Brewery(
    val id : String,
    val name: String,
    val breweryType: String,
    val street: String?,
    val city: String,
    val state: String,
    val country: String,
    val phone: String?,
    val websiteUrl: String?
)
