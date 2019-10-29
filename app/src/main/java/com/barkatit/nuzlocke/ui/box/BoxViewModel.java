package com.barkatit.nuzlocke.ui.box;

import androidx.databinding.ObservableField;

import com.barkatit.nuzlocke.base.BaseViewModel;
import com.barkatit.nuzlocke.data.model.Pokemon;
import com.barkatit.nuzlocke.data.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.subscribers.ResourceSubscriber;

public class BoxViewModel extends BaseViewModel {

    ObservableField<List<Pokemon>> pokemonList;

    @Inject
    public BoxViewModel(Repository repository) {
        super(repository);
        pokemonList = new ObservableField<>();
    }

    public void fetchData() {
        execute(repository.getBasicPokemonData(), new ResourceSubscriber<List<Pokemon>>() {
            @Override
            public void onNext(List<Pokemon> result) {
                pokemonList.set(result);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }
}
