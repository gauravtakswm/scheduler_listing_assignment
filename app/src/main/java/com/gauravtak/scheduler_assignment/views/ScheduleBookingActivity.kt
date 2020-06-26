package com.gauravtak.scheduler_assignment.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gauravtak.scheduler_assignment.R
import com.gauravtak.scheduler_assignment.databinding.ActivityScheduleBookingBinding
import com.gauravtak.scheduler_assignment.holders.MeetingsAdapter
import com.gauravtak.scheduler_assignment.model_classes.ScheduledMeetingsDataBean
import com.gauravtak.scheduler_assignment.utils_classes.TimeUtilsHelper
import com.gauravtak.scheduler_assignment.utils_classes.TimeUtilsHelper.Companion.getTodayDate
import com.gauravtak.scheduler_assignment.utils_classes.UtilsHelper
import com.gauravtak.scheduler_assignment.viewmodels.ButtonPrimaryGreenVM
import com.gauravtak.scheduler_assignment.viewmodels.ScheduleMeetingViewModel
import com.gauravtak.scheduler_assignment.viewmodels.ToolbarViewModel
import kotlinx.android.synthetic.main.activity_schedule_booking.*
import java.util.*

class ScheduleBookingActivity : AppCompatActivity() {
    var activityScheduleBookingBinding: ActivityScheduleBookingBinding? = null
    var scheduleBookingViewModel: ScheduleMeetingViewModel? = null
    var toolbarViewModel: ToolbarViewModel? = null
    private val mActivity: AppCompatActivity = this
    var meetingsDataBeanArrayList = ArrayList<ScheduledMeetingsDataBean>()
    private var meetingListAdapter: MeetingsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityScheduleBookingBinding = DataBindingUtil.setContentView(this, R.layout.activity_schedule_booking)
        activityScheduleBookingBinding?.executePendingBindings()
        setToolbarInit()
        setBottomBtnInit()
        setScheduleFormInit()
        //getScheduledMeetingListing();

    }


    private fun setBottomBtnInit() {
        //  ButtonPrimaryGreenVM buttonPrimaryGreenVM = (ButtonPrimaryGreenVM) (ViewModelProviders.of((MeetingsListingActivity)this).get(ButtonPrimaryGreenVM.class));
        val buttonPrimaryGreenVM = ButtonPrimaryGreenVM()
        activityScheduleBookingBinding!!.btnViewModel = buttonPrimaryGreenVM
        buttonPrimaryGreenVM.init(getString(R.string.submit))
        buttonPrimaryGreenVM.btnClickEvent!!.observe(this, Observer<Any?> {
            //validation action required for this stage
            if (validation()) {
                toolbarViewModel?.getScheduledMeetingListingApiCall(scheduleBookingViewModel?.meetingDate?.get().toString())
            }
        })

    }

    private fun validation(): Boolean {
        if (scheduleBookingViewModel?.meetingDate?.get().equals(getString(R.string.meeting_date), true)) {
            UtilsHelper.showSnackBar(mActivity, getString(R.string.please_select_meeting_date));
            et_meeting_date.requestFocus()
            return false;
        } else if (scheduleBookingViewModel?.meetingStartTime?.get().equals(getString(R.string.start_time), true)) {
            UtilsHelper.showSnackBar(mActivity, getString(R.string.please_select_meeting_start_time));
            et_meeting_start.requestFocus()
            return false;
        } else if (scheduleBookingViewModel?.meetingEndTime?.get().equals(getString(R.string.end_time), true)) {
            UtilsHelper.showSnackBar(mActivity, getString(R.string.please_select_meeting_end_time));
            et_meeting_end.requestFocus()
            return false;
        } else if (!TimeUtilsHelper.compareStartTimeEndTime(scheduleBookingViewModel?.meetingStartTime?.get()!!, scheduleBookingViewModel?.meetingEndTime?.get()!!)) {
            UtilsHelper.showSnackBar(mActivity, getString(R.string.please_select_correct_start_end_time));
            et_description.requestFocus()
            return false;
        } else if (et_description.text == null || et_description.text.toString().trim().isEmpty()) {
            UtilsHelper.showSnackBar(mActivity, getString(R.string.please_enter_description));
            et_description.requestFocus()
            return false;
        }
        return true;
    }

    private fun setToolbarInit() {
        toolbarViewModel = ViewModelProviders.of((this as ScheduleBookingActivity)).get(ToolbarViewModel::class.java)
        activityScheduleBookingBinding?.setToolbarViewModel(toolbarViewModel)
        toolbarViewModel?.init(mActivity, getString(R.string.schedule_a_meeting), getString(R.string.back), null)
        toolbarViewModel?.tvLeftClick!!.observe(this, Observer<Any?> { obj ->
            finish()
        })
        toolbarViewModel?.meetingListingEvent!!.observe(this, Observer<Any?> { obj ->
            scheduleBookingViewModel?.checkSlotsAvailability(mActivity, obj);
        })

    }

    private fun setScheduleFormInit() {
        scheduleBookingViewModel = ViewModelProviders.of((this as ScheduleBookingActivity)).get(ScheduleMeetingViewModel::class.java)
        activityScheduleBookingBinding?.bookingViewModel = scheduleBookingViewModel
        scheduleBookingViewModel?.initValues(mActivity);
        scheduleBookingViewModel?.showSlotMessage!!.observe(this, Observer<Any?> {
            //validation action required for this stage
            UtilsHelper.showSnackBar(mActivity, it as String)
        })

    }


}

