package com.example.mareu.meeting_list;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.callback.OnMeetingClickListener;
import com.example.mareu.databinding.MeetingListIsEmptyBinding;
import com.example.mareu.databinding.MeetingListRowBinding;

public class MeetingViewHolder extends RecyclerView.ViewHolder {

    MeetingListRowBinding rowBinding;
    MeetingListIsEmptyBinding emptyBinding;

    public MeetingViewHolder(@NonNull MeetingListRowBinding itemBinding, OnMeetingClickListener listener) {
        super(itemBinding.getRoot());

        rowBinding = itemBinding;

        rowBinding.itemListDeleteButton.setOnClickListener(v -> {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    listener.onDelete(position);
                }
            }
        });

    }

    public MeetingViewHolder(MeetingListIsEmptyBinding emptyBinding) {
        super(emptyBinding.getRoot());

        this.emptyBinding = emptyBinding;


    }

}

