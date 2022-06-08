package com.example.pokedexapp.presentation.ui.screens.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.pokedexapp.presentation.utils.LoadingStatus
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Singleton

@Singleton
@Composable
fun PokedexLoader(loadingStatus: MutableState<LoadingStatus>) {
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
        CircularProgressIndicator()
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