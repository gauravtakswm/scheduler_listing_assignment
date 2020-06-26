package com.gauravtak.scheduler_assignment.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gauravtak.scheduler_assignment.R
import com.gauravtak.scheduler_assignment.databinding.ActivityMeetingsListingBinding
import com.gauravtak.scheduler_assignment.holders.MeetingsAdapter
import com.gauravtak.scheduler_assignment.model_classes.ScheduledMeetingsDataBean
import com.gauravtak.scheduler_assignment.utils_classes.TimeUtilsHelper
import com.gauravtak.scheduler_assignment.utils_classes.TimeUtilsHelper.Companion.getTodayDate
import com.gauravtak.scheduler_assignment.viewmodels.ButtonPrimaryGreenVM
import com.gauravtak.scheduler_assignment.viewmodels.ToolbarViewModel
import java.util.*

class MeetingsListingActivity : AppCompatActivity() {
    var activityMeetingsListingBinding: ActivityMeetingsListingBinding? = null
    private val mActivity: AppCompatActivity = this
    var meetingsDataBeanArrayList = ArrayList<ScheduledMeetingsDataBean>()
    private var meetingListAdapter: MeetingsAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMeetingsListingBinding = DataBindingUtil.setContentView(this, R.layout.activity_meetings_listing)
        //   activityMeetingsListingBinding.setViewModel(new LoginViewModel());
        activityMeetingsListingBinding?.executePendingBindings()
        setToolbarInit()
        setBottomBtnInit()
        setUpRecyclerView()
        //getScheduledMeetingListing();
    }

    private fun setUpRecyclerView() {
        meetingListAdapter = MeetingsAdapter(mActivity, meetingsDataBeanArrayList)
        activityMeetingsListingBinding!!.recyclerViewMeetings.adapter = meetingListAdapter
    }

    private fun setBottomBtnInit() {
        //  ButtonPrimaryGreenVM buttonPrimaryGreenVM = (ButtonPrimaryGreenVM) (ViewModelProviders.of((MeetingsListingActivity)this).get(ButtonPrimaryGreenVM.class));
        val buttonPrimaryGreenVM = ButtonPrimaryGreenVM()
        activityMeetingsListingBinding!!.btnViewModel = buttonPrimaryGreenVM
        buttonPrimaryGreenVM.init(getString(R.string.schedule_company_meeting))
        buttonPrimaryGreenVM.btnClickEvent!!.observe(this, Observer<Any?> { startActivity(Intent(mActivity, ScheduleBookingActivity::class.java)) })
    }

    private fun setToolbarInit() {
        val toolbarViewModel = ViewModelProviders.of(this).get(ToolbarViewModel::class.java)
        activityMeetingsListingBinding!!.toolbarViewModel = toolbarViewModel
        toolbarViewModel.init(mActivity, getTodayDate(), getString(R.string.label_prev), getString(R.string.label_next))
        toolbarViewModel.meetingListingEvent!!.observe(this, Observer<Any?> { obj -> meetingListAdapter!!.setData(obj as ArrayList<ScheduledMeetingsDataBean>) })
        toolbarViewModel.tvLeftClick!!.observe(this, Observer<Any?> { obj ->
            val previousDateValue = TimeUtilsHelper.Companion.getMinus1Day(toolbarViewModel.centerTitleValue.get())
            toolbarViewModel.centerTitleValue.set(previousDateValue)
            toolbarViewModel.getScheduledMeetingListingApiCall(previousDateValue)

        })
        toolbarViewModel.tvRightClick!!.observe(this, Observer<Any?> { obj ->
            val nextDateValue = TimeUtilsHelper.Companion.getPlus1Day(toolbarViewModel.centerTitleValue.get())
            toolbarViewModel.centerTitleValue.set(nextDateValue)
            toolbarViewModel.getScheduledMeetingListingApiCall(nextDateValue)
        })
        toolbarViewModel.getScheduledMeetingListingApiCall(getTodayDate())
    }
}
