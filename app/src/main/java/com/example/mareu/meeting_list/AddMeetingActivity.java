package com.example.mareu.meeting_list;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mareu.R;
import com.example.mareu.databinding.ActivityAddMeetingBinding;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;
import com.example.mareu.service.MeetingApiService;
import com.example.mareu.service.MeetingRoomList;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddMeetingActivity extends AppCompatActivity {

    private final String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private ActivityAddMeetingBinding binding;
    private final List<String> meetingParticipants = new ArrayList<>();
    private MeetingApiService mApiService;
    private MeetingRoom meetingRoom;
    private int hour;
    private int minute;
    private int year;
    private int month;
    private int dayOfMonth;


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


            if (binding.meetingTopic.getText().toString().isEmpty() || binding.buttonSetupTime.getText().toString().equalsIgnoreCase("set up time")
                    || binding.buttonSetupDate.getText().toString().equalsIgnoreCase("set up date")) {

                Toast.makeText(this, "Make sure all text fields are filled in", Toast.LENGTH_LONG).show();


            } else {
                if (printChipsValue(binding.chipGroup2) == 0) {


                    meetingRoom = (MeetingRoom) binding.meetingRoomListSpinner.getSelectedItem();

                    Meeting meeting = new Meeting(
                            binding.meetingTopic.getText().toString(),
                            binding.buttonSetupTime.getText().toString(), binding.buttonSetupDate.getText().toString(),
                            meetingRoom,
                            meetingParticipants

                    );


                    mApiService.createMeeting(meeting);

                    Intent intent = new Intent(AddMeetingActivity.this, ListMeetingActivity.class);

                    startActivity(intent);


                }


            }


        });


        binding.etValue.setOnEditorActionListener((v, actionId, event) -> {


            // TODO Create Chip on space bar input


            if (actionId == EditorInfo.IME_ACTION_DONE /* || Spacebar is pressed */) {
                String chipText = v.getText().toString();
                if (!chipText.isEmpty()) {
                    addChipToGroup(chipText, binding.chipGroup2);
                    binding.etValue.setText("");
                }

                return true;

            }
            return false;
        });


    }

    private void addChipToGroup(String text, ChipGroup chipGroup) {

        Chip chip = new Chip(AddMeetingActivity.this);

        chip.setText(text);
        chip.setCloseIconVisible(true);
        chip.setClickable(false);
        chip.setCheckable(false);
        chipGroup.addView(chip);

        chip.setOnCloseIconClickListener(v -> {

            chipGroup.removeView(chip);

            printChipsValue(chipGroup);

        });


    }

    private int printChipsValue(ChipGroup chipGroup) {

        int controller = 0;

        for (int i = 0; i < chipGroup.getChildCount(); i++) {

            String email = ((Chip) chipGroup.getChildAt(i)).getText().toString();

            if (email.matches(EMAIL_PATTERN)) {
                meetingParticipants.add(email);
            } else {
                controller = 1;
                Toast.makeText(this, "bad email format", Toast.LENGTH_SHORT).show();
            }


        }

        return controller;

    }


    public void popDatePicker(View view) {

        DatePickerDialog.OnDateSetListener onDateSetListener = (view1, year, month, dayOfMonth) -> {

            this.year = year;
            this.month = month;
            this.dayOfMonth = dayOfMonth;

            binding.buttonSetupDate.setText(String.format(Locale.getDefault(), "%02d/%02d/%04d", this.dayOfMonth, this.month, this.year));

        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, onDateSetListener, year, month, dayOfMonth);
        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();
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
