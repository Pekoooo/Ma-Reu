package com.example.mareu.meeting_list;

import androidx.annotation.NonNull;

import com.example.mareu.callback.OnMeetingClickListener;
import com.example.mareu.databinding.MeetingListIsEmptyBinding;

public class EmptyMeetingViewHolder  {

    MeetingListIsEmptyBinding emptyListBinding;

    public EmptyMeetingViewHolder(@NonNull MeetingListIsEmptyBinding itemBinding) {
        super();

        emptyListBinding = itemBinding;
    }
}
