package com.barkatit.nuzlocke.ui.party;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.Observable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.barkatit.nuzlocke.R;
import com.barkatit.nuzlocke.base.BaseActivity;
import com.barkatit.nuzlocke.data.model.Pokemon;
import com.barkatit.nuzlocke.databinding.ActivityPartyBinding;
import com.barkatit.nuzlocke.ui.pokemon_details.PokemonDetailsActivity;

public class PartyActivity extends BaseActivity<PartyViewModel, ActivityPartyBinding> {

    @Override
    public void setupView() {
        final PokemonPartyAdapter adapter = new PokemonPartyAdapter(new PokemonPartyAdapter.OnPokemonPartyItemClickListener() {
            @Override
            public void onPokemonPartyItemClick(Pokemon pokemon) {
                Intent pokemonDetailsIntent = new Intent(PartyActivity.this, PokemonDetailsActivity.class);
                pokemonDetailsIntent.putExtra(PokemonDetailsActivity.POKEMON_ID_KEY, pokemon.getId());
                startActivity(pokemonDetailsIntent);
            }
        });
        binding.rvPokemon.setLayoutManager(new LinearLayoutManager(this));
        binding.rvPokemon.setAdapter(adapter);
        viewModel.pokemonList.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (viewModel.pokemonList.get() != null) {
                    adapter.setItemList(viewModel.pokemonList.get());
                }
            }
        });
        viewModel.fetchPokemonParty();
    }

    @Override
    protected void parseArguments(Bundle bundle) {

    }

    @Override
    public Class<PartyViewModel> getViewModelClass() {
        return PartyViewModel.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_party;
    }

    @Override
    public String getActionBarText() {
        return getString(R.string.party_action_bar_title);
    }

}
