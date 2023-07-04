package com.example.calsdown_projekatzakol.model

import androidx.annotation.DrawableRes

data class Coffee(
    val title: String,
    val price: String,
    @DrawableRes
    val imgId: Int,
    val description: String
)
