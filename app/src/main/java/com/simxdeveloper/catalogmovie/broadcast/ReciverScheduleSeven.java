package com.simxdeveloper.catalogmovie.broadcast;

import static com.simxdeveloper.catalogmovie.Apps.getContext;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.InboxStyle;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.simxdeveloper.catalogmovie.Apps;
import com.simxdeveloper.catalogmovie.R;
import com.simxdeveloper.catalogmovie.data.repo.ObservableHelper;
import com.simxdeveloper.catalogmovie.data.repo.model.global.ResultsItem;
import com.simxdeveloper.catalogmovie.helper.Const;
import com.simxdeveloper.catalogmovie.preference.AlaramPreference;
import com.simxdeveloper.catalogmovie.preference.PrefKey;
import com.simxdeveloper.catalogmovie.ui.detail.DetailActivity;
import com.simxdeveloper.catalogmovie.ui.home.HomeActivity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ExecutionException;

/**
 * User: simx Date: 01/06/18 20:00
 */
public class ReciverScheduleSeven extends BroadcastReceiver{
  private static String TAG = ReciverScheduleSeven.class.getSimpleName ();
  public static String NOTIFICATION_ID = "notification-id";
  public static int ID;
  public final static int NOTIF_ID_REPEATING_REMAINDER_DAY = 101;
  public final static int NOTIF_ID_REPEATING_UPDATE = 102;
  long[] pattern = {500,500,500,500,500,500,500,500,500};
  public ReciverScheduleSeven () {
  }

