package com.gauravtak.scheduler_assignment.utils_classes

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class TimeUtilsHelper {
    companion object {
        fun convertTimeFrom24to12Format(timeValue: String?): String {
            try {
                val _24HourSDF = SimpleDateFormat("HH:mm")
                val _12HourSDF = SimpleDateFormat("hh:mma")
                val _24HourDt = _24HourSDF.parse(timeValue)
                System.out.println(_24HourDt)
                val _12FormatTime = _12HourSDF.format(_24HourDt);
                return _12FormatTime;
            } catch (e: Exception) {
                e.printStackTrace()
                return timeValue.toString();
            }

        }

        fun getTodayDate(): String {
            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
            val currentDate = sdf.format(Date())
            return currentDate;
        }

        fun getPlus1Day(dateValue: String?): String {
            val format: DateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
            val date: Date = format.parse(dateValue)
            val c = Calendar.getInstance()
            c.time = date
            c.add(Calendar.DATE, 1)
            return format.format(c.time).toString();
        }

        fun getMinus1Day(dateValue: String?): String {
            val format: DateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
            val date: Date = format.parse(dateValue)
            val c = Calendar.getInstance()
            c.time = date
            c.add(Calendar.DATE, -1)
            return format.format(c.time).toString();
        }

        fun compareStartTimeEndTime(strStartTime: String, strEndTime: String): Boolean {
            val sdf = SimpleDateFormat("hh:mma")
            val inTime = sdf.parse(strStartTime)
            val outTime = sdf.parse(strEndTime)
            if (outTime.before(inTime)) { //Same way you can check with after() method also.
                return false
            } else {
                return true
            }

        }

    }

}