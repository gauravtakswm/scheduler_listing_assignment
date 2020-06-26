package com.gauravtak.scheduler_assignment.utils_classes

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import com.gauravtak.scheduler_assignment.SchedulerApp
import com.google.android.material.snackbar.Snackbar
import java.util.*

class UtilsHelper {
    companion object {
        public fun showToast(message: String) {
            Toast.makeText(SchedulerApp.mContext, message, Toast.LENGTH_LONG).show()
        }

        public fun showSnackBar(mActivity: AppCompatActivity, message: String) {
            Snackbar.make(mActivity.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
            // Toast.makeText(SchedulerApp.mContext,message,Toast.LENGTH_LONG).show()
        }

        public fun showDatePicker(mContext: Context, dateValue: ObservableField<String>, mTitle: String, disablePrevioudDates: Boolean) {
            val myCalendar: Calendar = Calendar.getInstance()
            val datePickerDialog: DatePickerDialog = DatePickerDialog(mContext, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth -> // TODO Auto-generated method stub
                /*  myCalendar.set(Calendar.YEAR, year)
                  myCalendar.set(Calendar.MONTH, monthOfYear)
                  myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                */
                dateValue.set(dayOfMonth.toString() + "-" + monthOfYear + "-" + year);
                //  updateLabel()
            }, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH))
            if (disablePrevioudDates)
                datePickerDialog.datePicker.minDate = System.currentTimeMillis() - 1000;
            datePickerDialog.show()
        }

        public fun showTimePicker(mContext: Context, timeValue: ObservableField<String>, mTitle: String) {
            val mcurrentTime = Calendar.getInstance()
            val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
            val minute = mcurrentTime[Calendar.MINUTE]
            val mTimePicker: TimePickerDialog
            mTimePicker = TimePickerDialog(mContext, TimePickerDialog.OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                val AM_PM: String
                var selectedHourValueStr = selectedHour.toString();
                var selectedMinValueStr = selectedMinute.toString();

                AM_PM = if (selectedHour < 12) {
                    "AM"
                } else {
                    "PM"

                }
                if (AM_PM.equals("PM", true) && !selectedHour.toString().equals("12")) {
                    selectedHourValueStr = (selectedHour - 12).toString();
                } else {
                    selectedHourValueStr = selectedHour.toString();
                }
                if (selectedHourValueStr.toInt() < 10) {
                    selectedHourValueStr = "0" + selectedHourValueStr
                }
                if (selectedMinValueStr.toInt() < 10) {
                    selectedMinValueStr = "0" + selectedMinValueStr
                }

                timeValue.set(selectedHourValueStr + ":" + selectedMinValueStr + "" + AM_PM)
            }, hour, minute, true) //Yes 24 hour time

            mTimePicker.setTitle(mTitle)
            mTimePicker.show()

        }
    }

}