package com.example.mareu.meeting_list;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.mareu.databinding.ActivityAddMeetingBinding;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;
import com.example.mareu.service.MeetingApiService;
import com.example.mareu.service.MeetingRoomList;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;
    private List<MeetingRoom> meetingRoomList;
    private final List<String> meetingParticipants = new ArrayList<>();
    private MeetingApiService mApiService;
    private String meetingTopic;
    private String meetingTime;
    private MeetingRoom meetingRoom;
    private int hour;
    private int minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mApiService = DI.getMeetingApiService();

        ArrayAdapter<MeetingRoom> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mApiService.getMeetingRooms());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.meetingRoomListSpinner.setAdapter(adapter);


        binding.buttonCreateMeeting.setOnClickListener(v -> {


            if (binding.meetingTopic.getText().toString().isEmpty() || binding.buttonSetupTime.getText().toString().equalsIgnoreCase("set up time")) {

                Toast.makeText(this, "Make sure all text fields are filled in", Toast.LENGTH_LONG).show();


            } else {


                meetingRoom = (MeetingRoom) binding.meetingRoomListSpinner.getSelectedItem();

                Meeting meeting = new Meeting(
                        binding.meetingTopic.getText().toString(),
                        binding.buttonSetupTime.getText().toString(), meetingRoom,
                        meetingParticipants

                );

                mApiService.createMeeting(meeting);

                Intent intent = new Intent(AddMeetingActivity.this, ListMeetingActivity.class);

                startActivity(intent);


            }


        });


    }


    public void popTimePicker(View view) {

        TimePickerDialog.OnTimeSetListener onTimeSetListener = (view1, selectedHour, selectedMinute) -> {
            hour = selectedHour;
            minute = selectedMinute;
            binding.buttonSetupTime.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));


        };


        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }


}