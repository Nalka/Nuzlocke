package com.barkatit.nuzlocke.ui.pokemon_details;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;

import com.barkatit.nuzlocke.R;
import com.barkatit.nuzlocke.base.BaseViewModel;
import com.barkatit.nuzlocke.data.model.Pokemon;
import com.barkatit.nuzlocke.data.model.Type;
import com.barkatit.nuzlocke.data.repository.Repository;
import com.barkatit.nuzlocke.managers.SpriteManager;

import java.text.DateFormat;
import java.util.Date;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;

public class PokemonDetailsViewModel extends BaseViewModel {

    public ObservableField<Pokemon> pokemonObservableField;
    public ObservableField<Type> pokemonType1ObservableField;
    public ObservableField<Type> pokemonType2ObservableField;
    public ObservableField<Boolean> pokemonReleased;
    public ObservableField<Integer> showMessageObservableField;

    private final int max_party_size = 6;

    @Inject
    public PokemonDetailsViewModel(Repository repository) {
        super(repository);
        pokemonObservableField = new ObservableField<>();
        pokemonReleased = new ObservableField<>();
        showMessageObservableField = new ObservableField<>();
        pokemonType1ObservableField = new ObservableField<>();
        pokemonType2ObservableField = new ObservableField<>();
    }

    public void fetchData(long id) {
        execute(repository.getPokemonById(id), new DisposableSingleObserver<Pokemon>() {
            @Override
            public void onSuccess(Pokemon pokemon) {
                pokemonObservableField.set(pokemon);
                pokemonType1ObservableField.set(Type.getTypeByName(pokemon.getType()));
                if (!TextUtils.isEmpty(pokemon.getSubType())) {
                    pokemonType2ObservableField.set(Type.getTypeByName(pokemon.getSubType()));
                }
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    @BindingAdapter("spriteImage")
    public static void spriteImage(ImageView view, int pokemonSpeciesId) {
        SpriteManager.loadImage(pokemonSpeciesId, view);
    }

    public String getFormattedDate(Date date) {
        if(date == null)
            return "";
        return DateFormat.getDateTimeInstance().format(date);
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

    private void checkPartySize(DisposableSingleObserver<Integer> callback) {
        execute(repository.getPokemonPartyCount(), callback);
    }

    private void executeMovePokemonToParty() {
        Pokemon pokemon = pokemonObservableField.get();
        pokemon.setInParty(true);
        execute(repository.updatePokemon(pokemon), new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                showMessageObservableField.set(R.string.pokemon_details_pokemon_moved_msg_party);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void movePokemonToParty() {
        checkPartySize(new DisposableSingleObserver<Integer>() {
            @Override
            public void onSuccess(Integer count) {
                if(count == max_party_size) {
                    showMessageObservableField.set(R.string.pokemon_details_pokemon_party_too_big_msg);
                } else {
                    executeMovePokemonToParty();
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public void movePokemonToBox() {
        Pokemon pokemon = pokemonObservableField.get();
        pokemon.setInParty(false);
        execute(repository.updatePokemon(pokemon), new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                showMessageObservableField.set(R.string.pokemon_details_pokemon_moved_msg_box);
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }
}
