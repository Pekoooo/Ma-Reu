package com.example.mareu.meeting_list;

import static android.content.ContentValues.TAG;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mareu.R;
import com.example.mareu.databinding.ActivityAddMeetingBinding;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;
import com.example.mareu.service.MeetingApiService;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;
    private final List<String> meetingParticipants = new ArrayList<>();
    private MeetingApiService mApiService;
    private MeetingRoom meetingRoom;
    private long meetingId;
    private int hour;
    private int minute;

    String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    Calendar calendar = Calendar.getInstance();

    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mApiService = DI.getMeetingApiService();

        setSpinner();
        setButtonCreate();
        setChipConverter();
        setPopPickers();


    }

    private void setPopPickers() {
        binding.buttonSetupTime.setOnClickListener(v -> popTimePicker());
        // R.ID.MENU. SETONCLICKLISTENER TRIGGER POPTIMEPICKER


        binding.buttonSetupDate.setOnClickListener(v -> popDatePicker());
    }


    private void setSpinner() {

        ArrayAdapter<MeetingRoom> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mApiService.getMeetingRooms());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.meetingRoomListSpinner.setAdapter(adapter);

        binding.editText.setOnClickListener(v -> binding.meetingRoomListSpinner.performClick());


        binding.meetingRoomListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                MeetingRoom selectedMeetingRoom = (MeetingRoom) binding.meetingRoomListSpinner.getSelectedItem();

                binding.editText.setText(selectedMeetingRoom.getName());


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }



    private void setButtonCreate() {
        binding.buttonCreateMeeting.setOnClickListener(v -> {


            if (binding.meetingTopic.getText().toString().isEmpty() || binding.buttonSetupTime.getText().toString().equalsIgnoreCase("set up time")
                    || binding.buttonSetupDate.getText().toString().equalsIgnoreCase("set up date")) {

                Toast.makeText(this, "Make sure all text fields are filled in", Toast.LENGTH_LONG).show();


            } else if (printChipsValue(binding.chipGroup2) == 1) {

                Toast.makeText(this, "Wrong email format", Toast.LENGTH_SHORT).show();
            } else {

                meetingRoom = (MeetingRoom) binding.meetingRoomListSpinner.getSelectedItem();

                meetingId = mApiService.generateId();

                Log.d(TAG, "setButtonCreate: created" + meetingId);


                Meeting meeting = new Meeting(
                        binding.meetingTopic.getText().toString(),
                        binding.buttonSetupTime.getText().toString(),
                        binding.buttonSetupDate.getText().toString(),
                        meetingRoom,
                        meetingParticipants, meetingId

                );


                mApiService.createMeeting(meeting);

                finish();

            }


        });
    }

    private void setChipConverter() {

        binding.editTextParticipants.setOnEditorActionListener((TextView, actionId, event) -> {


            if (actionId == EditorInfo.IME_ACTION_DONE) {
                String chipText = TextView.getText().toString();
                if (!chipText.isEmpty()) {
                    addChipToGroup(chipText, binding.chipGroup2);
                    binding.editTextParticipants.setText("");
                    binding.emptyChipGroupMessage.setText("");
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

            if (chipGroup.getChildCount() == 0) {

                binding.emptyChipGroupMessage.setText(R.string.no_participants);
            }

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

            }

        }

        return controller;

    }

    private void popDatePicker() {

        DatePickerDialog.OnDateSetListener onDateSetListener = (view1, year, month, dayOfMonth) -> {


            this.year = year;
            this.month = month + 1;
            this.dayOfMonth = dayOfMonth;


            binding.buttonSetupDate.setText(String.format(Locale.getDefault(), "%02d/%02d/%04d", this.dayOfMonth, this.month, this.year));

        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, onDateSetListener, year, month -1, dayOfMonth);
        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();


    }

    private void popTimePicker() {

        TimePickerDialog.OnTimeSetListener onTimeSetListener = (view1, selectedHour, selectedMinute) -> {
            hour = selectedHour;
            minute = selectedMinute;
            binding.buttonSetupTime.setText(String.format(Locale.getDefault(), "%02dh%02d", hour, minute));


        };


        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }


}
