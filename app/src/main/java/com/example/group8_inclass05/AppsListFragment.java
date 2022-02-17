package com.example.group8_inclass05;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class AppsListFragment extends Fragment {

    private static final String TAG = "Apps List";
    AppsListFragment.AppsListFragmentListener  mListener;

    private static final String ARG_CATEGORY = "category";
    ArrayList<DataServices.App> apps = new ArrayList<>();
    ListView listView;
    AppAdapter adapter;

    private String category;

    public AppsListFragment() {
        // Required empty public constructor
    }

    public static AppsListFragment newInstance(String category) {
        AppsListFragment fragment = new AppsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString(ARG_CATEGORY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apps_list, container, false);

        ArrayList<DataServices.App> apps = DataServices.getAppsByCategory(category);
        listView = view.findViewById(R.id.listViewAppList);
        adapter = new AppAdapter(getContext(), R.layout.application_row_item, apps);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataServices.App app = apps.get(position);
                mListener.appSelected(app);
            }
        });

        return view;
    }

    class AppAdapter extends ArrayAdapter<DataServices.App> {
        public AppAdapter(@NonNull Context context, int resource, @NonNull List<DataServices.App> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) { //Tells adapter how to create the rows
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.application_row_item, parent, false);
            }

            DataServices.App app = getItem(position);

            TextView textViewName = convertView.findViewById(R.id.textViewAppName);
            TextView textViewArtist = convertView.findViewById(R.id.textViewArtist);
            TextView textViewReleaseDate = convertView.findViewById(R.id.textViewReleaseDate);

            textViewName.setText(app.name);
            textViewArtist.setText(app.artistName);
            textViewReleaseDate.setText(app.releaseDate);

            return convertView;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //  Activity title is set to the selected category name
        getActivity().setTitle(category);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (AppsListFragment.AppsListFragmentListener) context;
    }

    public interface AppsListFragmentListener {
        void appSelected(DataServices.App app);
    }
}