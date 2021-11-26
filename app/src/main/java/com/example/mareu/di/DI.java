package com.example.mareu.di;

import com.example.mareu.service.DummyMeetingApiService;
import com.example.mareu.service.MeetingApiService;
import com.example.mareu.service.MeetingRoomList;

public class DI {

    private static MeetingApiService service = new DummyMeetingApiService();


    public static MeetingApiService getMeetingApiService(){
        return service;
    }

    public static MeetingApiService getNewInstanceApiService(){
        return new DummyMeetingApiService();
    }
}
