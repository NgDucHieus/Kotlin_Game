package com.example.eco.screen.phase2


import android.content.Context
import android.media.MediaPlayer
import com.example.eco.R

fun playAudioGuide(context: Context) {
    val mediaPlayer = MediaPlayer.create(context, R.raw.instruction_audio) // Replace R.raw.audio_guide with your audio file
    mediaPlayer.start()
}