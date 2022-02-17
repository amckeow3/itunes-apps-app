

//  In-Class 05
//  Group8_InClass05
//  Adrianna McKeown

package com.example.group8_inclass05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements AppCategoriesFragment.AppCategoriesFragmentListener, AppsListFragment.AppsListFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerView, new AppCategoriesFragment(), "fragment")
                .commit();
    }

    @Override
    public void categorySelected(String category) {
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.containerView, AppsListFragment.newInstance(category), "fragment")
                .addToBackStack("user_list")
                .commit();
    }

    @Override
    public void appSelected(DataServices.App app) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, AppDetailsFragment.newInstance(app), "fragment")
                .addToBackStack("user_list")
                .commit();
    }
}