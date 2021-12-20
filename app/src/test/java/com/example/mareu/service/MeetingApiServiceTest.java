package com.example.mareu.service;

import static android.content.ContentValues.TAG;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;

import android.util.Log;

import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MeetingApiServiceTest {


    private MeetingApiService SUT;

    @Before
    public void setUp() {

        // Defines the system under test (SUT)
        SUT = DI.getNewInstanceApiService();

    }


    @Test
    public void meetingApiService_getMeetings_withSuccess() {
        List<Meeting> meetings = SUT.getMeetings();
        List<Meeting> expectedMeetings = MeetingList.getDummyMeetingList();
        MatcherAssert.assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));

    }


    @Test
    public void meetingApiService_getMeetingRooms_withSuccess() {
        List<MeetingRoom> meetingRooms = SUT.getMeetingRooms();
        List<MeetingRoom> expectedRooms = MeetingRoomList.getExistingMeetingRoom();

        assertThat(meetingRooms, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedRooms.toArray()));

    }


    @Test
    public void meetingApiService_getMeetingsByRooms_withSuccess() {

        Meeting meeting = SUT.getMeetings().get(0);

        SUT.getMeetingsByRoom().add(meeting);

        assertTrue(SUT.getMeetingsByRoom().contains(meeting));

    }


    @Test
    public void meetingApiService_getMeetingsByDate_withSuccess() {

        Meeting meeting = SUT.getMeetings().get(0);

        SUT.getMeetingsByDate().add(meeting);

        assertTrue(SUT.getMeetingsByDate().contains(meeting));

    }


    @Test
    public void meetingApiService_deleteMeeting_withSuccess() {

        Meeting meetingToDelete = MeetingList.getDummyMeetingList().get(0);

        SUT.deleteMeeting(meetingToDelete);

        assertFalse(SUT.getMeetings().contains(meetingToDelete));
    }


    @Test
    public void meetingApiService_createMeeting_withSuccess() {

        List<String> meetingParticipants = new ArrayList<>();
        MeetingRoom meetingRoom = MeetingRoomList.getExistingMeetingRoom().get(0);

        Meeting meeting = new Meeting("Topic", "10H00", "10/05", meetingRoom, meetingParticipants, "540");

        SUT.createMeeting(meeting);

        assertTrue(SUT.getMeetings().contains(meeting));


    }


    @Test
    public void meetingApiService_deleteMeetingFromFilteredList_withSuccess() {

        List<Meeting> meetings = SUT.getMeetings();
        List<Meeting> meetingFiltered = SUT.getMeetingsByDate();

        Meeting meetingToDelete = meetings.get(0);

        meetingFiltered.add(meetingToDelete);

        SUT.deleteMeetingFromFilteredList(0, meetingFiltered);

        assertFalse(meetingFiltered.contains(meetingToDelete));

    }


    @Test
    public void meetingApiService_deleteMeetingWithId_withSuccess() {

        List<Meeting> meetings = SUT.getMeetings();
        Meeting meetingToDelete = meetings.get(0);

        SUT.deleteMeetingWithId(meetingToDelete);

        assertFalse(meetings.contains(meetingToDelete));

    }


}