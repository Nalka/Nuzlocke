package com.barkatit.nuzlocke.data.repository;

import com.barkatit.nuzlocke.data.model.Pokemon;
import com.barkatit.nuzlocke.data.repository.database.DatabaseRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class Repository {

    private DatabaseRepository databaseRepository;

    public Repository(DatabaseRepository databaseRepository) {
        this.databaseRepository = databaseRepository;
    }

    public Completable addPokemon(Pokemon pokemon) {
        return databaseRepository.addPokemon(pokemon);
    }

    public Flowable<List<Pokemon>> getBasicPokemonData() {
        return databaseRepository.getBasicPokemonData();
    }

    public Flowable<List<Pokemon>> getPartyPokemon() {
        return databaseRepository.getPartyPokemon();
    }

    public Single<Pokemon> getPokemonById(long id) {
        return databaseRepository.getPokemonById(id);
    }

    public Completable updatePokemon(Pokemon pokemon) {
        return databaseRepository.updatePokemon(pokemon);
    }
}
