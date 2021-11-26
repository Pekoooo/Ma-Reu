package com.example.mareu.service;

import static org.junit.Assert.assertTrue;

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
    public void setUp()  {

        SUT = DI.getNewInstanceApiService();

    }

    @Test
    public void meetingApiService_createMeeting_withSuccess() {
        
        List<String> meetingParticipants = new ArrayList<>();
        MeetingRoom meetingRoom = MeetingRoomList.getExistingMeetingRoom().get(0);

        Meeting meeting = new Meeting("Topic", "10H00", meetingRoom, meetingParticipants );

        SUT.createMeeting(meeting);

        assertTrue(SUT.getMeetings().contains(meeting));





        // NEED TO ADD A MEETING ROOM HERE BEFORE DOING ANOTHER TEST

        //Meeting meeting = new Meeting("Feature 1", "10h00",  meetingParticipants);



        //assertTrue(SUT.getMeetings().contains(meeting));
    }
}