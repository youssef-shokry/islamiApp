package com.route.islamie_app101.ui.application_screens.radio_fragments.services

import android.content.Intent
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import com.route.islamie_app101.utils.Constants.Companion.ACTION_PLAY_URL
import com.route.islamie_app101.utils.Constants.Companion.RADIO_STREAM_URL

class RadioService : MediaSessionService() {
    private var player: ExoPlayer? = null
    private var mediaSession: MediaSession? = null

    override fun onCreate() {
        super.onCreate()
        player = ExoPlayer.Builder(this).build()
        mediaSession = MediaSession.Builder(this, player!!).build()
    }

    override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession? {
        return mediaSession
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == ACTION_PLAY_URL){
            val url = intent.getStringExtra(RADIO_STREAM_URL)
            if (!url.isNullOrEmpty()) playerUrl(url)
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun playerUrl(url: String) {
        player?.apply {
            stop()
            clearMediaItems()
            setMediaItem(MediaItem.fromUri(url))
            prepare()
            playWhenReady = true
        }
    }

    override fun onDestroy() {
        mediaSession.run {
            player?.release()
            mediaSession = null
        }
        player = null
        super.onDestroy()
    }
}