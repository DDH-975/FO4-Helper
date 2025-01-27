package com.project.fifamanagerdata;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.project.fifamanagerdata.navigation.FeeCalculation;
import com.project.fifamanagerdata.navigation.SearchManager;
import com.project.fifamanagerdata.navigation.SearchMatchHistory;
import com.project.fifamanagerdata.navigation.SearchPlayer;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bnView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private SearchManager searchManager;
    private SearchMatchHistory searchMatchHistory;
    private SearchPlayer searchPlayer;
    private FeeCalculation feeCalculation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        bnView = (BottomNavigationView) findViewById(R.id.bottomNav);
        searchManager = new SearchManager();
        searchMatchHistory = new SearchMatchHistory();
        searchPlayer = new SearchPlayer();
        feeCalculation = new FeeCalculation();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        bnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.search_manager) {
                    setView(0);
                } else if (item.getItemId() == R.id.search_match_history) {
                    setView(1);
                } else if (item.getItemId() == R.id.search_player) {
                    setView(2);
                } else if (item.getItemId() == R.id.fee_calculation) {
                    setView(3);
                }

                return true;
            }
        });

        setView(0);

    }


    private void setView(int n) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        switch (n) {
            case 0:
                fragmentTransaction.replace(R.id.main_frame, searchManager);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentTransaction.replace(R.id.main_frame, searchMatchHistory);
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentTransaction.replace(R.id.main_frame, searchPlayer);
                fragmentTransaction.commit();
                break;
            case 3:
                fragmentTransaction.replace(R.id.main_frame, feeCalculation);
                fragmentTransaction.commit();
                break;
        }

    }

}