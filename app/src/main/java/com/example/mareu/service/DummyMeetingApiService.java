package com.example.mareu.service;

import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DummyMeetingApiService implements MeetingApiService {

    private final List<Meeting> meetings = MeetingList.getDummyMeetingList();
    private final List<MeetingRoom> meetingRooms = MeetingRoomList.getExistingMeetingRoom();
    private final List<Meeting> meetingsByRoom = new ArrayList<>();
    private final List<Meeting> meetingsByDate = new ArrayList<>();


    @Override
    public List<MeetingRoom> getMeetingRooms() {
        return meetingRooms;
    }

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public List<Meeting> getMeetingsByRoom() {
        return meetingsByRoom;
    }

    @Override
    public List<Meeting> getMeetingsByDate() {
        return meetingsByDate;
    }

    @Override
    public void createMeeting(Meeting meeting) {

        meetings.add(meeting);


    }

    @Override
    public long generateId() {
        Random random = new Random();

        long upperbound = 1000;

        long long_random = random.nextLong();


        return upperbound + long_random;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {

        meetings.remove(meeting);


    }


}
