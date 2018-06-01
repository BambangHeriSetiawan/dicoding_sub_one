package com.simxdeveloper.catalogmovie.schedule;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import com.evernote.android.job.DailyJob;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;
import com.evernote.android.job.JobRequest.Builder;
import com.simxdeveloper.catalogmovie.R;
import com.simxdeveloper.catalogmovie.ui.home.HomeActivity;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * User: simx Date: 01/06/18 3:25
 */
public class DailyJobEight extends DailyJob {
  public static final String TAG =DailyJobEight.class.getName ();
  private static void scedule(){
    if (!JobManager.instance ().getAllJobRequestsForTag (TAG).isEmpty ()){return;}
    JobRequest.Builder builder = new Builder (TAG);
    DailyJob.schedule (builder, TimeUnit.HOURS.toMillis (7),TimeUnit.HOURS.toMillis (7));
  }

  @NonNull
  @Override
  protected DailyJobResult onRunDailyJob (@NonNull Params params) {

    PendingIntent pendingIntent = PendingIntent
        .getActivity(getContext(), 0, new Intent (getContext(), HomeActivity.class), 0);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationChannel channel = new NotificationChannel(TAG, "Job Demo Eaght", NotificationManager.IMPORTANCE_LOW);
      channel.setDescription("Job demo job");
      getContext().getSystemService(NotificationManager.class).createNotificationChannel(channel);
    }

    Notification notification = new NotificationCompat.Builder(getContext(), TAG)
        .setContentTitle("ID " + params.getId())
        .setContentText("Job ran, exact " + params.isExact() + " , periodic " + params.isPeriodic() + ", transient " + params.isTransient())
        .setAutoCancel(true)
        .setChannelId(TAG)
        .setSound(null)
        .setContentIntent(pendingIntent)
        .setSmallIcon(R.mipmap.ic_launcher)
        .setShowWhen(true)
        .setColor(Color.GREEN)
        .setLocalOnly(true)
        .build();

    NotificationManagerCompat.from(getContext()).notify(new Random ().nextInt(), notification);

    return DailyJobResult.SUCCESS;
  }
}
