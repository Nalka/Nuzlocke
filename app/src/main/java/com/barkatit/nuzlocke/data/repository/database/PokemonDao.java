package com.barkatit.nuzlocke.data.repository.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.barkatit.nuzlocke.data.model.Pokemon;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface PokemonDao {

    @Query("SELECT * FROM pokemon WHERE inParty = 0 AND released = 0")
    Flowable<List<Pokemon>> getBoxedPokemon();

    @Query("SELECT * FROM pokemon WHERE inParty = 1 AND released = 0")
    Flowable<List<Pokemon>> getPartyPokemon();

    @Query("SELECT * FROM pokemon WHERE id = :id")
    Single<Pokemon> getPokemonById(long id);

    @Query("SELECT COUNT(id) FROM pokemon WHERE inParty = 1")
    Single<Integer> getPokemonPartyCount();

    @Insert
    Completable createPokemon(Pokemon pokemon);

    @Update
    Completable updatePokemon(Pokemon pokemon);
}
