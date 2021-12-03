package com.example.mareu.meeting_list;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mareu.callback.OnMeetingClickListener;
import com.example.mareu.databinding.ActivityMeetingListBinding;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.MeetingRoom;
import com.example.mareu.service.MeetingApiService;

import java.util.ArrayList;
import java.util.List;

public class ListMeetingActivity extends AppCompatActivity {

    private MeetingApiService apiService;
    private List<Meeting> meetings;
    private List<String> participants;
    private ListMeetingRecyclerViewAdapter adapter;


    private ActivityMeetingListBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        apiService = DI.getMeetingApiService();
        setView();
        initList();


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
        adapter = new ListMeetingRecyclerViewAdapter(this, meetings);
        binding.recyclerView.setAdapter(adapter);

        adapter.setOnMeetingClickListener(new OnMeetingClickListener() {
            @Override
            public void onDelete(int position) {
                removeItem(position);
            }
        });


    }


}