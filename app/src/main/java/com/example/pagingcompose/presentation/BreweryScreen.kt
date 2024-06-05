package com.example.pagingcompose.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.pagingcompose.domain.Brewery
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun BreweryScreen(
    breweries : LazyPagingItems<Brewery>
){
    val context = LocalContext.current

    LaunchedEffect(key1 = breweries.loadState){
        if (breweries.loadState.refresh is LoadState.Error){
            Toast.makeText(context,
                (breweries.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG)
                .show()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        if(breweries.loadState.refresh is LoadState.Loading){
            CircularProgressIndicator(
                modifier = Modifier
                )
        }else{
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(breweries.itemCount){
                    val brewery = breweries[it]
                    if (brewery != null){
                        BreweryItem(brewery = brewery, modifier = Modifier.fillMaxWidth())
                    }
                }

                item {
                    if(breweries.loadState.append is LoadState.Loading){
                        CircularProgressIndicator(
                            modifier = Modifier
                            )
                    }
                }

            }
        }
    }
}
