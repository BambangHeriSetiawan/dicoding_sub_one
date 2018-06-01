package com.simxdeveloper.catalogmovie.broadcast;

import static android.support.constraint.Constraints.TAG;
import static com.simxdeveloper.catalogmovie.Apps.getContext;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import com.simxdeveloper.catalogmovie.R;
import java.util.Random;

/**
 * User: simx Date: 01/06/18 20:00
 */
public class NotificationPublisher extends BroadcastReceiver{
  public static String NOTIFICATION_ID = "notification-id";
  public static String NOTIFICATION = "notification";

  @Override
  public void onReceive (Context context, Intent intent) {
    Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    long[] pattern = {500,500,500,500,500,500,500,500,500};
    Log.e ("NotificationPublisher", "onReceive: " );
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationChannel channel = new NotificationChannel(TAG, "Job Demo Seven", NotificationManager.IMPORTANCE_LOW);
      channel.setDescription("Job demo job");
      channel.setVibrationPattern (pattern);
      getContext().getSystemService(NotificationManager.class).createNotificationChannel(channel);
    }
    Notification notification = new NotificationCompat.Builder(getContext(), TAG)
        .setContentTitle("ID ")
        .setContentText("Job ran, exact ")
        .setAutoCancel(true)
        .setChannelId(TAG)
        .setVibrate (pattern)
        .setSound (alarmSound)
        .setPriority (NotificationManager.IMPORTANCE_HIGH)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setShowWhen(true)
        .setColor(Color.GREEN)
        .setLocalOnly(true)
        .build();

    NotificationManagerCompat.from(getContext()).notify(new Random ().nextInt(), notification);
  }
}
