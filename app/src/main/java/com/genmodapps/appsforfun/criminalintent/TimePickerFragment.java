package com.genmodapps.appsforfun.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;



public class TimePickerFragment extends DialogFragment {

    public static final String EXTRA_TIME =
            "com.genmodapps.appsforfun.criminalintent.time";

    public static final String ARG_TIME = "time";

    private TimePicker timePicker;

    public static TimePickerFragment newInstance(Date time){
        Bundle args = new Bundle();
        args.putSerializable(ARG_TIME, time);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Date time = (Date) getArguments().getSerializable(ARG_TIME);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_timepicker, null);

        timePicker = v.findViewById(R.id.dialog_time_picker);
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);

        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.time_picker_title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int hour = timePicker.getCurrentHour();
                        int minute = timePicker.getCurrentMinute();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(time);
                        calendar.set(Calendar.HOUR_OF_DAY, hour);
                        calendar.set(Calendar.MINUTE, minute);
                        Date time = calendar.getTime();
                        sendResult(Activity.RESULT_OK, time);
                    }
                })
                .create();


    }

    private void sendResult(int resultCode, Date time){
        if(getTargetFragment() == null){
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(EXTRA_TIME, time);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }
}