package com.sajiblocked.assistme.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.widget.Toast;

/**
 * Created by Mahidul Islam Misbah.
 */

public class Alarm_manager extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Alarm is on",Toast.LENGTH_LONG).show();
        Intent ring_intent = new Intent(context, RingtonePlayingService.class);
        context.startService(ring_intent);
        Vibrator vibrator = (Vibrator)context.getSystemService(context.VIBRATOR_SERVICE);
        vibrator.vibrate(3000);
    }
}
