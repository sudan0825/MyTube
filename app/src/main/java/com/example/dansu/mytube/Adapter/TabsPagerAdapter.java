package com.example.dansu.mytube.Adapter;

/**
 * Created by dansu on 10/9/15.
 */
import com.example.dansu.mytube.FragmentFavorite;
import com.example.dansu.mytube.FragmentPlayer;
import com.example.dansu.mytube.PlayerActivity;
import com.example.dansu.mytube.FragmentSearch;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // search Fragment
                return new FragmentSearch();
            case 1:
                // favorite Fragment
                return new FragmentFavorite();

        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

}