package com.barkatit.nuzlocke.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.barkatit.nuzlocke.base.BaseViewModelFactory;
import com.barkatit.nuzlocke.ui.box.BoxViewModel;
import com.barkatit.nuzlocke.ui.create_pokemon.CreatePokemonViewModel;
import com.barkatit.nuzlocke.ui.party.PartyViewModel;
import com.barkatit.nuzlocke.ui.pokemon_details.PokemonDetailsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(BaseViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(PartyViewModel.class)
    abstract ViewModel providePartyViewModel(PartyViewModel partyViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CreatePokemonViewModel.class)
    abstract ViewModel provideCreatePokemonViewModel(CreatePokemonViewModel createPokemonViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(BoxViewModel.class)
    abstract ViewModel provideBoxViewModel(BoxViewModel boxViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PokemonDetailsViewModel.class)
    abstract ViewModel providePokemonDetailsViewModel(PokemonDetailsViewModel pokemonDetailsViewModel);

}
