package com.example.metropolitanmuseumapp.presentation.museumobject

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.metropolitanmuseumapp.R
import com.example.metropolitanmuseumapp.entity.MuseumObject
import com.example.metropolitanmuseumapp.ui.theme.Gray70

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MuseumObjectView(
    museumObject: MuseumObject,
    modifier: Modifier
) {
    val padding = 20.dp
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.spacedBy(padding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier.fillMaxWidth()
        ) {
            GlideImage(
                modifier = modifier.fillMaxWidth(),
                model = null,
                contentDescription = null
            ) {
                it.load(museumObject.primaryImageSmall).centerCrop()
            }
            val currentContext = LocalContext.current
            BackButton(
                modifier = modifier
                    .align(Alignment.TopStart)
                    .padding(padding)
            ) {
                Toast.makeText(
                    currentContext,
                    currentContext.getString(R.string.click_back_button_toast),
                    Toast.LENGTH_SHORT
                ).show()
            }
            ResizeButton(
                modifier = modifier
                    .align(Alignment.TopEnd)
                    .padding(padding)
            ) {
                Toast.makeText(
                    currentContext,
                    currentContext.getString(R.string.click_resize_button_toast),
                    Toast.LENGTH_SHORT
                ).show()
            }

            Column(
                modifier = modifier
                    .align(Alignment.BottomStart)
                    .padding(start = padding, end = padding, bottom = padding),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                if (museumObject.title.isNotBlank()) TitleTextUpperCase(text = museumObject.title)
                if (museumObject.objectBeginDate != null && museumObject.objectEndDate != null) {
                    DataText(text = "${museumObject.objectBeginDate} - ${museumObject.objectEndDate}")
                }
            }
        }
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = padding),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (museumObject.artistDisplayName.isNotEmpty()) {
                TextGroup(
                    title = stringResource(R.string.artist_title),
                    text = "${museumObject.artistDisplayName} (${museumObject.artistRole}," +
                            " ${museumObject.artistDisplayBio})"
                )
            }

            if (museumObject.department.isNotBlank()) {
                TextGroup(
                    title = stringResource(R.string.department_title),
                    text = museumObject.department
                )
            }

            if (museumObject.medium.isNotBlank()) {
                TextGroup(
                    title = stringResource(R.string.medium_title),
                    text = museumObject.medium
                )
            }

            if (museumObject.dimensions.isNotBlank()) {
                TextGroup(
                    title = stringResource(R.string.dimensions_title),
                    text = museumObject.dimensions
                )
            }

        }
    }
}

@Composable
fun TextGroup(title: String, text: String) {
    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        TitleTextUpperCase(title.uppercase())
        DataText(text)
    }
}

@Composable
fun TitleTextUpperCase(text: String) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 20.sp
    )
}

@Composable
fun DataText(text: String) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 12.sp
    )
}

@Composable
fun ResizeButton(modifier: Modifier, onclick: () -> Unit) {
    ActionButton(
        modifier = modifier,
        id = R.drawable.pan_zoom_24dp_ffffff_fill0_wght400_grad0_opsz24_1,
        onclick = onclick
    )
}

@Composable
fun BackButton(modifier: Modifier, onclick: () -> Unit) {
    ActionButton(
        modifier = modifier,
        id = R.drawable.arrow_back_24dp_ffffff_fill0_wght400_grad0_opsz24_1,
        onclick = onclick
    )
}

@Composable
fun ActionButton(modifier: Modifier, id: Int, onclick: () -> Unit) {
    IconButton(
        modifier = modifier,
        onClick = onclick,
        colors = IconButtonColors(
            containerColor = Gray70,
            contentColor = Color.White,
            disabledContainerColor = Gray70,
            disabledContentColor = Color.White
        )
    ) {
        Icon(
            painter = painterResource(id),
            contentDescription = null
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewViewContainer() {
    MuseumObjectView(
        MuseumObject(
            objectID = 45734,
            objectName = "Hanging scroll",
            title = "Quail and Millet",
            objectBeginDate = 1667,
            objectEndDate = 1682,
            primaryImageSmall = "https://images.metmuseum.org/CRDImages/as/web-large/DP251139.jpg",
            artistRole = "Artist",
            artistDisplayName = "Kiyohara Yukinobu",
            artistDisplayBio = "Japanese, 1643–1682",
            department = "Asian Art",
            culture = "Japan",
            period = "Edo period (1615–1868)",
            medium = "Hanging scroll; ink and color on silk",
            dimensions = "46 5/8 x 18 3/4 in. (118.4 x 47.6 cm)"
        ),
        Modifier
    )
}