  @Override
  public void onReceive (Context context, Intent intent) {
    ID = intent.getIntExtra (NOTIFICATION_ID,100);
    switch (ID) {
      case NOTIF_ID_REPEATING_REMAINDER_DAY:
        if (AlaramPreference.read (PrefKey.IS_ALARAM_AT_7,Boolean.class)){
          showNotifRemainder ();
        }
        break;
      case NOTIF_ID_REPEATING_UPDATE:
        if (AlaramPreference.read (PrefKey.IS_ALARAM_AT_7,Boolean.class)) {
          ObservableHelper.nowPlayingObservable ().subscribe (
              responseNowPlaying -> {
                if (VERSION.SDK_INT >= VERSION_CODES.N) {
                  responseNowPlaying.getResults ().forEach (resultsItem -> {
                    Log.e ("ReciverScheduleSeven",
                        "onReceive: " + isEqualeReleaseDate (resultsItem.getReleaseDate ()));
                    if (isEqualeReleaseDate (resultsItem.getReleaseDate ())){
                      showNotification (resultsItem);
                    }
                    // Enable code belowe for testing
                    /*if (resultsItem.getReleaseDate ().equalsIgnoreCase ("2018-05-15")){
                      showNotification (resultsItem);
                    }*/
                  });
                }else {
                  for (int i = 0; i < responseNowPlaying.getResults ().size (); i++) {
                    Log.e ("ReciverScheduleSeven", "onReceive: " + isEqualeReleaseDate (
                        responseNowPlaying.getResults ().get (i).getReleaseDate ()));
                    if (isEqualeReleaseDate (responseNowPlaying.getResults ().get (i).getReleaseDate ())){
                      showNotification (responseNowPlaying.getResults ().get (i));
                    }
                    // Enable code belowe for testing
                    /*if (responseNowPlaying.getResults ().get (i).getReleaseDate ().equalsIgnoreCase ("2018-05-15")){
                      showNotification (responseNowPlaying.getResults ().get (i));
                    }*/
                  }
                }
              }
          );
        }
        break;
    }

  }
  private void showNotification(ResultsItem resultsItem){
    Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    Intent intent = new Intent(getContext (), DetailActivity.class);
    intent.putExtra (DetailActivity.KEY_DETAIL,resultsItem);
    PendingIntent pendingIntent = PendingIntent.getActivity(getContext (), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationChannel channel = new NotificationChannel(TAG, resultsItem.getTitle (), NotificationManager.IMPORTANCE_LOW);
      channel.setDescription("Hari ini "+ resultsItem.getOriginalTitle () + " release");
      channel.setVibrationPattern (pattern);
      channel.setShowBadge (true);
      getContext().getSystemService(NotificationManager.class).createNotificationChannel(channel);
    }
    Notification notification = new NotificationCompat.Builder(getContext(), TAG)
        .setContentTitle(resultsItem.getTitle ())
        .setContentText(resultsItem.getOverview ())
        .setAutoCancel(true)
        .setChannelId(TAG)
        .setVibrate (pattern)
        .setSound (alarmSound)
        .setContentIntent (pendingIntent)
        .setStyle (new InboxStyle ().setSummaryText ("Hari ini "+ resultsItem.getOverview () + " release").setBigContentTitle (
            resultsItem.getTitle ()).addLine (resultsItem.getOverview ()))
        .setPriority (NotificationManager.IMPORTANCE_HIGH)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setShowWhen(true)
        .setColor(Color.GREEN)
        .setLocalOnly(true)
        .build();

    NotificationManagerCompat.from(getContext()).notify(new Random ().nextInt(), notification);
  }
  private void showNotifRemainder(){
    Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
    Intent intent = new Intent(getContext (), HomeActivity.class);
    PendingIntent pendingIntent = PendingIntent.getActivity(getContext (), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationChannel channel = new NotificationChannel(TAG, Apps.getContext ().getResources ().getString (R.string.app_name), NotificationManager.IMPORTANCE_LOW);
      channel.setDescription(Apps.getContext ().getResources ().getString (R.string.app_name) + " Missing you.. ");
      channel.setVibrationPattern (pattern);
      getContext().getSystemService(NotificationManager.class).createNotificationChannel(channel);
    }
    Notification notification = new NotificationCompat.Builder(getContext(), TAG)
        .setContentTitle(Apps.getContext ().getResources ().getString (R.string.app_name))
        .setContentText(Apps.getContext ().getResources ().getString (R.string.app_name) + " Missing you.. ")
        .setAutoCancel(true)
        .setChannelId(TAG)
        .setVibrate (pattern)
        .setSound (alarmSound)
        .setContentIntent (pendingIntent)
        .setStyle (new InboxStyle ())
        .setPriority (NotificationManager.IMPORTANCE_HIGH)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setShowWhen(true)
        .setColor(Color.GREEN)
        .setLocalOnly(true)
        .build();

    NotificationManagerCompat.from(getContext()).notify(new Random ().nextInt(), notification);
  }

  public void setRepeatingAlarm(Context context, int hour, int minut, int id){
    AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(context, ReciverScheduleSeven.class);
    intent.putExtra(NOTIFICATION_ID, id);
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, hour);
    calendar.set(Calendar.MINUTE, minut);
    calendar.set(Calendar.SECOND, 0);

    PendingIntent pendingIntent =  PendingIntent.getBroadcast(context,
        id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    Toast.makeText(context, "Repeating alarm set up", Toast.LENGTH_SHORT).show();
  }
  public void cancelAlarm(Context context,int requestCode){
    AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(context, ReciverScheduleSeven.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0);
    alarmManager.cancel(pendingIntent);

    Toast.makeText(context, "Repeating alarm dibatalkan", Toast.LENGTH_SHORT).show();
  }

  private boolean isEqualeReleaseDate(String releaseDate){
    DateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    String curentDate = sdfDate.format (System.currentTimeMillis ());
    if (releaseDate.equalsIgnoreCase (curentDate)){
      return true;
    }else {
      return false;
    }
  }
  private Bitmap createBitmap(String path, Context context){
    Bitmap bmp = null;
    try {
      bmp = Glide.with(context)
          .asBitmap ()
          .load(Const.IMAGE_PATH + path)
          .submit (Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();

    }catch (InterruptedException | ExecutionException e){
      Log.d("Widget Load Error","error");
    }
    return bmp;
  }
}
