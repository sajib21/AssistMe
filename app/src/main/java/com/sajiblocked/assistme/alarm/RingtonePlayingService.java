package com.sajiblocked.assistme.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.sajiblocked.assistme.R;

/**
 * Created by Mahidul Islam Misbah.
 */

public class RingtonePlayingService extends Service {
    MediaPlayer play_song;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        play_song = MediaPlayer.create(this , R.raw.m);
        play_song.start();
        return START_NOT_STICKY;
    }
}