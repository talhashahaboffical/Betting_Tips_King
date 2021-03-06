package com.bettingtipsking.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bettingtipsking.app.Helper.ItemClickListener;
import com.bettingtipsking.app.R;

import com.bettingtipsking.app.databinding.ItemFixturesBinding;
import com.bumptech.glide.Glide;

import java.util.List;

public class FixturesAdapter extends RecyclerView.Adapter<FixturesAdapter.FixturesViewHolder> {

    Context context;
    List<com.bettingtipsking.app.model.FinalFixturesModel> list;
    ItemClickListener listener;
    boolean h2h;

    public FixturesAdapter(Context context, List<com.bettingtipsking.app.model.FinalFixturesModel> list, ItemClickListener listener, boolean h2h) {
        this.context = context;
        this.list = list;
        this.listener = listener;
        this.h2h = h2h;

    }

    @NonNull
    @Override
    public FixturesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FixturesAdapter.FixturesViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_fixtures, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FixturesViewHolder holder, int position) {
        holder.binding.textLeagueName.setText(list.get(position).getLeague().getName());
        holder.binding.textCountryName.setText(list.get(position).getLeague().getCountry());
        holder.binding.textSeason.setText("Season(" + list.get(position).getLeague().getSeason() + ")");
        Glide.with(context).load(list.get(position).getLeague().getLogo()).into(holder.binding.imageLeagueIcon);

        MatchesAdapter adapter = new MatchesAdapter(context, list.get(position).getMatches(), listener, h2h);
        holder.binding.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.binding.recyclerView.setAdapter(adapter);


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FixturesViewHolder extends RecyclerView.ViewHolder {
        ItemFixturesBinding binding;

        public FixturesViewHolder(ItemFixturesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
