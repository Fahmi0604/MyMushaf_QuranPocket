package com.example.mymushaf.quran;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerQuranAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentListTitles = new ArrayList<>();

    public ViewPagerQuranAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentListTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return fragmentListTitles.get(position);
    }

    public void AddFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        fragmentListTitles.add(title);
    }
}
