package com.example.mymushaf.quran.listSurah.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushaf.MainActivity;
import com.example.mymushaf.R;
import com.example.mymushaf.quran.listSurah.models.SurahList;
import com.example.mymushaf.surah.SurahFragment;
import com.example.mymushaf.surah.models.Surah;

import java.util.List;

public class SurahListAdapter extends RecyclerView.Adapter<SurahListAdapter.SurahListAdapterViewHolder> {

    private Context context;
    private List<SurahList> surahLists;

    public SurahListAdapter(Context context, List<SurahList> surahLists) {
        this.context = context;
        this.surahLists = surahLists;
    }

    @NonNull
    @Override
    public SurahListAdapter.SurahListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_quran_listsurah, parent, false);
        SurahListAdapterViewHolder holder = new SurahListAdapterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SurahListAdapter.SurahListAdapterViewHolder holder, int position) {
        holder.number_of_ayah.setText(String.valueOf(surahLists.get(position).getNumber_of_ayah()) + "Ayat");
        holder.number_of_surah.setText(String.valueOf(surahLists.get(position).getNumber_of_surah()));
        holder.name.setText(surahLists.get(position).getName());
        holder.place.setText(surahLists.get(position).getName());
        holder.name_translations.setText(surahLists.get(position).getNameTranslations().getArab());

        holder.layout_listSurah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                SurahFragment surahFragment = new SurahFragment();
//                Bundle arguments = new Bundle();
//                arguments.putString("position", String.valueOf(surahLists.get(position).getNumber_of_surah()));
//                surahFragment.setArguments(arguments);
//                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.fragment_container, surahFragment, null).addToBackStack(null).commit();

                Bundle bundle = new Bundle();
                bundle.putString("position", String.valueOf(surahLists.get(position).getNumber_of_surah()));
                Navigation.findNavController(v).navigate(R.id.action_quranFragment_to_surahFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return surahLists.size();
    }

    public class SurahListAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView number_of_ayah, number_of_surah, name, place, name_translations;
        private ConstraintLayout layout_listSurah;
        public SurahListAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            number_of_ayah = itemView.findViewById(R.id.number_of_ayah);
            number_of_surah = itemView.findViewById(R.id.number_of_surah);
            name = itemView.findViewById(R.id.name);
            place = itemView.findViewById(R.id.place);
            name_translations = itemView.findViewById(R.id.name_translations);
            layout_listSurah = itemView.findViewById(R.id.layout_listSurah);
        }
    }
}
