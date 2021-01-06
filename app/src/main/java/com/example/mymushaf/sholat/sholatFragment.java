package com.example.mymushaf.sholat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mymushaf.R;
import com.example.mymushaf.databinding.FragmentSholatBinding;
import com.example.mymushaf.home.SholatPlaceHolderApi;
import com.example.mymushaf.home.getKota.Kota;
import com.example.mymushaf.home.getKota.KotaPlaceHolderApi;
import com.example.mymushaf.home.models.Sholat;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class sholatFragment extends Fragment {

    private FragmentSholatBinding binding;
    private Sholat sholat;
    private Kota kotaList;
    private String kodeKota;
    private String kota;
    private String dateNow;
    private FusedLocationProviderClient fusedLocationProviderClient;

    public sholatFragment() {
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
         binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sholat, container, false);
         View view = binding.getRoot();

         setupLocationService();

         return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

    private void blink() {
        final Handler hander = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(550);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hander.post(new Runnable() {
                    @Override
                    public void run() {
                        if(binding.timeNow.getVisibility() == View.VISIBLE) {
                            binding.timeNow.setVisibility(View.INVISIBLE);
                        } else {
                            binding.timeNow.setVisibility(View.VISIBLE);
                        }
                        blink();
                    }
                });
            }
        }).start();
    }

    public void setupLocationService() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getLocation();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

    }

    public void getLocation() {

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );

                        kota = String.valueOf(addresses.get(0).getSubAdminArea());


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                getKodeKotaApi();
            }
        });
    }

    public void getSholatApi() {

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        dateNow = simpleDateFormat1.format(calendar.getTime());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.banghasan.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SholatPlaceHolderApi sholatPlaceHolderApi = retrofit.create(SholatPlaceHolderApi.class);

        Call<Sholat> call = sholatPlaceHolderApi.getSholat("https://api.banghasan.com/sholat/format/json/jadwal/kota/"+kodeKota+"/tanggal/" + dateNow);

        call.enqueue(new Callback<Sholat>() {
            @Override
            public void onResponse(Call<Sholat> call, Response<Sholat> response) {

                if (response.isSuccessful()) {
                    sholat = response.body();
                    Toast.makeText(getActivity(), "List Sholat", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Sholat> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        binding.locationNow.setText(kota);
        binding.timeNow.setText(dateNow);
    }

    public void getKodeKotaApi() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.banghasan.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        KotaPlaceHolderApi kotaPlaceHolderApi = retrofit.create(KotaPlaceHolderApi.class);

        Call<Kota> call = kotaPlaceHolderApi.getKota("https://api.banghasan.com/sholat/format/json/kota/nama/"+kota);

        call.enqueue(new Callback<Kota>() {
            @Override
            public void onResponse(Call<Kota> call, Response<Kota> response) {

                if (response.isSuccessful()){
                    kotaList  = response.body();

                    for (int i=0; i < kotaList.getKota_details().size(); i++){
                        if (kotaList.getKota_details().get(i).getNama().equalsIgnoreCase(kota)){
                            kodeKota = kotaList.getKota_details().get(i).getId();
                        }
                    }

                    getSholatApi();
                }

            }

            @Override
            public void onFailure(Call<Kota> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}