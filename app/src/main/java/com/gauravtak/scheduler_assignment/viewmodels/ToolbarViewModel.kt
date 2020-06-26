package com.gauravtak.scheduler_assignment.viewmodels

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.gauravtak.scheduler_assignment.R
import com.gauravtak.scheduler_assignment.model_classes.ScheduledMeetingsDataBean
import com.gauravtak.scheduler_assignment.network_call.APICommon
import com.gauravtak.scheduler_assignment.utils_classes.CustomProgressDialog
import com.gauravtak.scheduler_assignment.utils_classes.SingleLiveEvent
import com.gauravtak.scheduler_assignment.utils_classes.UtilsHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

public class ToolbarViewModel : ViewModel() {
    val meetingListingEvent: SingleLiveEvent<ArrayList<ScheduledMeetingsDataBean>>? = SingleLiveEvent()
    val tvLeftClick: SingleLiveEvent<Void>? = SingleLiveEvent()
    val tvRightClick: SingleLiveEvent<Void>? = SingleLiveEvent()
    var isRightTextVisible = ObservableBoolean()
    var leftTitleValue = ObservableField<String>()
    var centerTitleValue = ObservableField<String>()
    var rightTitleValue = ObservableField<String>()

    var mActivity: AppCompatActivity? = null

    fun init(mActivity: AppCompatActivity, centerTitle: String, leftTitle: String?, rightTitle: String?) {
        centerTitleValue.set(centerTitle)
        if (rightTitle != null) {
            rightTitleValue.set(rightTitle)
            isRightTextVisible.set(true)
        }
        leftTitleValue.set(leftTitle)
        this.mActivity = mActivity;
    }

    public fun getScheduledMeetingListingApiCall(dateValue: String) {
        CustomProgressDialog.showProgress(mActivity)
        val call = APICommon.getInstance().getScheduledMeetings(dateValue)
        call.enqueue(object : Callback<ArrayList<ScheduledMeetingsDataBean?>?> {
            override fun onResponse(call: Call<ArrayList<ScheduledMeetingsDataBean?>?>, response: Response<ArrayList<ScheduledMeetingsDataBean?>?>) {
                CustomProgressDialog.hideprogressbar()
                if (response.body() != null && response.body()?.size != 0) {
                    meetingListingEvent?.value = response.body() as ArrayList<ScheduledMeetingsDataBean>;
                } else {
                    UtilsHelper.showToast("No schedule meeting found for the date $dateValue");
                    meetingListingEvent?.value = ArrayList<ScheduledMeetingsDataBean>()
                }
            }

            override fun onFailure(call: Call<ArrayList<ScheduledMeetingsDataBean?>?>, t: Throwable) {
                CustomProgressDialog.hideprogressbar()
            }
        })
    }

    fun performClick(view: View) {
        when (view.id) {
            R.id.tv_right_title -> {
                tvRightClick?.call()
            }
            R.id.tv_left_title -> {
                tvLeftClick?.call()
            }
        }
    }

}