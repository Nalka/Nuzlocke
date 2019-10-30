package com.barkatit.nuzlocke.data.repository.database;

import com.barkatit.nuzlocke.data.model.Pokemon;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class DatabaseRepository {

    private PokemonDao pokemonDao;

    public DatabaseRepository(PokemonDao pokemonDao) {
        this.pokemonDao = pokemonDao;
    }

    public Completable addPokemon(Pokemon pokemon) {
        return pokemonDao.createPokemon(pokemon);
    }

    public Flowable<List<Pokemon>> getBasicPokemonData() {
        return pokemonDao.getBoxedPokemon();
    }

    public Flowable<List<Pokemon>> getPartyPokemon() {
        return pokemonDao.getPartyPokemon();
    }

    public Single<Pokemon> getPokemonById(long id) {
        return pokemonDao.getPokemonById(id);
    }

    public Single<Integer> getPokemonPartyCount() {
        return pokemonDao.getPokemonPartyCount();
    }

    public Completable updatePokemon(Pokemon pokemon) {
        return pokemonDao.updatePokemon(pokemon);
    }
}
