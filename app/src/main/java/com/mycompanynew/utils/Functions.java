package com.mycompanynew.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;
import com.mycompanynew.interfaces.DateSelectListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Functions {

    //Date Picker
    public static void showDatePicker(final Context mcontext,
                               Date selectedDate, Date minDate, Date maxDate, DateSelectListener dateSelectListener) {

        Calendar newCalendar = Calendar.getInstance();
        if (selectedDate != null)
            newCalendar.setTime(selectedDate);
        DatePickerDialog datePickerDialog = new DatePickerDialog(mcontext, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                if(dateSelectListener != null)
                    dateSelectListener.dateSelect(newDate);
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        try {
            if (minDate != null)
                datePickerDialog.getDatePicker().setMinDate(minDate.getTime());
            if (maxDate != null)
                datePickerDialog.getDatePicker().setMaxDate(maxDate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        datePickerDialog.show();
    }
}
