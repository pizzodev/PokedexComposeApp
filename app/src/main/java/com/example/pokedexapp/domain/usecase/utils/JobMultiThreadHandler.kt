package com.example.pokedexapp.domain.usecase.utils

import kotlinx.coroutines.Job
import kotlinx.coroutines.joinAll

object JobMultiThreadHandler {
    suspend fun waitForCompletion(jobs: List<Job>) {
        jobs.joinAll()
    }
}