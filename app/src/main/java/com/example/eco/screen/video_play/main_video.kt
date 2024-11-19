package com.example.eco.screen.video_play

import android.content.Context
import androidx.annotation.OptIn
import androidx.compose.foundation.background
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
import com.example.eco.R

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayerFromUrl(
    context: Context,
    videoUrl: String,
    isFullScreen: Boolean,
    onToggleFullScreen: () -> Unit
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

    // Video Player Layout
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .then(if (isFullScreen) Modifier.fillMaxHeight() else Modifier.aspectRatio(16f / 9f))
            .background(Color.Black)
    ) {
        // AndroidView to display ExoPlayer's PlayerView
        AndroidView(
            factory = { ctx ->
                PlayerView(ctx).apply {
                    player = exoPlayer
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        // Bottom Bar with Expand/Collapse Button
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.6f)) // Semi-transparent background
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                // Expand/Collapse Button
                IconButton(onClick = { onToggleFullScreen() }) {
                    Icon(
                        imageVector = if (isFullScreen) Icons.Filled.FullscreenExit else Icons.Filled.Fullscreen,
                        contentDescription = if (isFullScreen) "Exit Full Screen" else "Full Screen",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun MyCoursesScreen() {
    val context = LocalContext.current

    // State to manage full-screen mode
    var isFullScreen by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            if (!isFullScreen) { // Hide the top bar in full-screen mode
                TopAppBar(
                    title = { Text("My Courses", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
                    backgroundColor = Color.White,
                    elevation = 0.dp
                )
            }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color(0xFFF6F6F6)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Video Player
                VideoPlayerFromUrl(
                    context = context,
                    videoUrl = "https://firebasestorage.googleapis.com/v0/b/clouds-caafc.appspot.com/o/raw_sample.mp4?alt=media&token=b6d74115-7bb6-4dcb-9ab2-02a7fa0baa8a",
                    isFullScreen = isFullScreen,
                    onToggleFullScreen = { isFullScreen = !isFullScreen }
                )

                if (!isFullScreen) {
                    // Optional text to show video title or description
                    Text(
                        text = "Sample Video",
                        modifier = Modifier.padding(16.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MyCoursesScreenPreview() {
    MyCoursesScreen()
}

