package com.example.mymushaf.home;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mymushaf.R;
import com.example.mymushaf.databinding.FragmentHomeBinding;
import com.example.mymushaf.home.alarm.AlertReceiver;
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

        binding.gotoQuran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_quranFragment);
            }
        });

        return view;
    }

    public void setupAlarm(Date mili){
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent( getActivity(), AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 1, intent, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, mili.getTime(), pendingIntent);

    }

    public void setupDataSholat(){

        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time1 = simpleDateFormat1.format(calendar.getTime());

        Date timeNow=null;
        Date dzuhur=null;
        Date ashar=null;
        Date maghrib=null;
        Date isya=null;
        Date subuh=null;

        try {
            timeNow = simpleDateFormat1.parse(time1);
            dzuhur = simpleDateFormat1.parse(sholat.getSholat_query().getTanggal()+" "+sholat.getSholatJadwal().getSholatJadwalData().getDzuhur());
            ashar = simpleDateFormat1.parse(sholat.getSholat_query().getTanggal()+" "+sholat.getSholatJadwal().getSholatJadwalData().getAshar());
            maghrib = simpleDateFormat1.parse(sholat.getSholat_query().getTanggal()+" "+sholat.getSholatJadwal().getSholatJadwalData().getMaghrib());
            isya = simpleDateFormat1.parse(sholat.getSholat_query().getTanggal()+" "+sholat.getSholatJadwal().getSholatJadwalData().getIsya());
            subuh = simpleDateFormat1.parse(sholat.getSholat_query().getTanggal()+" "+sholat.getSholatJadwal().getSholatJadwalData().getSubuh());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long hours;
        long minutes;

        if ((timeNow.before(isya) && timeNow.before(subuh)) || timeNow.after(isya)){

            binding.titleSholat.setText("Subuh");
            long timeDiff = subuh.getTime() - timeNow.getTime();
            startTime(timeDiff);

            setupAlarm(subuh);

        } else if (timeNow.before(dzuhur) && timeNow.after(subuh)){

            binding.titleSholat.setText("Dzuhur");
            long timeDiff = dzuhur.getTime() - timeNow.getTime();
            startTime(timeDiff);

            setupAlarm(dzuhur);

        } else if (timeNow.before(ashar) && timeNow.after(dzuhur)){

            binding.titleSholat.setText("Ashar");
            long timeDiff = ashar.getTime() - timeNow.getTime();
            startTime(timeDiff);

            setupAlarm(ashar);

        } else if (timeNow.before(maghrib) && timeNow.after(ashar)){

            binding.titleSholat.setText("Maghrib");
            long timeDiff = maghrib.getTime() - timeNow.getTime();
            startTime(timeDiff);

            setupAlarm(maghrib);

        } else if (timeNow.before(isya) && timeNow.after(maghrib)){

            binding.titleSholat.setText("Isya");
            long timeDiff = isya.getTime() - timeNow.getTime();
            startTime(timeDiff);

            setupAlarm(isya);

        }

    }

    public void startTime(long countMili){
        new CountDownTimer(countMili, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

                String myTime = String.format("%02d:%02d:%02d", millisUntilFinished / (3600*1000),
                        millisUntilFinished/(60*1000) % 60,
                        millisUntilFinished/1000 % 60);

                binding.countdownSholat.setText(myTime);
            }

            @Override
            public void onFinish() {
                binding.countdownSholat.setText("00:00");
                Toast.makeText(getActivity(), "waktunya Sholat", Toast.LENGTH_SHORT).show();
            }
        }.start();
    }

    public void getSholatApi() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = simpleDateFormat1.format(calendar.getTime());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.banghasan.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SholatPlaceHolderApi sholatPlaceHolderApi = retrofit.create(SholatPlaceHolderApi.class);

        Call<Sholat> call = sholatPlaceHolderApi.getSholat("https://api.banghasan.com/sholat/format/json/jadwal/kota/754/tanggal/"+dateNow);

        call.enqueue(new Callback<Sholat>() {
            @Override
            public void onResponse(Call<Sholat> call, Response<Sholat> response) {

                if (response.isSuccessful()){
                    sholat = response.body();
                    setupDataSholat();
                    binding.setSholat(sholat);
                    Toast.makeText(getActivity(), "ٱلسَّلَامُ عَلَيْكُمْ\u200E ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Sholat> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}