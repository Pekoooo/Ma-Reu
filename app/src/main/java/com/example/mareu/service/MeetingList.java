package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MeetingList {




    private static List<String> dummyList;

    public static List<Meeting> STATIC_MEETING_LIST = Arrays.asList(
            new Meeting("Meeting", "10H00", MeetingRoomList.getExistingMeetingRoom().get(1), dummyList),
            new Meeting("Meeting", "10H00", MeetingRoomList.getExistingMeetingRoom().get(1), dummyList),
            new Meeting("Meeting", "10H00", MeetingRoomList.getExistingMeetingRoom().get(1), dummyList)
    );




    static List<Meeting> getDummyMeetingList() {
        return new ArrayList<>(STATIC_MEETING_LIST);
    }


}
