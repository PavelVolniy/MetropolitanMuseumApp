package com.example.metropolitanmuseumapp.entity

data class MuseumObject(
    val objectID: Int,
    val objectName: String,
    val title: String,
    val objectBeginDate: Int,
    val objectEndDate: Int,
    val primaryImageSmall: String,
    val artistRole: String,
    val artistDisplayName: String,
    val artistDisplayBio: String,
    val department: String,
    val culture: String,
    val period: String,
    val medium: String,
    val dimensions: String
)