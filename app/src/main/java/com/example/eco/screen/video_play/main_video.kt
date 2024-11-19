package com.example.eco.screen.video_play

import android.content.Context
import androidx.annotation.OptIn
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.FullscreenExit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView


import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ClosedCaption
import androidx.compose.material.icons.filled.FastForward
import androidx.compose.material.icons.filled.FastRewind
import androidx.media3.common.Player

import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.VolumeOff
import androidx.compose.material.icons.filled.VolumeUp
import kotlinx.coroutines.delay


import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Slider

import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.graphicsLayer

@OptIn(UnstableApi::class)
@Composable
fun JetpackComposeVideoPlayerWithAnimatedBlur(
    context: Context,
    videoUrl: String
) {
    // Initialize ExoPlayer
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(videoUrl)
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = false
        }
    }

    // Cleanup ExoPlayer when composable is disposed
    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer.release()
        }
    }

    // State for playback control
    var isPlaying by remember { mutableStateOf(false) }
    var currentTime by remember { mutableStateOf(0L) }
    var duration by remember { mutableStateOf(0L) }
    var showUI by remember { mutableStateOf(true) }

    // Blur animation state
    val blurRadius by animateDpAsState(targetValue = if (showUI) 20.dp else 0.dp)

    // Observing playback progress
    LaunchedEffect(exoPlayer) {
        exoPlayer.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                if (state == Player.STATE_READY) {
                    duration = exoPlayer.duration
                }
            }
        })

        while (true) {
            currentTime = exoPlayer.currentPosition
            delay(1000)
        }
    }

    // Auto-hide UI logic
    LaunchedEffect(showUI) {
        if (showUI) {
            delay(3000) // Hide UI after 3 seconds
            showUI = false
        }
    }

    // Video Player Layout
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
            .background(Color.Black)
            .clickable {
                showUI = true // Show UI on user interaction
            }
    ) {
        // PlayerView with blur effect
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                    useController = false // Disable default controls
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .blur(blurRadius) // Apply animated blur
        )

        // Center Playback Controls (Play, Pause, Skip)
        if (showUI) {
            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Skip Back Button
                IconButton(
                    onClick = {
                        exoPlayer.seekTo((exoPlayer.currentPosition - 10000).coerceAtLeast(0)) // Skip back 10 seconds
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.FastRewind,
                        contentDescription = "Skip Back 10 Seconds",
                        tint = Color.White,
                        modifier = Modifier.size(48.dp)
                    )
                }

                // Play/Pause Button
                IconButton(
                    onClick = {
                        if (isPlaying) {
                            exoPlayer.pause()
                        } else {
                            exoPlayer.play()
                        }
                        isPlaying = !isPlaying
                    }
                ) {
                    Icon(
                        imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = if (isPlaying) "Pause" else "Play",
                        tint = Color.White,
                        modifier = Modifier.size(64.dp)
                    )
                }

                // Skip Forward Button
                IconButton(
                    onClick = {
                        exoPlayer.seekTo((exoPlayer.currentPosition + 10000).coerceAtMost(duration)) // Skip forward 10 seconds
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.FastForward,
                        contentDescription = "Skip Forward 10 Seconds",
                        tint = Color.White,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }

        // Bottom Controls: Time Display and Progress Bar
        if (showUI) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                // Slider (Progress Bar)
                Slider(
                    value = if (duration > 0) currentTime.toFloat() / duration else 0f,
                    onValueChange = { value ->
                        exoPlayer.seekTo((value * duration).toLong()) // Seek to the new position
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    colors = SliderDefaults.colors(
                        thumbColor = Color.Red,
                        activeTrackColor = Color.Red,
                        inactiveTrackColor = Color.Gray
                    )
                )

                // Time Display (Above the Progress Bar)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopStart)
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Current Time
                    Text(
                        text = "${formatTime(currentTime)} / ${formatTime(duration)}",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

// Helper function to format time in mm:ss
fun formatTime(milliseconds: Long): String {
    val totalSeconds = milliseconds / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}

@Composable
fun MyCoursesScreen() {
    val context = LocalContext.current

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
                // Custom Video Player
                JetpackComposeVideoPlayerWithAnimatedBlur(
                    context = context,
                    videoUrl = "https://firebasestorage.googleapis.com/v0/b/clouds-caafc.appspot.com/o/raw_sample.mp4?alt=media&token=b6d74115-7bb6-4dcb-9ab2-02a7fa0baa8a"
                )
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun MyCoursesScreenPreview() {
    MyCoursesScreen()
}
