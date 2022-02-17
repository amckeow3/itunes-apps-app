package com.example.group8_inclass05;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AppDetailsFragment extends Fragment {

    private static final String ARG_APP = "param1";
    private static final String TAG = "APP DETAILS: ";
    private DataServices.App app;
    ListView listView;
    private ArrayList<String> genres;
    ArrayAdapter<String> adapter;

    public AppDetailsFragment() {
        // Required empty public constructor
    }

    public static AppDetailsFragment newInstance(DataServices.App app) {
        AppDetailsFragment fragment = new AppDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_APP, app);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            app = (DataServices.App) getArguments().getSerializable(ARG_APP);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_app_details, container, false);
        Log.d(TAG, "app selected: " + app);
        genres = new ArrayList<String>();

        for (String genre : app.genres) {
            genres.add(genre);
        }

        listView = view.findViewById(R.id.listViewGenres);
        TextView viewName = view.findViewById(R.id.textViewDetailsName);
        TextView viewArtist = view.findViewById(R.id.textViewDetailsArtist);
        TextView viewReleaseDate = view.findViewById(R.id.textViewDetailsReleaseDate);
        viewName.setText(app.name);
        viewArtist.setText(app.artistName);
        viewReleaseDate.setText(app.releaseDate);

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, genres);
        listView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(app.name);
    }
}