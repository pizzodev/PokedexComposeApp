package com.example.pokedexapp.presentation.ui.screens.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.pokedexapp.data.model.PokemonWithDetail

@Composable
fun PokemonRow(pokemonDetail: PokemonWithDetail, onItemClickCbk: (name: String) -> Unit) {
    Surface(
        modifier = Modifier
            .background(Color.LightGray)
    ) {
        Card(Modifier
            .padding(15.dp, 10.dp, 15.dp, 10.dp)
            .background(Color.White)
            .clickable {
                onItemClickCbk.invoke(pokemonDetail.name)
            },
            elevation = 5.dp
        ) {
            Row(
                modifier = Modifier.wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    modifier = Modifier
                        .padding(15.dp, 15.dp, 15.dp, 15.dp)
                        .size(80.dp)
                        .clip(CircleShape)
                        .border(0.5.dp, Color.Gray, CircleShape)
                    ,
                    contentScale = ContentScale.Fit,
                    painter = rememberImagePainter(
                        data = pokemonDetail.sprites.front_default,
                    ),
                    contentDescription = "Pokemon sprite"
                )
                Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    text = pokemonDetail.name.capitalize(Locale.current)
                )
            }
        }
    }
}