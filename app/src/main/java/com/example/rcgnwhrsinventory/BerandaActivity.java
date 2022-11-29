package com.example.rcgnwhrsinventory;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.example.rcgnwhrsinventory.Fragment.HomeFragment;
import com.example.rcgnwhrsinventory.Fragment.ProfilFragment;
import com.example.rcgnwhrsinventory.Fragment.SavedFragment;
import com.example.rcgnwhrsinventory.Fragment.ScanFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BerandaActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    RelativeLayout mToolbarLayout;
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        bottomNav = findViewById(R.id.bottom_navigation);
        mToolbarLayout = findViewById(R.id.mainToolbar);

        if (savedInstanceState == null) {
            bottomNav.setSelectedItemId(R.id.home);
            fragmentManager = getSupportFragmentManager();
            HomeFragment homeFragment = new HomeFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, homeFragment)
                    .commit();
        }

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.scan:
                        fragment = new ScanFragment();
                        break;
                    case R.id.saved:
                        fragment = new SavedFragment()  ;
                        break;
                    case R.id.profile:
                        fragment = new ProfilFragment();
                        break;

                }

                if (fragment != null) {
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                } else {
                    Log.e(TAG, "Error in creating fragment");
                }
                return true;
            }
        });

    }
}