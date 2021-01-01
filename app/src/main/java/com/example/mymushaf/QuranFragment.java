package com.example.mymushaf;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class QuranFragment extends Fragment {

    private FragmentQuranBinding binding;
    private Surah surahList;
    private LinearLayoutManager linearLayoutManager;
    private SurahAdapter surahAdapter;
    private RecyclerView recyclerView;

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
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quran, container, false);
        View view = binding.getRoot();

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
                    surahAdapter = new SurahAdapter(getActivity(), surahList);
                    binding.RecyclerViewSurah.setAdapter(surahAdapter);
                    Toast.makeText(getActivity(), "berhasil" + response.body().getSurah_verses().get(0).getAyat(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Surah> call, Throwable t) {
                Toast.makeText(getActivity(), "Failure : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}