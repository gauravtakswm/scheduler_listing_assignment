package com.gauravtak.scheduler_assignment.viewmodels

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.gauravtak.scheduler_assignment.R
import com.gauravtak.scheduler_assignment.model_classes.ScheduledMeetingsDataBean
import com.gauravtak.scheduler_assignment.utils_classes.SingleLiveEvent
import com.gauravtak.scheduler_assignment.utils_classes.TimeUtilsHelper
import com.gauravtak.scheduler_assignment.utils_classes.UtilsHelper


public class ScheduleMeetingViewModel : ViewModel() {
    var mContext: Context? = null
    val meetingDate: ObservableField<String> = ObservableField<String>()
    val meetingStartTime: ObservableField<String> = ObservableField<String>()
    val meetingEndTime: ObservableField<String> = ObservableField<String>()
    val showSlotMessage: SingleLiveEvent<String> = SingleLiveEvent();


    public fun initValues(mContext: Context) {
        this.mContext = mContext;
        this.meetingDate.set(mContext?.resources.getString(R.string.meeting_date));
        this.meetingStartTime.set(mContext?.resources.getString(R.string.start_time));
        this.meetingEndTime.set(mContext?.resources.getString(R.string.end_time));

    }

    fun performClick(view: View) {
        when (view.id) {
            R.id.et_meeting_date -> {
                if (mContext != null)
                    UtilsHelper.showDatePicker(mContext!!, meetingDate, mContext?.resources?.getString(R.string.meeting_date)!!, true)

            }
            R.id.et_meeting_start -> {
                UtilsHelper.showTimePicker(mContext!!, meetingStartTime, mContext?.resources?.getString(R.string.start_time)!!)
            }
            R.id.et_meeting_end -> {
                UtilsHelper.showTimePicker(mContext!!, meetingEndTime, mContext?.resources?.getString(R.string.end_time)!!)
            }
        }

    }

    fun checkSlotsAvailability(mActivity: AppCompatActivity, obj: Any?) {
        val arrayListOfMeetings = obj as ArrayList<ScheduledMeetingsDataBean>
        var isSlotAvailable = true;
        for (meetingDataBean in arrayListOfMeetings) {
            val meetingStartTimeValue = TimeUtilsHelper.convertTimeFrom24to12Format(meetingDataBean.start_time);
            val meetingEndTimeValue = TimeUtilsHelper.convertTimeFrom24to12Format(meetingDataBean.end_time);

            if (TimeUtilsHelper.compareStartTimeEndTime(meetingStartTimeValue, meetingStartTime.get().toString()) && TimeUtilsHelper.compareStartTimeEndTime(meetingStartTime.get().toString(), meetingEndTimeValue)) {
                isSlotAvailable = false;
                break;
            } else if (TimeUtilsHelper.compareStartTimeEndTime(meetingStartTimeValue, meetingEndTime.get().toString()) && TimeUtilsHelper.compareStartTimeEndTime(meetingEndTime.get().toString(), meetingEndTimeValue)) {
                isSlotAvailable = false;
                break;
            } else if (TimeUtilsHelper.compareStartTimeEndTime(meetingStartTime.get().toString(), meetingStartTimeValue) && TimeUtilsHelper.compareStartTimeEndTime(meetingStartTimeValue, meetingEndTime.get().toString())) {
                isSlotAvailable = false;
                break;
            } else if (TimeUtilsHelper.compareStartTimeEndTime(meetingStartTime.get().toString(), meetingEndTimeValue) && TimeUtilsHelper.compareStartTimeEndTime(meetingEndTimeValue, meetingEndTime.get().toString())) {
                isSlotAvailable = false;
                break;
            }
        }
        if (isSlotAvailable) {
            showSlotMessage.value = mActivity.getString(R.string.slot_available)
        } else {
            showSlotMessage.value = mActivity.getString(R.string.slot_not_available)

        }
    }
}