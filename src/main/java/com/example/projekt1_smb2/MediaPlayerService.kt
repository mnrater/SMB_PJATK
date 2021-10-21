package com.example.projekt1_smb2

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.media.MediaPlayer.*
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import java.io.IOException

class MediaPlayerService : Service(), OnCompletionListener {
    private val iBinder: IBinder = LocalBinder()
    private lateinit var mediaPlayer: MediaPlayer
    private var songId = 1

    private fun setSong(songId: Int) {
        mediaPlayer.reset()
        val assetFileDescriptor = resources.openRawResourceFd(resources.getIdentifier("song$songId", "raw", packageName))
        try {
            mediaPlayer.setDataSource(
                    assetFileDescriptor.fileDescriptor,
                    assetFileDescriptor.startOffset,
                    assetFileDescriptor.length
            )
            assetFileDescriptor.close()
            mediaPlayer.prepare()
            Log.d("setSong", "You set a song!")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun initMediaPlayer() {
        mediaPlayer = MediaPlayer()
        mediaPlayer.setOnCompletionListener(this)
        setSong(songId)
    }

    private fun play() {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
            Log.d("play", "You played a song! New SongId$songId")
        }
    }

    private fun pause() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            Log.d("pause", "You paused a song!")
        }
    }

    private fun stop() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            setSong(songId)
            Log.d("stop", "You stoped a song!")
        }
    }

    private fun back() {
        if (songId > 1) {
            songId = songId.minus(1)
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
            setSong(songId)
            mediaPlayer.start()
            Log.d("back", "You changed your song to previous one! New SongId$songId")
        }
        else
            songId = songId.plus(1)
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
            setSong(songId)
            mediaPlayer.start()
            Log.d("return1", "You returned to old song! New SongId$songId")
    }

    private fun skip() {
        if (songId < 2) {
            songId = songId.plus(1)
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
            setSong(songId)
            mediaPlayer.start()
            Log.d("skip", "You skiped a song! New SongId$songId")
        }
        else
            songId = songId.minus(1)
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
            setSong(songId)
            mediaPlayer.start()
            Log.d("return", "You returned to old song! New SongId$songId")
    }

    private val audioPlayer: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == null) return
            when (intent.getStringExtra("button")) {
                applicationContext.getString(R.string.actionBtPlay) -> play()
                applicationContext.getString(R.string.actionBtPause) -> pause()
                applicationContext.getString(R.string.actionBtNext) -> skip()
                applicationContext.getString(R.string.actionBtPrevious) -> back()
                applicationContext.getString(R.string.actionBtStop) -> stop()
            }
            Log.d("broadCastReceiver", "broadCastReceiver")
        }
    }

    private fun registerAudioPlayer() {
        val filter = IntentFilter(getString(R.string.broadcast_test))
        registerReceiver(audioPlayer, filter)
        Log.d("broadCastReceivers", "broadCastReceiverxDDDD")
    }

    override fun onBind(intent: Intent?): IBinder {
        return iBinder
    }

    override fun onCreate() {
        super.onCreate()
        registerAudioPlayer()
        Log.d("onCreate", "onCreate")
    }

    override fun onCompletion(mp: MediaPlayer?) {
        mp?.stop()
        setSong(songId)
        Log.d("onCompletion", "onCompletion")
    }

    override fun onDestroy() {
        super.onDestroy()
        stop()
        mediaPlayer.release()
        unregisterReceiver(audioPlayer)
        Log.d("onDestroy", "onDestroy")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        initMediaPlayer()
        Log.d("onStartCommand", "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    inner class LocalBinder : Binder() {
        val service
            get() = this@MediaPlayerService
    }
}