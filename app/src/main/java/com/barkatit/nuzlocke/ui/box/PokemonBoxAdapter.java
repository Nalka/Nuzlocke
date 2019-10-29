package com.barkatit.nuzlocke.ui.box;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.barkatit.nuzlocke.data.model.Pokemon;
import com.barkatit.nuzlocke.databinding.ItemBoxPokemonBinding;
import com.barkatit.nuzlocke.managers.SpriteManager;

import java.util.List;

public class PokemonBoxAdapter extends RecyclerView.Adapter<PokemonBoxAdapter.PokemonViewHolder> {

    private SortedList<Pokemon> itemList;
    private OnPokemonBoxItemClickListener listener;

    public PokemonBoxAdapter(OnPokemonBoxItemClickListener listener) {
        this.listener = listener;
        itemList = new SortedList<>(Pokemon.class, sortedListCallback);
    }

    public void add(Pokemon pokemonModel) {
        itemList.add(pokemonModel);
    }

    public void remove(Pokemon pokemonModel) {
        itemList.remove(pokemonModel);
    }

    public void setItemList(List<Pokemon> models) {
        itemList.beginBatchedUpdates();
        for (int i = itemList.size() - 1; i >= 0; i--) {
            final Pokemon model = itemList.get(i);
            if (!models.contains(model)) {
                itemList.remove(model);
            }
        }
        itemList.addAll(models);
        itemList.endBatchedUpdates();
    }

    public void add(List<Pokemon> models) {
        itemList.addAll(models);
    }

    public void remove(List<Pokemon> models) {
        itemList.beginBatchedUpdates();
        for (Pokemon model : models) {
            itemList.remove(model);
        }
        itemList.endBatchedUpdates();
    }
    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PokemonViewHolder(ItemBoxPokemonBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
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

        private ItemBoxPokemonBinding binding;

        public PokemonViewHolder(@NonNull ItemBoxPokemonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final Pokemon pokemon) {
            binding.setModel(pokemon);
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null)
                        listener.onPokemonBoxItemClick(pokemon);
                }
            });
            SpriteManager.loadImage(pokemon.getSpeciesId(), binding.ivSprite);
        }
    }

    public interface OnPokemonBoxItemClickListener {
        void onPokemonBoxItemClick(Pokemon pokemon);
    }

    private final SortedList.Callback<Pokemon> sortedListCallback = new SortedList.Callback<Pokemon>() {
        @Override
        public int compare(Pokemon o1, Pokemon o2) {
            return o1.getName().compareToIgnoreCase(o2.getName());
        }

        @Override
        public void onChanged(int position, int count) {
            notifyItemRangeChanged(position, count);
        }

        @Override
        public boolean areContentsTheSame(Pokemon oldItem, Pokemon newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areItemsTheSame(Pokemon item1, Pokemon item2) {
            return item1.equals(item2);
        }

        @Override
        public void onInserted(int position, int count) {
            notifyItemRangeInserted(position, count);
        }

        @Override
        public void onRemoved(int position, int count) {
            notifyItemRangeRemoved(position, count);
        }

        @Override
        public void onMoved(int fromPosition, int toPosition) {
            notifyItemMoved(fromPosition, toPosition);
        }
    };
}
