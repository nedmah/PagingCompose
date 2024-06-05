package com.example.pagingcompose.data.mappers

import com.example.pagingcompose.data.local.BreweryEntity
import com.example.pagingcompose.data.remote.BreweryDto
import com.example.pagingcompose.domain.Brewery

fun BreweryDto.toBreweryEntity() : BreweryEntity{
    return BreweryEntity(
        id = id,
        name = name,
        breweryType = brewery_type,
        street = street,
        city = city,
        state = state,
        country = country,
        phone = phone,
        websiteUrl = website_url
    )
}

fun BreweryEntity.toBrewery() : Brewery {
    return Brewery(
        id = id,
        name = name,
        breweryType = breweryType,
        street = street,
        city = city,
        state = state,
        country = country,
        phone = phone,
        websiteUrl = websiteUrl
    )
}