package com.simxdeveloper.catalogmovie.helper;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.text.format.DateFormat;
import java.util.Calendar;

/**
 * User: simx Date: 01/06/18 23:02
 */
@SuppressLint("ValidFragment")
public class TimePickerDialogFragment extends DialogFragment {
  private TimePickerDialog.OnTimeSetListener onTimeSetListener;

  @SuppressLint("ValidFragment")
  public TimePickerDialogFragment (OnTimeSetListener onTimeSetListener) {
    this.onTimeSetListener = onTimeSetListener;
  }

  @Override
  public Dialog onCreateDialog (Bundle savedInstanceState) {
    final Calendar c = Calendar.getInstance();
    int hour = c.get(Calendar.HOUR);
    int minute = c.get(Calendar.MINUTE);

    return new TimePickerDialog(getActivity(), onTimeSetListener, hour, minute,
        DateFormat.is24HourFormat(getActivity()));
  }
}
