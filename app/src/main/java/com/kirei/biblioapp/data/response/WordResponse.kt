package com.kirei.biblioapp.data.response

data class WordResponse(
    val definition: String,
    val thumbs_up: Int,
    val author: String,
    val word: String,
    val defid: Int,
    val written_on: String,
    val example: String,
    val thumbs_down: Int,
    val id: Int
)