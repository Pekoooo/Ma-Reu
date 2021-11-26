package com.example.mareu.service;

import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;
import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {

    private final List<Meeting> meetings = MeetingList.getDummyMeetingList();
    private final List<MeetingRoom> meetingRooms = MeetingRoomList.getExistingMeetingRoom();


    @Override
    public List<MeetingRoom> getMeetingRooms() {
        return meetingRooms;
    }

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public void createMeeting(Meeting meeting) {

        meetings.add(meeting);


    }

    @Override
    public void deleteMeeting(Meeting meeting) {

        meetings.remove(meeting);


    }


}
