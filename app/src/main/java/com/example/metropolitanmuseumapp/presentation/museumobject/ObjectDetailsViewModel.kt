package com.example.metropolitanmuseumapp.presentation.museumobject

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.metropolitanmuseumapp.data.retrofit.MetMuseumRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val OBJECT_ID = 436535

@HiltViewModel
class ObjectDetailsViewModel @Inject constructor(
    private val metMuseumRepo: MetMuseumRepo
) : ViewModel() {
    private val _museumObject = MutableStateFlow<RequestState>(RequestState.Loading)
    val museumObject get() = _museumObject.asStateFlow()

    fun getMuseumObjectById(id: Int = OBJECT_ID) {
        viewModelScope.launch {
            try {
                _museumObject.value = RequestState.Success(
                    metMuseumRepo.getMuseumObjectById(id)
                )
            } catch (e: Exception) {
                _museumObject.value = RequestState.Error(e.message.toString())
                Log.e("ViewModel",e.message.toString())
            }
        }
    }
}