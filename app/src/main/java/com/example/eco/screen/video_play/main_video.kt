package com.example.eco.screen.video_play


import android.content.Context
import androidx.annotation.OptIn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.ui.PlayerView
import com.example.eco.R

@Composable
fun MyCoursesScreen(context: Context) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Courses", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                backgroundColor = Color.White,
                elevation = 0.dp
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFFF6F6F6)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Video Player Section
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f) // Keeps the container proportional
                        .background(
                            color = Color(0xFFEDEAFF),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    VideoPlayerFromResource(context = context, resId = R.raw.raw_sample)
                }

                // Progress Bar Section
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .background(
                            color = Color(0xFFD9E8FF),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "08:25 / 20:00",
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.high_volume),
                        contentDescription = "Volume",
                        modifier = Modifier.size(24.dp) // Resize the icon
                    )
                }
            }
        }
    )
}

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayerFromResource(context: Context, resId: Int) {
    // Build the URI for the raw resource
    val videoUri = RawResourceDataSource.buildRawResourceUri(resId)

    // Initialize ExoPlayer
    val exoPlayer = ExoPlayer.Builder(context).build().apply {
        val mediaItem = MediaItem.fromUri(videoUri)
        setMediaItem(mediaItem)
        prepare()
        playWhenReady = false
    }

    // AndroidView to display ExoPlayer's PlayerView
    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).apply {
                player = exoPlayer
            }
        },
        modifier = Modifier
            .fillMaxSize() // Ensures video fills the container
    )
}

@Preview (showBackground = true)
@Composable
fun MyCoursesActivity() {
    val context = LocalContext.current // Get the current context
    MyCoursesScreen(context = context)
}