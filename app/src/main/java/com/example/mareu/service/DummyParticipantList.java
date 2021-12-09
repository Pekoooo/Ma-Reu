package com.example.mareu.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyParticipantList {

    public static List<String> DUMMY_PARTICIPANTS_LIST = Arrays.asList(

            "didier@lamzone.com",
            "Jessica75016@lamzone.com"
    );

    static List<String> getDummyParticipantsList(){
        return new ArrayList<>(DUMMY_PARTICIPANTS_LIST);
    }
}
