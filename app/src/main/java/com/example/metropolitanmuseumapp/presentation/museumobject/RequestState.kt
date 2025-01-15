package com.example.metropolitanmuseumapp.presentation.museumobject

import com.example.metropolitanmuseumapp.entity.MuseumObject

sealed class RequestState {
    data object Loading : RequestState()
    class Error(val message: String) : RequestState()
    class Success(val museumObject: MuseumObject) : RequestState()
}