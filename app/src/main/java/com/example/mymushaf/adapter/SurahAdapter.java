package com.example.mymushaf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymushaf.R;
import com.example.mymushaf.databinding.FragmentQuranBinding;
import com.example.mymushaf.models.Surah;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;

import java.util.List;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.SurahAdapterViewHolder> {

    FragmentQuranBinding fragmentQuranBinding;
    private Context context;
    private Surah surahList;

    public SurahAdapter(Context context, Surah surahList) {
        this.context = context;
        this.surahList = surahList;
    }

    @NonNull
    @Override
    public SurahAdapter.SurahAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        SurahAdapterViewHolder holder = new SurahAdapterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SurahAdapter.SurahAdapterViewHolder holder, int position) { ;
        holder.text.setText(surahList.getSurah_verses().get(position).getAyat());
        holder.translation_id.setText(surahList.getSurah_verses().get(position).getTranslation_id());
    }

    @Override
    public int getItemCount() {
        return surahList.getNumber_of_ayah();
    }

    public class SurahAdapterViewHolder extends RecyclerView.ViewHolder{

        private TextView text, translation_id;

        public SurahAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            translation_id = itemView.findViewById(R.id.translation_id);
        }
    }
}
