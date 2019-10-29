package com.barkatit.nuzlocke.di;

import com.barkatit.nuzlocke.ui.box.BoxActivity;
import com.barkatit.nuzlocke.ui.create_pokemon.CreatePokemonActivity;
import com.barkatit.nuzlocke.ui.party.PartyActivity;
import com.barkatit.nuzlocke.ui.pokemon_details.PokemonDetailsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    abstract PartyActivity bindPartyActivity();

    @ContributesAndroidInjector()
    abstract CreatePokemonActivity bindCreatePokemonActivity();

    @ContributesAndroidInjector
    abstract BoxActivity bindBoxActivity();

    @ContributesAndroidInjector
    abstract PokemonDetailsActivity bindPokemonDetailsActivity();
}
