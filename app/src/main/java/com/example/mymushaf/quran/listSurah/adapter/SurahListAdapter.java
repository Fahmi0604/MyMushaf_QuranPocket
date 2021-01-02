package com.example.mymushaf.quran.listSurah.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushaf.R;
import com.example.mymushaf.quran.listSurah.models.SurahList;

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
    }

    @Override
    public int getItemCount() {
        return surahLists.size();
    }

    public class SurahListAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView number_of_ayah, number_of_surah, name, place, name_translations;

        public SurahListAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            number_of_ayah = itemView.findViewById(R.id.number_of_ayah);
            number_of_surah = itemView.findViewById(R.id.number_of_surah);
            name = itemView.findViewById(R.id.name);
            place = itemView.findViewById(R.id.place);
            name_translations = itemView.findViewById(R.id.name_translations);
        }
    }
}
