package com.example.mareu.meeting_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mareu.R;
import com.example.mareu.databinding.ActivityMeetingListBinding;
import com.example.mareu.databinding.MeetingListRowBinding;
import com.example.mareu.model.Meeting;

import java.util.List;

public class ListMeetingRecyclerViewAdapter extends RecyclerView.Adapter<ListMeetingRecyclerViewAdapter.ViewHolder> {


    private final List<Meeting> mMeetings;
    private final onMeetingClickListener mListener;

    public interface onMeetingClickListener {

        void onDelete(int position);
    }

    public ListMeetingRecyclerViewAdapter(List<Meeting> meetingList, onMeetingClickListener listener) {
        this.mMeetings = meetingList;
        this.mListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MeetingListRowBinding binding = MeetingListRowBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // Change to placeholder later
        Meeting meeting = mMeetings.get(position);
        holder.mRowBinding.itemListText1.setText(meeting.getMeetingTopic() + " - " + meeting.getMeetingTime() + " - " + meeting.getMeetingRoom().getName());
        Glide.with(holder.mRowBinding.itemListImageview.getContext())
                .load(meeting.getMeetingRoom().getRoomPictureUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mRowBinding.itemListImageview);


    }

    @Override
    public int getItemCount() {
        return mMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements onMeetingClickListener {

        private MeetingListRowBinding mRowBinding;
        onMeetingClickListener mOnMeetingClickListener;


        public ViewHolder(@NonNull MeetingListRowBinding itemBinding, onMeetingClickListener listener) {
            super(itemBinding.getRoot());

            mRowBinding = itemBinding;
        }

        @Override
        public void onDelete(int position) {

        }
    }
}
