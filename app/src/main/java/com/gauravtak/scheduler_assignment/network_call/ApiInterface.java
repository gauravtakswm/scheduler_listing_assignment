


package com.gauravtak.scheduler_assignment.network_call;

import com.gauravtak.scheduler_assignment.model_classes.ScheduledMeetingsDataBean;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {


    @GET("schedule")
    Call<ArrayList<ScheduledMeetingsDataBean>> getScheduledMeetings(@Query("date") String dateValue);


}
