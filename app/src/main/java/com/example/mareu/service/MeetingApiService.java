package com.example.mareu.service;

import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;

import java.util.List;

public interface MeetingApiService {

    List<MeetingRoom> getMeetingRooms();

    List<Meeting> getMeetings();

    List<Meeting> getMeetingsByRoom();

    List<Meeting> getMeetingsByDate();

    void deleteMeeting(Meeting meeting);

    void createMeeting(Meeting meeting);

    void deleteMeetingFromFilteredList(int position, List<Meeting> filteredMeetings);

    void deleteMeetingWithId(Meeting meetingToDelete);






}
