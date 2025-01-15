package com.example.metropolitanmuseumapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.metropolitanmuseumapp.presentation.museumobject.MuseumObjectContainer
import com.example.metropolitanmuseumapp.ui.theme.MetropolitanMuseumAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MetropolitanMuseumAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MuseumObjectContainer(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
