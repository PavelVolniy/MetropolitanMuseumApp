package com.example.metropolitanmuseumapp.data.retrofit

import com.example.metropolitanmuseumapp.entity.MuseumObject
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface MetMuseumAPI {

    @GET("/public/collection/v1/objects/{objectID}")
    suspend fun getObjectById(@Path("objectID") id: Int): MuseumObject
}