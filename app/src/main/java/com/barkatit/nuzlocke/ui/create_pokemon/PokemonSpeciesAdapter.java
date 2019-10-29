package com.barkatit.nuzlocke.ui.create_pokemon;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.barkatit.nuzlocke.data.model.PokemonSpeciesData;

import java.util.ArrayList;
import java.util.List;

public class PokemonSpeciesAdapter extends ArrayAdapter<PokemonSpeciesData> {

    private List<PokemonSpeciesData> items, suggestions;

    public PokemonSpeciesAdapter(@NonNull Context context, int resource, @NonNull List<PokemonSpeciesData> objects) {
        super(context, resource, objects);
        this.items = objects;
        this.suggestions = new ArrayList<>(objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        try {
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                view = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
            }
            PokemonSpeciesData species = getItem(position);
            TextView name = (TextView) view.findViewById(android.R.id.text1);
            name.setText(species.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public PokemonSpeciesData getItem(int position) {
        return suggestions.get(position);
    }
    @Override
    public int getCount() {
        return suggestions.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        return pokemonSpeciesFilter;
    }

    private Filter pokemonSpeciesFilter = new Filter() {

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            PokemonSpeciesData pokemonSpecies = (PokemonSpeciesData) resultValue;
            return pokemonSpecies.getName();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if(constraint != null) {
                ArrayList<PokemonSpeciesData> results = new ArrayList<>();
                for(PokemonSpeciesData speciesData : items) {
                    if(speciesData.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        results.add(speciesData);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = results;
                filterResults.count = results.size();
                return filterResults;
            }

            return new FilterResults();
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if(results != null && results.count > 0) {
                suggestions.clear();
                if(results.values instanceof List) {
                    List<PokemonSpeciesData> tempValues = (List<PokemonSpeciesData>) results.values;
                    suggestions.addAll(tempValues);
                    notifyDataSetChanged();
                }
            } else {
                suggestions.clear();
                notifyDataSetChanged();
            }
        }
    };
}
