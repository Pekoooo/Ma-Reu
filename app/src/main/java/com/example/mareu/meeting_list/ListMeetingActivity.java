package com.example.mareu.meeting_list;

import android.app.DatePickerDialog;
import android.content.Context;
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
import com.example.mareu.databinding.ActivityMeetingListBinding;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;
import com.example.mareu.service.MeetingApiService;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ListMeetingActivity extends AppCompatActivity {

    private MeetingApiService apiService;
    private List<Meeting> meetings;
    private List<Meeting> meetingsByRoom;
    private List<Meeting> meetingsByDate;
    private ListMeetingRecyclerViewAdapter adapter;
    private String DateSet;
    private Context context;

    Calendar calendar = Calendar.getInstance();

    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

    private ActivityMeetingListBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiService = DI.getMeetingApiService();
        meetingsByDate = apiService.getMeetingsByDate();
        meetingsByRoom = apiService.getMeetingsByRoom();
        setView();
        setSpinner();


        binding.addMeetinfab.setOnClickListener(v -> {
            Intent intent = new Intent(ListMeetingActivity.this, AddMeetingActivity.class);
            startActivity(intent);
        });


    }


    private void setView() {
        binding = ActivityMeetingListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    private void setSpinner() {

        ArrayAdapter<MeetingRoom> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, apiService.getMeetingRooms());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.meetingRoomListSpinner.setAdapter(adapter);


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

                getSelectedDate();

                return true;
            case R.id.filter_by_room:


                filterMeetingsByRoom();
                initFilteredList(meetingsByRoom);

                return true;
            case R.id.reset_filters:

                initList();
                meetingsByDate.clear();
                meetingsByRoom.clear();

                return true;
        }
        return super.onOptionsItemSelected(item);
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

                initFilteredList(meetingsByRoom);

                if (meetingsByRoom.size() == 0) {

                    Toast.makeText(ListMeetingActivity.this, "No meetings planned for this room", Toast.LENGTH_SHORT).show();
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


    }

    private void initFilteredList(List<Meeting> filteredList) {

        binding.recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        adapter = new ListMeetingRecyclerViewAdapter(filteredList);
        binding.recyclerView.setAdapter(adapter);

        adapter.setOnMeetingClickListener(position -> {

            apiService.deleteMeetingFromFilteredList(position, filteredList);

            adapter.notifyItemRemoved(position);

            if (filteredList.size() < 1) {
                initList();

                Toast.makeText(this, "No meetings for this room, filters have been reset", Toast.LENGTH_LONG).show();
            }

        });

    }


    private void getSelectedDate() {

        DatePickerDialog.OnDateSetListener onDateSetListener = (view, year, month, dayOfMonth) -> {

            this.year = year;
            this.month = month;
            this.dayOfMonth = dayOfMonth;

            DateSet = String.format(Locale.getDefault(), "%02d/%02d/%04d", this.dayOfMonth, month + 1, this.year);

            filterMeetingsByDate();

        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, onDateSetListener, year, month, dayOfMonth);
        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();

    }

    private void filterMeetingsByDate() {

        final String selectedDate = DateSet;

        meetingsByDate.clear();

        for (int i = 0; i < meetings.size(); i++) {

            Meeting currentMeeting = meetings.get(i);

            if (currentMeeting.getMeetingDate().matches(selectedDate)) {
                meetingsByDate.add(currentMeeting);
            }
        }

        initFilteredList(meetingsByDate);

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
        meetings = apiService.getMeetings();
        binding.recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        adapter = new ListMeetingRecyclerViewAdapter(meetings);
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnMeetingClickListener(this::removeItem);


    }


}