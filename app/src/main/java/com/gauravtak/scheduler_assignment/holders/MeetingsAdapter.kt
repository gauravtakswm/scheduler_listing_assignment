package com.gauravtak.scheduler_assignment.holders

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.gauravtak.scheduler_assignment.R
import com.gauravtak.scheduler_assignment.databinding.ListItemScheduleMeetingBinding
import com.gauravtak.scheduler_assignment.model_classes.ScheduledMeetingsDataBean
import com.gauravtak.scheduler_assignment.viewmodels.RowScheduledMeetingListingVM


/*class MeetingsAdapter {
}*/

class MeetingsAdapter(private val activity: Activity, private var meetingList: ArrayList<ScheduledMeetingsDataBean>)
    : RecyclerView.Adapter<MeetingsAdapter.MeetingViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MeetingViewHolder {
        val listItemScheduleMeetingBinding: ListItemScheduleMeetingBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.context),
                R.layout.list_item_schedule_meeting, viewGroup, false) as ListItemScheduleMeetingBinding
        return MeetingViewHolder(listItemScheduleMeetingBinding);
    }

    override fun onBindViewHolder(holder: MeetingViewHolder, position: Int) {
        val meetingsDataBean: ScheduledMeetingsDataBean = meetingList[position]
        holder.bind(meetingsDataBean)


    }

    class MeetingViewHolder(listItemScheduleMeetingBinding: ListItemScheduleMeetingBinding) : RecyclerView.ViewHolder(listItemScheduleMeetingBinding.getRoot()) {
        var mBinding: ListItemScheduleMeetingBinding? = null
        fun bind(meetingsDataBean: ScheduledMeetingsDataBean) {
            mBinding?.executePendingBindings()
            val rowScheduledMeetingListingVM = RowScheduledMeetingListingVM(meetingsDataBean)
            mBinding?.rowScheduledMeetingListing = rowScheduledMeetingListingVM;
        }

        init {
            mBinding = listItemScheduleMeetingBinding
        }
    }

    override fun getItemCount(): Int = meetingList.size


    fun setData(data: java.util.ArrayList<ScheduledMeetingsDataBean>) {

        this.meetingList = data
        notifyDataSetChanged()
    }
}