package com.example.mareu.model;

import androidx.annotation.NonNull;

public class MeetingRoom {

    private String name;
    private String roomPictureUrl;
    private int roomId;
    private int maximumCapacity;



    private boolean isAvailable;

    public MeetingRoom(String name, String roomPictureUrl, int roomId, int maximumCapacity, boolean isAvailable){

        this.name = name;
        this.roomPictureUrl = roomPictureUrl;
        this.roomId = roomId;
        this.maximumCapacity = maximumCapacity;
        this.isAvailable = isAvailable;
    }

    public String getRoomPictureUrl() {
        return roomPictureUrl;
    }

    public void setRoomPictureUrl(String roomPictureUrl) {
        this.roomPictureUrl = roomPictureUrl;
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public void setMaximumCapacity(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {

        this.roomId = roomId;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }




}
