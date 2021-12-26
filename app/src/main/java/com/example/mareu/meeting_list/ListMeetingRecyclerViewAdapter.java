package com.example.mareu.meeting_list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mareu.R;
import com.example.mareu.callback.OnMeetingClickListener;
import com.example.mareu.databinding.MeetingListIsEmptyBinding;
import com.example.mareu.databinding.MeetingListRowBinding;
import com.example.mareu.model.Meeting;

import java.util.List;

public class ListMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingViewHolder> {

    private static final int VIEW_TYPE_EMPTY = 0;
    private static final int VIEW_TYPE_MEETING_LIST = 1;


    private final List<Meeting> meetings;
    private OnMeetingClickListener listener;


    public ListMeetingRecyclerViewAdapter(List<Meeting> items) {
        meetings = items;

    }

    public void setOnMeetingClickListener(OnMeetingClickListener listener) {
        this.listener = listener;

    }


    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());


        if (viewType == VIEW_TYPE_EMPTY) {

            MeetingListIsEmptyBinding binding = MeetingListIsEmptyBinding.inflate(layoutInflater, parent, false);
            return new MeetingViewHolder(binding);

        }

        MeetingListRowBinding binding = MeetingListRowBinding.inflate(layoutInflater, parent, false);
        return new MeetingViewHolder(binding, listener);

    }


    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {

        int viewType = getItemViewType(position);

        if (viewType == VIEW_TYPE_EMPTY) {

            holder.emptyBinding.textView.setText(R.string.No_Meetings);
            holder.emptyBinding.textView2.setText(R.string.No_Meetings_Text);

        } else {


            Meeting meeting = meetings.get(position);

            String meetingDetail = meeting.getMeetingTopic() + " - " + meeting.getMeetingTime() + " - Salle " + meeting.getMeetingRoom().getName();


            holder.rowBinding.itemListMeetingInfo.setText(meetingDetail);
            holder.rowBinding.itemListParticipantsEmail.setText(meeting.getMeetingParticipants().toString().replace("[", "").replace("]", ""));
            Glide.with(holder.rowBinding.itemListImageview.getContext())
                    .load(meeting.getMeetingRoom().getRoomPictureUrl())
                    .apply(RequestOptions.circleCropTransform())
                    .into(holder.rowBinding.itemListImageview);

        }


    }


    @Override
    public int getItemViewType(int position) {
        if (meetings.size() == 0) {
            return VIEW_TYPE_EMPTY;
        } else {
            return VIEW_TYPE_MEETING_LIST;

        }
    }

    @Override
    public int getItemCount() {
        if (meetings.size() == 0) {
            return 1;
        } else {
            return meetings.size();
        }

    }


}
