package com.example.mareu.service;

import com.example.mareu.model.MeetingRoom;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeetingRoomList {

    public static List<MeetingRoom> EXISTING_MEETING_ROOM = Arrays.asList(

            new MeetingRoom("Mind Expansion Mansion", "https://www.colorbook.io/imagecreator.php?hex=ffc09f&width=1920&height=1080&text=%201920x1080", 1, 10, true),
            new MeetingRoom("Decision Accelerator", "https://www.colorhexa.com/ffee93.png", 2, 15, true),
            new MeetingRoom("Pressure Cooker", "https://www.colorhexa.com/a0ced9.png", 3, 5, true),
            new MeetingRoom("Bored Room", "https://www.colorhexa.com/adf7b6.png", 4, 5, true),
            new MeetingRoom("The Bat Cave", "https://www.colorhexa.com/e4959e.png", 5, 8, true)

    );

    static List<MeetingRoom> getExistingMeetingRoom() {
        return new ArrayList<>(EXISTING_MEETING_ROOM);
    }


}
