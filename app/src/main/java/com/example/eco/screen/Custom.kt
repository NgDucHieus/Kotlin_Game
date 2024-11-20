package com.example.eco.screen
import Fruit
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.eco.R

@Composable
fun StaggeredVerticalGameUI() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Positioned Items
        Box(
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.TopCenter)
                .offset(y = 40.dp) // Adjust top circle position
                .background(Color.Yellow, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Check, // Completed icon
                contentDescription = "Completed",
                tint = Color.White,
                modifier = Modifier.size(40.dp)
            )
        }

        Box(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.Center)
                .offset(x = -30.dp) // Staggered to the left
                .background(Color.Green, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "START",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Box(
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.BottomCenter)
                .offset(x = 30.dp, y = -100.dp) // Staggered to the right
                .background(Color.LightGray, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Lock, // Locked icon
                contentDescription = "Locked",
                tint = Color.Gray,
                modifier = Modifier.size(40.dp)
            )
        }

        Box(
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.BottomCenter)
                .offset(y = -40.dp) // Aligned centrally, staggered lower
                .background(Color.LightGray, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Lock, // Locked icon
                contentDescription = "Locked",
                tint = Color.Gray,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}


@Preview
@Composable
fun Pre()
{
    StaggeredVerticalGameUI()
}