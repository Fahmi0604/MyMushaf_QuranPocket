package com.example.mymushaf.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mymushaf.R;
import com.example.mymushaf.databinding.FragmentHomeBinding;
import com.example.mymushaf.home.models.Sholat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private Sholat sholat;
    private FragmentHomeBinding binding;
    public HomeFragment() {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        View view = binding.getRoot();

        getSholatApi();

        return view;
    }

    public void setupDataSholat(){

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String time1 = simpleDateFormat.format(calendar.getTime());

        Date timeNow=null;
        Date dzuhur=null;
        Date ashar=null;
        Date maghrib=null;
        Date isya=null;
        Date subuh=null;

        try {
            timeNow = simpleDateFormat.parse(time1);
            dzuhur = simpleDateFormat.parse(sholat.getSholatJadwal().getSholatJadwalData().getDzuhur());
            ashar = simpleDateFormat.parse(sholat.getSholatJadwal().getSholatJadwalData().getAshar());
            maghrib = simpleDateFormat.parse(sholat.getSholatJadwal().getSholatJadwalData().getMaghrib());
            isya = simpleDateFormat.parse(sholat.getSholatJadwal().getSholatJadwalData().getIsya());
            subuh = simpleDateFormat.parse(sholat.getSholatJadwal().getSholatJadwalData().getSubuh());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long hours;
        long minutes;

        if ((timeNow.before(isya) && timeNow.before(subuh)) || timeNow.after(isya)){
            binding.titleSholat.setText("Subuh");

            long timeDiff = subuh.getTime() - timeNow.getTime();
            Toast.makeText(getActivity(), String.valueOf(timeDiff), Toast.LENGTH_SHORT).show();


        } else if (timeNow.before(dzuhur) && timeNow.after(subuh)){
            binding.titleSholat.setText("Dzuhur");
        } else if (timeNow.before(ashar) && timeNow.after(dzuhur)){
            binding.titleSholat.setText("Ashar");
        } else if (timeNow.before(maghrib) && timeNow.after(ashar)){
            binding.titleSholat.setText("Maghrib");
        } else if (timeNow.before(isya) && timeNow.after(maghrib)){
            binding.titleSholat.setText("Isya");
        }

        startTime();
    }

    public void startTime(){
        new CountDownTimer(20000 + 100, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                binding.countdownSholat.setText(String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                binding.countdownSholat.setText("00:00");
                Toast.makeText(getActivity(), "waktunya Sholat", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    public void getSholatApi() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.banghasan.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SholatPlaceHolderApi sholatPlaceHolderApi = retrofit.create(SholatPlaceHolderApi.class);

        Call<Sholat> call = sholatPlaceHolderApi.getSholat("https://api.banghasan.com/sholat/format/json/jadwal/kota/754/tanggal/2021-01-03");

        call.enqueue(new Callback<Sholat>() {
            @Override
            public void onResponse(Call<Sholat> call, Response<Sholat> response) {

                if (response.isSuccessful()){
                    sholat = response.body();
                    setupDataSholat();
                    binding.setSholat(sholat);
                    Toast.makeText(getActivity(), sholat.getSholatJadwal().getSholatJadwalData().getIsya(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Sholat> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}