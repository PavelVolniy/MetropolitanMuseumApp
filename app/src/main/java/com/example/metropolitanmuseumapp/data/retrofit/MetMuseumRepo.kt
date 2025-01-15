package com.example.metropolitanmuseumapp.data.retrofit

import javax.inject.Inject

class MetMuseumRepo @Inject constructor(
    private val api: MetMuseumAPI
) {
    suspend fun getMuseumObjectById(id: Int) = api.getObjectById(id)
}