package com.example.mareu.meeting_list;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mareu.databinding.ActivityMeetingListBinding;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;
import com.example.mareu.service.MeetingApiService;

import java.util.ArrayList;
import java.util.List;

public class ListMeetingActivity extends AppCompatActivity implements ListMeetingRecyclerViewAdapter.onMeetingClickListener {

    private List<Meeting> meetings;
    private List<String> participants;
    private MeetingApiService apiService;


    private ActivityMeetingListBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMeetingListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        apiService = DI.getMeetingApiService();
        meetings = apiService.getMeetings();
        initList();


        binding.addMeetinfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListMeetingActivity.this, AddMeetingActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: is called");
        super.onResume();
        initList();


    }

    private void initList() {

        apiService.getMeetings();
        initRecyclerView();


        // meetings.add(new Meeting("Feature 1", "10h00", new MeetingRoom("Mind Expansion Mansion", "https://i.pravatar.cc/150?u=a042581f4e29026704d", 1, 10, true)));
        // meetings.add(new Meeting("Feature 1", "10h00", new MeetingRoom("Mind Expansion Mansion", "https://i.pravatar.cc/150?u=a042581f4e29026704d", 1, 10, true)));
        // meetings.add(new Meeting("Feature 1", "10h00", new MeetingRoom("Mind Expansion Mansion", "https://i.pravatar.cc/150?u=a042581f4e29026704d", 1, 10, true)));
        // meetings.add(new Meeting("Feature 1", "10h00", new MeetingRoom("Mind Expansion Mansion", "https://i.pravatar.cc/150?u=a042581f4e29026704d", 1, 10, true)));
        // meetings.add(new Meeting("Feature 1", "10h00", new MeetingRoom("Mind Expansion Mansion", "https://i.pravatar.cc/150?u=a042581f4e29026704d", 1, 10, true)));
        // meetings.add(new Meeting("Feature 1", "10h00", new MeetingRoom("Mind Expansion Mansion", "https://i.pravatar.cc/150?u=a042581f4e29026704d", 1, 10, true)));
        // meetings.add(new Meeting("Feature 1", "10h00", new MeetingRoom("Mind Expansion Mansion", "https://i.pravatar.cc/150?u=a042581f4e29026704d", 1, 10, true)));
        // meetings.add(new Meeting("Feature 1", "10h00", new MeetingRoom("Mind Expansion Mansion", "https://i.pravatar.cc/150?u=a042581f4e29026704d", 1, 10, true)));
        // meetings.add(new Meeting("Feature 1", "10h00", new MeetingRoom("Mind Expansion Mansion", "https://i.pravatar.cc/150?u=a042581f4e29026704d", 1, 10, true)));
        // meetings.add(new Meeting("Feature 1", "10h00", new MeetingRoom("Mind Expansion Mansion", "https://i.pravatar.cc/150?u=a042581f4e29026704d", 1, 10, true)));
        // meetings.add(new Meeting("Feature 1", "10h00", new MeetingRoom("Mind Expansion Mansion", "https://i.pravatar.cc/150?u=a042581f4e29026704d", 1, 10, true)));



    }


    private void initRecyclerView() {

        binding.recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        ListMeetingRecyclerViewAdapter adapter = new ListMeetingRecyclerViewAdapter(meetings, this);

        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(adapter);

    }

    @Override
    public void onDelete(int position) {

    }
}