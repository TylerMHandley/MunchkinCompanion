package com.example.munchkincompanion;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

public class NotificationFragment extends Fragment {

    protected RecyclerView mRecyclerView;
    protected NotificationAdapter mAdapter;
    protected LinearLayoutManager mLayoutManager;
    protected ArrayList<String> mNotifications;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        Log.d("FUCK", "onCreate");
        initDataset();
        mAdapter = new NotificationAdapter(mNotifications, getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("FUCK", "whyyyyyyyyyyyy");
        View rootView = inflater.inflate(R.layout.fragment_notification, container, false);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);


        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rvNotificationList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        Log.d("FUCK", "set the adapter");

        return rootView;
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */
    private void initDataset() {

        for (int i = 0; i < 60; i++) {
            mNotifications.add("This is string " + i);
        }
    }
}