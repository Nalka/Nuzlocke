package com.barkatit.nuzlocke.ui.party;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barkatit.nuzlocke.data.model.Pokemon;
import com.barkatit.nuzlocke.databinding.ItemPartyPokemonBinding;
import com.barkatit.nuzlocke.managers.SpriteManager;

import java.util.ArrayList;
import java.util.List;

public class PokemonPartyAdapter extends RecyclerView.Adapter<PokemonPartyAdapter.PokemonViewHolder> {

    private List<Pokemon> itemList = new ArrayList<>();
    private OnPokemonPartyItemClickListener listener;

    public PokemonPartyAdapter(OnPokemonPartyItemClickListener listener) {
        this.listener = listener;

    }

    public void setItemList(List<Pokemon> models) {
        itemList = models;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PokemonViewHolder(ItemPartyPokemonBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        Pokemon item = itemList.get(position);

        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class PokemonViewHolder extends RecyclerView.ViewHolder {

        private ItemPartyPokemonBinding binding;

        public PokemonViewHolder(@NonNull ItemPartyPokemonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final Pokemon pokemon) {
            binding.setModel(pokemon);
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                        listener.onPokemonPartyItemClick(pokemon);
                }
            });
            SpriteManager.loadImage(pokemon.getSpeciesId(), binding.ivSprite);
        }
    }

    public interface OnPokemonPartyItemClickListener {
        void onPokemonPartyItemClick(Pokemon pokemon);
    }


}
