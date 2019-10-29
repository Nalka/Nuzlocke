package com.barkatit.nuzlocke.ui.pokemon_details;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.MenuRes;
import androidx.databinding.Observable;

import com.barkatit.nuzlocke.R;
import com.barkatit.nuzlocke.base.BaseActivity;
import com.barkatit.nuzlocke.databinding.ActivityPokemonDetailsBinding;

public class PokemonDetailsActivity extends BaseActivity<PokemonDetailsViewModel, ActivityPokemonDetailsBinding> implements PokemonDetailsView {

    public static final String POKEMON_ID_KEY = PokemonDetailsActivity.class.getSimpleName()+"$PokemonId";

    private long pokemonId;

    @MenuRes
    private int menuResId = R.menu.pokemon_details_menu;

    @Override
    protected void parseArguments(Bundle bundle) {
        pokemonId = bundle.getLong(POKEMON_ID_KEY);
    }

    @Override
    public void setupView() {
        binding.setViewModel(viewModel);
        viewModel.fetchData(pokemonId);
        viewModel.pokemonReleased.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if(viewModel.pokemonReleased.get() != null && viewModel.pokemonReleased.get() == true) {
                    finish();
                }
            }
        });
        viewModel.pokemonMovedToParty.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if(viewModel.pokemonMovedToParty.get() != null && viewModel.pokemonMovedToParty.get() == true) {
                    Toast.makeText(PokemonDetailsActivity.this, getString(R.string.pokemon_details_pokemon_moved_msg), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public Class<PokemonDetailsViewModel> getViewModelClass() {
        return PokemonDetailsViewModel.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pokemon_details;
    }

    @Override
    public String getActionBarText() {
        return getString(R.string.pokemon_details_action_bar_title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(menuResId, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.pokemon_release) {
            viewModel.releasePokemon();
        } else if(item.getItemId() == R.id.pokemon_move_party) {
            viewModel.movePokemonToParty();
        }
        return super.onOptionsItemSelected(item);
    }
}
