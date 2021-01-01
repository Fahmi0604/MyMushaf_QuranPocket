package com.example.mymushaf;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mymushaf.adapter.SurahAdapter;
import com.example.mymushaf.databinding.FragmentQuranBinding;
import com.example.mymushaf.models.Surah;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class QuranFragment extends Fragment {

    private FragmentQuranBinding binding;
    private Surah surahList;
    private SurahAdapter surahAdapter;
    private MediaPlayer mediaPlayer;
    private String url="";

    public QuranFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quran, container, false);
        View view = binding.getRoot();

        getSurahAPI();


        return view;
    }

    public void getAudio() {
        mediaPlayer = new MediaPlayer();
        binding.buttonAudioPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    binding.buttonAudioPlay.setImageResource(R.drawable.ic_baseline_play_circle_24);
                } else {
                    mediaPlayer.start();
                    binding.buttonAudioPlay.setImageResource(R.drawable.ic_baseline_pause_circle_24);
                    binding.buttonAudioRefresh.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.buttonAudioRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.reset();
                binding.buttonAudioRefresh.setVisibility(View.INVISIBLE);
                binding.buttonAudioPlay.setImageResource(R.drawable.ic_baseline_play_circle_24);
                prepareMediaPlayer();
            }
        });

        prepareMediaPlayer();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                binding.buttonAudioPlay.setImageResource(R.drawable.ic_baseline_play_circle_24);
                binding.buttonAudioRefresh.setVisibility(View.INVISIBLE);
                mediaPlayer.reset();
                prepareMediaPlayer();
            }
        });
    }

    public void prepareMediaPlayer(){
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
        } catch (Exception e){
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void getSurahAPI() {
        binding.RecyclerViewSurah.setLayoutManager(new LinearLayoutManager(getContext()));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SurahPlaceHolderApi surahPlaceHolderApi = retrofit.create(SurahPlaceHolderApi.class);

        Call<Surah> call = surahPlaceHolderApi.getSurah("https://raw.githubusercontent.com/penggguna/QuranJSON/master/surah/1.json");

        call.enqueue(new Callback<Surah>() {
            @Override
            public void onResponse(Call<Surah> call, Response<Surah> response) {

                if (response.isSuccessful()){
                    surahList = response.body();
                    url = response.body().getSurah_recitations().get(2).getAudio_url();
                    surahAdapter = new SurahAdapter(getActivity(), surahList);
                    binding.RecyclerViewSurah.setAdapter(surahAdapter);
                    binding.setSurah(surahList);
                    getAudio();
                    Toast.makeText(getActivity(), response.body().getSurah_verses().get(0).getAyat(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Surah> call, Throwable t) {
                Toast.makeText(getActivity(), "Failure : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}