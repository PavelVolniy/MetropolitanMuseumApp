package com.example.metropolitanmuseumapp.presentation.museumobject

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("RememberReturnType")
@Composable
fun MuseumObjectContainer(
    modifier: Modifier,
    objectDetailsViewModel: ObjectDetailsViewModel = hiltViewModel()
) {
    remember { objectDetailsViewModel.getMuseumObjectById() }
    val state = objectDetailsViewModel.museumObject.collectAsState()
    when (state.value) {
        is RequestState.Error -> {
            Toast.makeText(
                LocalContext.current,
                (state.value as RequestState.Error).message,
                Toast.LENGTH_SHORT
            ).show()
        }

        RequestState.Loading -> {
            Box(modifier = modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
        }

        is RequestState.Success -> {
            MuseumObjectView(
                modifier = Modifier,
                museumObject = (state.value as RequestState.Success).museumObject
            )
        }
    }
}
