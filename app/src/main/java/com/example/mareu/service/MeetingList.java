package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class MeetingList {


    public static List<Meeting> STATIC_MEETING_LIST = Collections.emptyList();


    static List<Meeting> getDummyMeetingList() {
        return new ArrayList<>(STATIC_MEETING_LIST);
    }


}
