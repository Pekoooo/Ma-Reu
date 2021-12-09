package com.example.mareu.service;

import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class MeetingList {





    public static List<Meeting> STATIC_MEETING_LIST = Arrays.asList(


            new Meeting("Q4 General meeting", "13h30", "12/11/2021", MeetingRoomList.getExistingMeetingRoom().get(0), DummyParticipantList.getDummyParticipantsList()),
            new Meeting("Daily SCRUM", "14h30", "12/11/2021", MeetingRoomList.getExistingMeetingRoom().get(1), DummyParticipantList.getDummyParticipantsList()),
            new Meeting("Digital marketing meeting ", "15h30", "12/11/2021", MeetingRoomList.getExistingMeetingRoom().get(2), DummyParticipantList.getDummyParticipantsList()),
            new Meeting("Q4 General meeting", "13h30", "12/11/2021", MeetingRoomList.getExistingMeetingRoom().get(9), DummyParticipantList.getDummyParticipantsList())
    );


    static List<Meeting> getDummyMeetingList() {
        return new ArrayList<>(STATIC_MEETING_LIST);
    }


}
