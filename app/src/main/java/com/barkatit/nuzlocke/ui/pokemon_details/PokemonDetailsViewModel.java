package com.barkatit.nuzlocke.ui.pokemon_details;

import androidx.databinding.ObservableField;

import com.barkatit.nuzlocke.base.BaseViewModel;
import com.barkatit.nuzlocke.data.model.Pokemon;
import com.barkatit.nuzlocke.data.repository.Repository;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;

public class PokemonDetailsViewModel extends BaseViewModel {

    public ObservableField<Pokemon> pokemonObservableField;
    public ObservableField<Boolean> pokemonReleased;
    public ObservableField<Boolean> pokemonMovedToParty;

    @Inject
    public PokemonDetailsViewModel(Repository repository) {
        super(repository);
        pokemonObservableField = new ObservableField<>();
        pokemonReleased = new ObservableField<>();
        pokemonMovedToParty = new ObservableField<>();
    }

    public void fetchData(long id) {
        execute(repository.getPokemonById(id), new DisposableSingleObserver<Pokemon>() {
            @Override
            public void onSuccess(Pokemon pokemon) {
                pokemonObservableField.set(pokemon);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    public void releasePokemon() {
        Pokemon pokemon = pokemonObservableField.get();
        pokemon.setReleased(true);
        execute(repository.updatePokemon(pokemon), new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                pokemonReleased.set(true);
            }

            @Override
            public void onError(Throwable e) {

            }
        });

    }

    public void movePokemonToParty() {
        Pokemon pokemon = pokemonObservableField.get();
        pokemon.setInParty(true);
        execute(repository.updatePokemon(pokemon), new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                pokemonMovedToParty.set(true);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
