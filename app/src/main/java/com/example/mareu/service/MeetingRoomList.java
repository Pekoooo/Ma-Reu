package com.example.mareu.service;

import com.example.mareu.model.MeetingRoom;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeetingRoomList {

    public static List<MeetingRoom> EXISTING_MEETING_ROOM = Arrays.asList(

            new MeetingRoom("Select your meeting room", "NoURL", 404, 0, false),
            new MeetingRoom("Link", "https://www.colorbook.io/imagecreator.php?hex=ffc09f&width=1920&height=1080&text=%201920x1080", 1, 10, true),
            new MeetingRoom("Ganon", "https://www.colorhexa.com/ffee93.png", 2, 15, true),
            new MeetingRoom("Zelda", "https://www.colorhexa.com/a0ced9.png", 3, 5, true),
            new MeetingRoom("Impa", "https://www.colorhexa.com/adf7b6.png", 4, 5, true),
            new MeetingRoom("Navi", "https://www.colorhexa.com/e4959e.png", 5, 8, true),
            new MeetingRoom("Midona", "https://www.colorbook.io/imagecreator.php?hex=ffc09f&width=1920&height=1080&text=%201920x1080", 1, 10, true),
            new MeetingRoom("Sidon", "https://www.colorhexa.com/ffee93.png", 7, 15, true),
            new MeetingRoom("Mipha", "https://www.colorhexa.com/a0ced9.png", 8, 5, true),
            new MeetingRoom("Urbosa", "https://www.colorhexa.com/adf7b6.png", 9, 5, true),
            new MeetingRoom("Tingle", "https://www.colorhexa.com/e4959e.png", 10, 8, true)

    );

    static List<MeetingRoom> getExistingMeetingRoom() {
        return new ArrayList<>(EXISTING_MEETING_ROOM);
    }


}
