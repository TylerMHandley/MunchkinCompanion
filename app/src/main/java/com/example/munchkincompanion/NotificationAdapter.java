package com.example.munchkincompanion;

import android.app.Notification;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by huntermurphy on 4/4/18.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            // Need to edit a whole bunch of shit
            nameTextView = (TextView) itemView.findViewById(R.id.notification_name);
        }
    }

    private List<String> mNotifications;
    private Context mContext;

    public NotificationAdapter(List<String> Notifications, Context context) {
        mNotifications = Notifications;
        mContext = context;
        this.notifyDataSetChanged();
    }

    private Context getmContext() {
        return mContext;
    }

    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View bucketView = inflater.inflate(R.layout.item_notification, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(bucketView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NotificationAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        String item = mNotifications.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.nameTextView;
        textView.setText(item);

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mNotifications.size();
    }
}

