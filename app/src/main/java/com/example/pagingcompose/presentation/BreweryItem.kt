package com.example.pagingcompose.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pagingcompose.domain.Brewery
import com.example.pagingcompose.ui.theme.PagingComposeTheme

@Composable
fun BreweryItem(
    brewery: Brewery,
    modifier: Modifier
){
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = brewery.name,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = brewery.breweryType,
                fontStyle = FontStyle.Italic,
                color = Color.Blue,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = brewery.city,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = brewery.country,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                fontSize = 16.sp
            )
        }
    }
}

@Preview
@Composable
fun BreweryItemPreview(){
    PagingComposeTheme {
        BreweryItem(brewery = Brewery(
            "id",
            "pivo",
            "svetloe",
            "street",
            "Moscow",
            "Moscow state",
            "Russia",
            "8917161651",
            null
        ), modifier = Modifier)
    }
}