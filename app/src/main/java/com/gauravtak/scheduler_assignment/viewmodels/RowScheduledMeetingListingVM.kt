package com.gauravtak.scheduler_assignment.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.gauravtak.scheduler_assignment.model_classes.ScheduledMeetingsDataBean
import com.gauravtak.scheduler_assignment.utils_classes.TimeUtilsHelper

public class RowScheduledMeetingListingVM(val meetingsDataBean: ScheduledMeetingsDataBean) : ViewModel() {
    var timeValue: ObservableField<String>? = ObservableField<String>()
    var startTimeValue: ObservableField<String>? = ObservableField<String>()
    var endTimeValue: ObservableField<String>? = ObservableField<String>()

    var desValue: ObservableField<String>? = ObservableField<String>()
    var paticipantsValue: ObservableField<String>? = ObservableField<String>()

    init {
        timeValue?.set(TimeUtilsHelper.convertTimeFrom24to12Format(meetingsDataBean.start_time) + " - " + TimeUtilsHelper.convertTimeFrom24to12Format(meetingsDataBean.end_time))
        desValue?.set(meetingsDataBean.description)
        paticipantsValue?.set(meetingsDataBean.participants.joinToString(separator = ", "))
        startTimeValue?.set(TimeUtilsHelper.convertTimeFrom24to12Format(meetingsDataBean.start_time));
        endTimeValue?.set(TimeUtilsHelper.convertTimeFrom24to12Format(meetingsDataBean.end_time));

    }

}