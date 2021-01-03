package com.example.mymushaf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.mymushaf.quran.QuranFragment;
import com.example.mymushaf.surah.SurahFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FragmentManager manager = getSupportFragmentManager();
//        manager.beginTransaction().add(R.id.fragment_container, new SurahFragment()).commit();

    }
}