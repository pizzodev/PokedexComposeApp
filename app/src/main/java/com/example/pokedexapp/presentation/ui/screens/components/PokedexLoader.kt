package com.example.pokedexapp.presentation.ui.screens.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pokedexapp.R
import com.example.pokedexapp.presentation.utils.LoadingStatus
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Singleton

@Singleton
@Composable
fun PokedexLoader(loadingStatus: MutableState<LoadingStatus>) {

    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1000
            }
        )
    )

    AnimatedVisibility(
        visible = evaluateStatus(loadingStatus.value),
        enter = fadeIn(
            // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
            initialAlpha = 0.4f
        ),
        exit = fadeOut(
            // Overwrites the default animation with tween
            animationSpec = tween(durationMillis = 250)
        )
    ) {
        Surface(
            modifier = Modifier
                .padding(15.dp)
                .wrapContentSize()
                .rotate(angle),
            color = Color.White
        ) {
            Column(modifier = Modifier.padding(1.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Image(painter = painterResource(id = R.drawable.pokeball),
                    contentDescription = "sunny icon",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(100.dp))
            }
        }
    }
}

private fun evaluateStatus(status: LoadingStatus): Boolean {
    return when (status) {
        LoadingStatus.IDLE -> {
            false
        }
        LoadingStatus.LOADING -> {
            true
        }
        LoadingStatus.COMPLETED, LoadingStatus.FAILED -> {
            false
        }
    }
}