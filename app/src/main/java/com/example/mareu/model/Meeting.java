package com.example.mareu.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Meeting implements Parcelable {

    private String meetingTopic;
    private String meetingTime;
    private MeetingRoom meetingRoom;
    private List<String> meetingParticipants;
    private String meetingDate;




    public Meeting(String meetingTopic, String meetingTime, String meetingDate, MeetingRoom meetingRoom, List<String> meetingParticipants) {

        this.meetingTopic = meetingTopic;
        this.meetingTime = meetingTime;
        this.meetingDate = meetingDate;
        this.meetingRoom = meetingRoom;
        this.meetingParticipants = meetingParticipants;



    }


    protected Meeting(Parcel in) {

        meetingTopic = in.readString();
        meetingTime = in.readString();


    }


    public static final Creator<Meeting> CREATOR = new Creator<Meeting>() {
        @Override
        public Meeting createFromParcel(Parcel in) {
            return new Meeting(in);
        }

        @Override
        public Meeting[] newArray(int size) {
            return new Meeting[size];
        }
    };

    public List<String> getMeetingParticipants() {
        return meetingParticipants;
    }

    public void setMeetingParticipants(List<String> meetingParticipants) {
        this.meetingParticipants = meetingParticipants;
    }

    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom) {
        this.meetingRoom = meetingRoom;
    }


    public String getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(String meetingTime) {
        this.meetingTime = meetingTime;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }


    public String getMeetingTopic() {
        return meetingTopic;
    }

    public void setMeetingTopic(String meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {

        parcel.writeString(meetingTopic);
        parcel.writeString(meetingTime);


    }
}
