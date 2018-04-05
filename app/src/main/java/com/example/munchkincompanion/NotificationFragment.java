package com.example.munchkincompanion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        setHasOptionsMenu(true);
        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        Log.d("FUCK", "onCreate");
        mNotifications = new ArrayList<String>();
        //initDataset();
        mAdapter = new NotificationAdapter(mNotifications, getActivity());
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.notification, menu);
        menu.findItem(R.id.mode1).setVisible(false);
        menu.findItem(R.id.mode2).setVisible(false);
        menu.findItem(R.id.mode3).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.create_notification) {
            Intent add_intent = new Intent(getActivity(), AddNotificationActivity.class);
            startActivityForResult(add_intent, 1);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK) {
            mNotifications.add(0, data.getStringExtra("Notification"));
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */
    private void initDataset() {

        for (int i = 0; i < 60; i++) {
            String add = "This is notification" + i;

            mNotifications.add(add);
        }
    }
}