package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    @Override
    public int getInboxCapacity() {
        return inboxCapacity;
    }

    int inboxCapacity;
    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        inboxCapacity=Integer.MAX_VALUE;
        calendar=new ArrayList();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        if(calendar.size()==0)
            return 0;
        Collections.sort(calendar,(m1,m2)-> m1.getEndTime().compareTo(m2.getEndTime()));
        int meetingsCanAttend=1;
        LocalTime prevEndTime=calendar.get(0).getEndTime();
        for(int i=1;i<calendar.size();i++){
            LocalTime currStartTime=calendar.get(i).getStartTime();
            if(currStartTime.isAfter(prevEndTime))
            {
                meetingsCanAttend++;
                prevEndTime=calendar.get(i).getEndTime();
            }
        }
        return meetingsCanAttend;
    }
}
