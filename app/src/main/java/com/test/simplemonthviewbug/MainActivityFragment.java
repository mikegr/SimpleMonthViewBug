package com.test.simplemonthviewbug;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        view.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalendar(0, 0, 0, 0);
            }
        });

        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                showCalendar(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE) - 5, 0, 0);
            }
        });


        view.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                showCalendar(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE) + 5, 0, 0);
            }
        });
        view.findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showCalendar(23, 59, 59, 999);
            }
        });

        return view;

    }

    public void showCalendar(int hour, int min, int sec, int millis) {
        final Calendar cal = Calendar.getInstance();

        final Calendar maxCal = Calendar.getInstance();
        maxCal.add(Calendar.DATE, 7);
        maxCal.set(Calendar.HOUR_OF_DAY, hour);
        maxCal.set(Calendar.MINUTE, min);
        maxCal.set(Calendar.SECOND, sec);
        maxCal.set(Calendar.MILLISECOND, millis);

        new DialogFragment() {
            @NonNull
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                DatePickerDialog dlg = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        //ignore
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
                DatePicker datePicker = dlg.getDatePicker();
                datePicker.setMaxDate(maxCal.getTime().getTime());
                return dlg;
            }
        }.show(getFragmentManager(), "TAG");

    }
}
