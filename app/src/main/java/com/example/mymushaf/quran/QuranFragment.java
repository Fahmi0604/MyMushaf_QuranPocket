package com.example.mymushaf.quran;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymushaf.R;
import com.example.mymushaf.databinding.FragmentQuranBinding;
import com.example.mymushaf.quran.listSurah.ListSurahFragment;
import com.example.mymushaf.quran.listSurah.adapter.SurahListAdapter;

public class QuranFragment extends Fragment {

    private FragmentQuranBinding binding;

    public QuranFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quran, container, false);
        View view = binding.getRoot();

        setupViewPager();

        return view;
    }

    public void setupViewPager() {
        ViewPagerQuranAdapter adapter = new ViewPagerQuranAdapter(getParentFragmentManager());
        adapter.AddFragment(new ListSurahFragment(), "Surat");
        adapter.AddFragment(new ListSurahFragment(), "Kutipan");
        binding.viewPager.setAdapter(adapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }


}