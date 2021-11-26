package com.example.mareu.service;

import com.example.mareu.model.MeetingRoom;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeetingRoomList {

    public static List<MeetingRoom> EXISTING_MEETING_ROOM = Arrays.asList(

            new MeetingRoom("Mind Expansion Mansion", "https://i.pravatar.cc/150?u=a042581f4e29026704d", 1, 10, true),
            new MeetingRoom("Decision Accelerator", "https://i.pravatar.cc/150?u=a042581f4e29026704d", 2, 15, true),
            new MeetingRoom("Pressure Cooker", "https://i.pravatar.cc/150?u=a042581f4e29026704d", 3, 5, true),
            new MeetingRoom("Bored Room", "https://i.pravatar.cc/150?u=a042581f4e29026704d", 4, 5, true),
            new MeetingRoom("The Bat Cave", "https://i.pravatar.cc/150?u=a042581f4e29026704d", 5, 8, true)

    );

    static List<MeetingRoom> getExistingMeetingRoom() {
        return new ArrayList<>(EXISTING_MEETING_ROOM);
    }


}
