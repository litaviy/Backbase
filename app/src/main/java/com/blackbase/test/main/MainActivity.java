package com.blackbase.test.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.blackbase.test.R;
import com.blackbase.test.common.Condition;
import com.blackbase.test.main.cities.CitiesLstFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment citiesListFragment = fragmentManager.findFragmentByTag(CitiesLstFragment.TAG);
        if (Condition.isNull(citiesListFragment)) {
            citiesListFragment = new CitiesLstFragment();
            fragmentManager.beginTransaction()
                    .add(
                            R.id.mainFrameLayout,
                            citiesListFragment,
                            CitiesLstFragment.TAG
                    )
                    .commit();
        }
    }
}
