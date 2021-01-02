package com.example.mymushaf.quran.listSurah;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mymushaf.R;
import com.example.mymushaf.databinding.FragmentListSurahBinding;
import com.example.mymushaf.quran.listSurah.adapter.SurahListAdapter;
import com.example.mymushaf.quran.listSurah.models.SurahList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListSurahFragment extends Fragment {

    private FragmentListSurahBinding binding;
    private List<SurahList> surahLists;
    private SurahListAdapter surahListAdapter;

    public ListSurahFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_list_surah, container, false);
        View view = binding.getRoot();

        binding.RecyclerViewQuranListSurah.setLayoutManager(new LinearLayoutManager(getContext()));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/penggguna/QuranJSON/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SurahListPlaceHolderApi surahListPlaceHolderApi = retrofit.create(SurahListPlaceHolderApi.class);

        Call<List<SurahList>> call = surahListPlaceHolderApi.getSurahList();

        call.enqueue(new Callback<List<SurahList>>() {
            @Override
            public void onResponse(Call<List<SurahList>> call, Response<List<SurahList>> response) {

                if (response.isSuccessful()){
                    surahLists = response.body();
                    surahListAdapter = new SurahListAdapter(getActivity(), surahLists);
                    binding.RecyclerViewQuranListSurah.setAdapter(surahListAdapter);
                    Toast.makeText(getActivity(), "Berhasil", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<SurahList>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}