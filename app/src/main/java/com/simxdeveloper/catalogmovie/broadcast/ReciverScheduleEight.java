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
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import com.simxdeveloper.catalogmovie.R;
import com.simxdeveloper.catalogmovie.data.repo.ObservableHelper;
import com.simxdeveloper.catalogmovie.data.repo.model.global.ResultsItem;
import java.util.Random;
import java.util.function.Consumer;

/**
 * User: simx Date: 01/06/18 20:00
 */
public class ReciverScheduleEight extends BroadcastReceiver{
  public static String NOTIFICATION_ID = "notification-id-8";
  @Override
  public void onReceive (Context context, Intent intent) {
    ObservableHelper.nowPlayingObservable ().subscribe (
        responseNowPlaying -> {
          if (VERSION.SDK_INT >= VERSION_CODES.N) {
            responseNowPlaying.getResults ().forEach (resultsItem -> {
              showNotification (resultsItem.getTitle ());
            });
          }else {
            for (int i = 0; i < responseNowPlaying.getResults ().size (); i++) {
              showNotification (responseNowPlaying.getResults ().get (i).getTitle ());
            }
          }
        }
    );
  }
  private void showNotification(String title){
    Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    long[] pattern = {500,500,500,500,500,500,500,500,500};
    Log.e ("ReciverScheduleSeven", "onReceive: " );
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationChannel channel = new NotificationChannel(TAG, title, NotificationManager.IMPORTANCE_LOW);
      channel.setDescription("Hari ini "+ title + " release");
      channel.setVibrationPattern (pattern);
      getContext().getSystemService(NotificationManager.class).createNotificationChannel(channel);
    }
    Notification notification = new NotificationCompat.Builder(getContext(), TAG)
        .setContentTitle(title)
        .setContentText("Hari ini "+ title + " release")
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
