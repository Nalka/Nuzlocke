package com.barkatit.nuzlocke.ui.box;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.MenuRes;
import androidx.appcompat.widget.SearchView;
import androidx.databinding.Observable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.barkatit.nuzlocke.R;
import com.barkatit.nuzlocke.base.BaseActivity;
import com.barkatit.nuzlocke.data.model.Pokemon;
import com.barkatit.nuzlocke.databinding.ActivityBoxBinding;
import com.barkatit.nuzlocke.ui.create_pokemon.CreatePokemonActivity;
import com.barkatit.nuzlocke.ui.pokemon_details.PokemonDetailsActivity;

import java.util.ArrayList;
import java.util.List;

public class BoxActivity extends BaseActivity<BoxViewModel, ActivityBoxBinding> implements BoxView {

    @MenuRes
    private int menuResId = R.menu.box_menu;

    private PokemonBoxAdapter adapter;
    private List<Pokemon> pokemonList = new ArrayList<>();

    @Override
    public void setupView() {

        adapter = new PokemonBoxAdapter(new PokemonBoxAdapter.OnPokemonBoxItemClickListener() {
            @Override
            public void onPokemonBoxItemClick(Pokemon pokemon) {
                Intent pokemonDetailsIntent = new Intent(BoxActivity.this, PokemonDetailsActivity.class);
                pokemonDetailsIntent.putExtra(PokemonDetailsActivity.POKEMON_ID_KEY, pokemon.getId());
                startActivity(pokemonDetailsIntent);
            }
        });
        binding.rvPokemon.setLayoutManager(new GridLayoutManager(this, 3));
        binding.rvPokemon.setAdapter(adapter);
        viewModel.pokemonList.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (viewModel.pokemonList.get() != null) {
                    pokemonList = viewModel.pokemonList.get();
                    adapter.setItemList(pokemonList);
                }
            }
        });
        viewModel.fetchData();
    }

    @Override
    protected void parseArguments(Bundle bundle) {

    }


    @Override
    public Class<BoxViewModel> getViewModelClass() {
        return BoxViewModel.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_box;
    }

    @Override
    public String getActionBarText() {
        return getString(R.string.box_action_bar_title);
    }

    public void onAddPokemonClick(View view) {
        startActivity(new Intent(this, CreatePokemonActivity.class));
    }

    private static List<Pokemon> filter(List<Pokemon> models, String query) {
        final String lowerCaseQuery = query.toLowerCase();

        final List<Pokemon> filteredModelList = new ArrayList<>();
        for (Pokemon model : models) {
            final String name = model.getName().toLowerCase();
            if (name.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
            final String species = model.getSpecies().toLowerCase();
            if (species.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
            final String nature = model.getNature().toLowerCase();
            if (nature.contains(lowerCaseQuery)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(menuResId, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<Pokemon> filteredModelList = filter(pokemonList, newText);
                adapter.setItemList(filteredModelList);
                binding.rvPokemon.scrollToPosition(0);
                return true;
            }
        });
        return true;
    }
}
