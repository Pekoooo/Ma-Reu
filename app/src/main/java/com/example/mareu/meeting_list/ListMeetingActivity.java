package com.example.mareu.meeting_list;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.callback.OnMeetingClickListener;
import com.example.mareu.databinding.ActivityMeetingListBinding;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;
import com.example.mareu.service.MeetingApiService;

import java.util.List;
import java.util.Locale;

public class ListMeetingActivity extends AppCompatActivity {

    private MeetingApiService apiService;
    private List<Meeting> meetings;
    private List<Meeting> meetingsByRoom;
    private List<Meeting> meetingsByDate;
    private ListMeetingRecyclerViewAdapter adapter;
    private int year;
    private int month;
    private int dayOfMonth;


    private ActivityMeetingListBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiService = DI.getMeetingApiService();
        setView();
        setSpinner();


        binding.addMeetinfab.setOnClickListener(v -> {
            Intent intent = new Intent(ListMeetingActivity.this, AddMeetingActivity.class);
            startActivity(intent);
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter_by_date:
                filterMeetingsByDate();

                return true;
            case R.id.filter_by_room:

                filterMeetingsByRoom();
                initFilteredByRoomList();

                return true;
            case R.id.reset_filters:
                initList();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initFilteredByRoomList() {

        meetingsByRoom = apiService.getMeetingsByRoom();

        binding.recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        adapter = new ListMeetingRecyclerViewAdapter(meetingsByRoom);
        binding.recyclerView.setAdapter(adapter);

        adapter.setOnMeetingClickListener(new OnMeetingClickListener() {
            @Override
            public void onDelete(int position) {


                Meeting meetingToDelete;

                meetingToDelete = meetingsByRoom.get(position);

                deleteMeeting(meetingToDelete);

                meetingsByRoom.remove(position);


                adapter.notifyItemRemoved(position);

            }

            private void deleteMeeting(Meeting meetingToDelete) {

                for (int i = 0; i < meetings.size(); i++) {

                    Meeting currentMeeting = meetings.get(i);

                    if (currentMeeting.getMeetingId() == meetingToDelete.getMeetingId()) {

                        meetings.remove(currentMeeting);
                    }

                }
            }
        });


    }

    private void filterMeetingsByRoom() {

        binding.meetingRoomListSpinner.performClick();


        binding.meetingRoomListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                final String selectedMeetingRoom = binding.meetingRoomListSpinner.getSelectedItem().toString();


                meetingsByRoom.clear();


                for (int i = 0; i < meetings.size(); i++) {

                    Meeting currentMeeting = meetings.get(i);

                    if (currentMeeting.getMeetingRoom().getName().matches(selectedMeetingRoom)) {

                        meetingsByRoom.add(currentMeeting);

                    }

                }

                initFilteredByRoomList();

                if (meetingsByRoom.size() == 0) {


                    Toast.makeText(ListMeetingActivity.this, "No meetings planned for this room", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


    }

    private void setSpinner() {

        ArrayAdapter<MeetingRoom> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, apiService.getMeetingRooms());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.meetingRoomListSpinner.setAdapter(adapter);


    }


    private void filterMeetingsByDate() {

        getSelectedDate();

        // Opens a date dialog view
        // Once date selected, return the date on text format
        // With text format loop through apiservice.getmeetinglist
        // if getmeetinglist.get(i).getMeetingtime == text returned by date dialog
        // meetinglistbydate.add(i)


        meetingsByDate = apiService.getMeetingsByDate();
    }

    private String getSelectedDate() {

        DatePickerDialog.OnDateSetListener onDateSetListener = (view, year, month, dayOfMonth) -> {


            this.year = year;
            this.month = month + 1;
            this.dayOfMonth = dayOfMonth;


        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, onDateSetListener, year, month, dayOfMonth);
        datePickerDialog.show();

        return String.format(Locale.getDefault(), "%02d/%02d/%04d", this.dayOfMonth, this.month, this.year);


    }

    private void setView() {
        binding = ActivityMeetingListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }


    private void removeItem(int position) {




        meetings.remove(position);
        adapter.notifyItemRemoved(position);


    }


    @Override
    protected void onResume() {
        super.onResume();
        initList();


    }

    private void initList() {

        //TODO SWITCH CASE TO INIT EITHER meetings / meetingsByRoom / meetingsByDate

        meetings = apiService.getMeetings();

        binding.recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);

        // if CONDITION adapter = meedings else if CONDITION adapter = meetingsByRoom else if CONDITION adapter = meetingsByDate

        adapter = new ListMeetingRecyclerViewAdapter(meetings);
        binding.recyclerView.setAdapter(adapter);

        adapter.setOnMeetingClickListener(this::removeItem);


    }


}