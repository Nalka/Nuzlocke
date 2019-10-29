package com.barkatit.nuzlocke.ui.create_pokemon;

import android.content.Context;
import android.text.TextUtils;

import androidx.databinding.ObservableField;

import com.barkatit.nuzlocke.base.BaseViewModel;
import com.barkatit.nuzlocke.data.model.Pokemon;
import com.barkatit.nuzlocke.data.model.PokemonGender;
import com.barkatit.nuzlocke.data.model.PokemonSpeciesData;
import com.barkatit.nuzlocke.data.repository.Repository;
import com.barkatit.nuzlocke.managers.AssetManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;

public class CreatePokemonViewModel extends BaseViewModel {

    @Inject
    public CreatePokemonViewModel(Repository repository) {
        super(repository);
    }

    ObservableField<Boolean> finishActivity = new ObservableField<>();
    ObservableField<List<PokemonSpeciesData>> pokemonSpeciesData = new ObservableField<>();

    public void fetchAutoCompleteData(final Context context) {
        execute(Single.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return AssetManager.getDataFromAssetFile(context, "pokemon_data.json");
            }
        }).map(new Function<String, List<PokemonSpeciesData>>() {
            @Override
            public List<PokemonSpeciesData> apply(String s) throws Exception {
                Type listType = new TypeToken<ArrayList<PokemonSpeciesData>>(){}.getType();
                return new Gson().fromJson(s, listType);
            }
        }), new DisposableSingleObserver<List<PokemonSpeciesData>>() {
            @Override
            public void onSuccess(List<PokemonSpeciesData> result) {
                pokemonSpeciesData.set(result);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        });
    }

    public PokemonSpeciesData getSpeciesDataByName(String name) {
        List<PokemonSpeciesData> dataList = pokemonSpeciesData.get();
        if(dataList != null) {
            for(PokemonSpeciesData data : dataList) {
                if(data.getName().equalsIgnoreCase(name)) {
                    return data;
                }
            }

        }
        return null;
    }

    public void createPokemon(
            String name,
            PokemonSpeciesData species,
            String nature,
            String type,
            String subtype,
            String hitPoints,
            String attack,
            String defence,
            String specialAttack,
            String specialDefence,
            String speed,
            String placeMet,
            PokemonGender gender,
            String level
    ) {
        if(TextUtils.isEmpty(attack))
            attack = "0";
        if(TextUtils.isEmpty(defence))
            defence = "0";
        if(TextUtils.isEmpty(specialAttack))
            specialAttack = "0";
        if(TextUtils.isEmpty(specialDefence))
            specialDefence = "0";
        if(TextUtils.isEmpty(speed))
            speed = "0";
        if(TextUtils.isEmpty(hitPoints))
            hitPoints = "0";
        if(TextUtils.isEmpty(level))
            level = "1";
        int attackParsed = Integer.parseInt(attack);
        int defenceParsed = Integer.parseInt(defence);
        int specialAttackParsed = Integer.parseInt(specialAttack);
        int specialDefenceParsed = Integer.parseInt(specialDefence);
        int speedParsed = Integer.parseInt(speed);
        int hitPointsParsed = Integer.parseInt(hitPoints);
        int levelMet = Integer.parseInt(level);

        Pokemon pokemon = new Pokemon();
        pokemon.setName(name);
        pokemon.setSpecies(species.getName());
        pokemon.setSpeciesId(species.getId());
        pokemon.setNature(nature);
        pokemon.setType(type);
        pokemon.setSubType(subtype);
        pokemon.setHp(hitPointsParsed);
        pokemon.setAttack(attackParsed);
        pokemon.setDefence(defenceParsed);
        pokemon.setSpecialAttack(specialAttackParsed);
        pokemon.setSpecialDefence(specialDefenceParsed);
        pokemon.setSpeed(speedParsed);
        pokemon.setLevel(levelMet);
        pokemon.setLevelMet(levelMet);
        pokemon.setCaught(new Date());
        pokemon.setPlaceMet(placeMet);
        pokemon.setGender(gender);

        execute(getRepository().addPokemon(pokemon), new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                finishActivity.set(true);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        });
    }
}
