package com.kirei.biblioapp

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers


data class CoroutinesContextProvider(

    val main: CoroutineContext = Dispatchers.Main,
    val io: CoroutineContext = Dispatchers.Default
)