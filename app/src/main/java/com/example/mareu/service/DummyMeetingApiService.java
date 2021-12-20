package com.example.mareu.service;

import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.meeting_list.ListMeetingActivity;
import com.example.mareu.meeting_list.ListMeetingRecyclerViewAdapter;
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
    public void deleteMeetingFromFilteredList(int position, List<Meeting> filteredMeetings) {
        Meeting meetingToDelete;

        meetingToDelete = filteredMeetings.get(position);

        deleteMeetingWithId(meetingToDelete);

        filteredMeetings.remove(position);


    }




    @Override
    public void deleteMeetingWithId(Meeting meetingToDelete) {

        for (int i = 0; i < meetings.size(); i++) {

            Meeting currentMeeting = meetings.get(i);

            if (currentMeeting.getMeetingId().equals(meetingToDelete.getMeetingId())) {

                meetings.remove(currentMeeting);
            }

        }

    }


    @Override
    public void deleteMeeting(Meeting meeting) {

        meetings.remove(meeting);


    }


}
