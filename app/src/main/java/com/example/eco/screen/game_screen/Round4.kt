package com.example.eco.screen.game_screen

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument

data class VideoEpisode(
    val id: Int,
    val title: String,
    val videoUrl: String,
    var isWatched: Boolean = false
)

@Composable
fun MainApp() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val episodes = remember {
        mutableStateListOf(
            VideoEpisode(
                id = 1,
                title = "Tập 1: Quả Cam",
                videoUrl = "https://firebasestorage.googleapis.com/v0/b/clouds-caafc.appspot.com/o/raw_sample.mp4?alt=media&token=b6d74115-7bb6-4dcb-9ab2-02a7fa0baa8a"
            ),
            VideoEpisode(
                id = 2,
                title = "Tập 2: Quả Táo",
                videoUrl = "https://firebasestorage.googleapis.com/v0/b/clouds-caafc.appspot.com/o/raw_sample.mp4?alt=media&token=b6d74115-7bb6-4dcb-9ab2-02a7fa0baa8a"
            )
        )
    }

    NavHost(navController = navController, startDestination = "video_list") {
        composable("video_list") {
            VideoListScreen(
                episodes = episodes,
                onEpisodeSelected = { episode ->
                    navController.navigate("video_player/${episode.id}")
                }
            )
        }

        composable(
            route = "video_player/{episodeId}",
            arguments = listOf(navArgument("episodeId") { type = NavType.IntType })
        ) { backStackEntry ->
            val episodeId = backStackEntry.arguments?.getInt("episodeId")
            val episode = episodes.first { it.id == episodeId }
            VideoPlayerScreen(
                context = context,
                episode = episode,
                onComplete = {
                    episode.isWatched = true
                    navController.popBackStack()
                    if (episodes.all { it.isWatched }) {
                        navController.navigate("special_episode")
                    } else {
                        navController.navigate("survey_form")
                    }
                }
            )
        }

        composable("survey_form") {
            SurveyFormScreen(
                onSubmit = { navController.popBackStack() }
            )
        }

        composable("special_episode") {
            SpecialEpisodeScreen()
        }
    }
}

@Composable
fun VideoListScreen(
    episodes: List<VideoEpisode>,
    onEpisodeSelected: (VideoEpisode) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Thông điệp cuộc sống") },
                backgroundColor = Color(0xFF6200EE),
                contentColor = Color.White
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(episodes) { episode -> // Correct usage
                EpisodeItem(
                    episode = episode,
                    onClick = { onEpisodeSelected(episode) }
                )
            }
        }
    }
}


@Composable
fun EpisodeItem(episode: VideoEpisode, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = episode.title,
                fontSize = 18.sp,
                color = if (episode.isWatched) Color.Gray else Color.Black,
                modifier = Modifier.weight(1f)
            )
            if (episode.isWatched) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Watched",
                    tint = Color.Green
                )
            }
        }
    }
}

@Composable
fun VideoPlayerScreen(
    context: Context,
    episode: VideoEpisode,
    onComplete: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(episode.title) },
                backgroundColor = Color(0xFF6200EE),
                contentColor = Color.White
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            VideoPlayer(
                context = context,
                videoUrl = episode.videoUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                onComplete = onComplete
            )
        }
    }
}

@Composable
fun SurveyFormScreen(onSubmit: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Feedback Form") },
                backgroundColor = Color(0xFF6200EE),
                contentColor = Color.White
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Please fill out the feedback form!", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { onSubmit() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Submit Feedback")
            }
        }
    }
}

@Composable
fun SpecialEpisodeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tập ĐẶC BIỆT") },
                backgroundColor = Color(0xFF6200EE),
                contentColor = Color.White
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Giáo dục đạo đức tài chính khi đi mua hàng",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { /* Handle action */ }) {
                Text(text = "Learn More")
            }
        }
    }
}

@Composable
fun VideoPlayer(
    context: Context,
    videoUrl: String,
    modifier: Modifier = Modifier,
    onComplete: () -> Unit = {}
) {
    val exoPlayer = ExoPlayer.Builder(context).build().apply {
        setMediaItem(MediaItem.fromUri(Uri.parse(videoUrl)))
        prepare()
        playWhenReady = true

        addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_ENDED) {
                    onComplete()
                }
            }
        })
    }

    DisposableEffect(exoPlayer) {
        onDispose { exoPlayer.release() }
    }

    Box(modifier = modifier) {
        AndroidView(
            modifier = Modifier.matchParentSize(),
            factory = { PlayerView(context).apply { player = exoPlayer } }
        )
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewMainApp() {
    MainApp()
}
