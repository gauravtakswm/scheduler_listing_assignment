package com.gauravtak.scheduler_assignment.model_classes;

import java.util.List;

public class ScheduledMeetingsDataBean {

    /**
     * start_time : 9:00
     * end_time : 11:00
     * description : iOS development session 1.0
     * participants : ["Sushant Sehgal","Neha","Sumit Arora","Rajeev Kakkar","Prashant Lehri"]
     */

    private String start_time;
    private String end_time;
    private String description;
    private List<String> participants;

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public void setParticipants(List<String> participants) {
        this.participants = participants;
    }
}
