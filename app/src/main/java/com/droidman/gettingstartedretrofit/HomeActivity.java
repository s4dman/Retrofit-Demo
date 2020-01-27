package com.droidman.gettingstartedretrofit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.droidman.gettingstartedretrofit.Fragments.GetFragment;
import com.droidman.gettingstartedretrofit.Fragments.PostFragment;
import com.droidman.gettingstartedretrofit.Fragments.PutFragment;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigation = findViewById(R.id.navigationView);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(new GetFragment());
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.navigation_get:
                            openFragment(new GetFragment());
                            return true;
                        case R.id.navigation_post:
                            openFragment(new PostFragment());
                            return true;
                        case R.id.navigation_put:
                            openFragment(new PutFragment());
                            return true;
                    }
                    return false;
                }
            };

}
