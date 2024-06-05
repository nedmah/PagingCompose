package com.example.pagingcompose.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.pagingcompose.data.local.BreweryEntity
import com.example.pagingcompose.data.mappers.toBrewery
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class BreweryViewModel @Inject constructor(
    pager : Pager<Int,BreweryEntity>
): ViewModel() {

    val pageFlow = pager.flow.map {pagingData ->
        pagingData.map {
            it.toBrewery()
        }
    }.cachedIn(viewModelScope)
}