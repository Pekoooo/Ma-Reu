package com.example.mareu.meeting_list;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.mareu.databinding.ActivityAddMeetingBinding;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;
import com.example.mareu.service.MeetingApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;
    private List<MeetingRoom> meetingRoomList;
    private List<String> meetingParticipants = new ArrayList<>();
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


        binding.addParticipantsButton.setOnClickListener(v -> {

            if (!binding.meetingParticipants.getText().toString().trim().equals("")) {

                meetingParticipants.add(binding.meetingParticipants.getText().toString());
                Toast.makeText(AddMeetingActivity.this, "Participant added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AddMeetingActivity.this, "Please enter at least one participant", Toast.LENGTH_LONG).show();
            }


            binding.meetingParticipants.setText("");


        });

        binding.buttonCreateMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Meeting meeting = new Meeting(
                        binding.meetingTopic.getText().toString(),
                        binding.buttonSetupTime.getText().toString(), meetingRoom,
                        meetingParticipants);

                //meetingRoom =  (MeetingRoom) binding.meetingRoomListSpinner.getSelectedItem();

                meetingRoom = (mApiService.getMeetingRooms().get(0));


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

        int style = AlertDialog.THEME_HOLO_DARK;
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, style, onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }


}