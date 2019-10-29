package com.barkatit.nuzlocke.ui.create_pokemon;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.databinding.Observable;

import com.barkatit.nuzlocke.R;
import com.barkatit.nuzlocke.base.BaseActivity;
import com.barkatit.nuzlocke.data.model.PokemonGender;
import com.barkatit.nuzlocke.data.model.PokemonSpeciesData;
import com.barkatit.nuzlocke.data.model.Type;
import com.barkatit.nuzlocke.databinding.ActivityCreatePokemonBinding;

public class CreatePokemonActivity extends BaseActivity<CreatePokemonViewModel, ActivityCreatePokemonBinding> implements CreatePokemonView {

    @Override
    public void setupView() {
        viewModel.fetchAutoCompleteData(this);
        binding.setViewModel(viewModel);
        binding.tvType1.setVisibility(View.GONE);
        binding.tvType2.setVisibility(View.GONE);

        setupSpecies();

        viewModel.finishActivity.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if(viewModel.finishActivity.get() != null && viewModel.finishActivity.get()) {
                    finish();
                }
            }
        });

        setupGenders();
    }

    private void setupSpecies() {
        binding.etSpecies.setThreshold(1);
        viewModel.pokemonSpeciesData.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if(viewModel.pokemonSpeciesData.get() != null) {
                    PokemonSpeciesAdapter adapter = new PokemonSpeciesAdapter(CreatePokemonActivity.this, android.R.layout.simple_dropdown_item_1line, viewModel.pokemonSpeciesData.get());
                    binding.etSpecies.setAdapter(adapter);
                    binding.etSpecies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                            binding.ilSpecies.setErrorEnabled(false);
                            PokemonSpeciesData pokemonSpeciesData = (PokemonSpeciesData) adapterView.getItemAtPosition(position);
                            displaySpeciesData(pokemonSpeciesData);
                        }
                    });
                }
            }
        });
        binding.etSpecies.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    PokemonSpeciesData enteredSpecies = viewModel.getSpeciesDataByName(binding.etSpecies.getText().toString());
                    if(enteredSpecies == null) {
                        binding.etSpecies.setError(getString(R.string.create_pokemon_species_error));
                    } else {
                        binding.etSpecies.setText(enteredSpecies.getName());
                        displaySpeciesData(enteredSpecies);
                    }
                } else {
                    binding.ilSpecies.setErrorEnabled(false);
                }
            }
        });
    }

    private void setupGenders() {
        String[] pokemonGenders = getResources().getStringArray(R.array.pokemon_gender_array);
        ArrayAdapter<String> pokemonGenderAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, pokemonGenders);
        binding.etGender.setAdapter(pokemonGenderAdapter);
        binding.etGender.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //based on what's in pokemon_gender_array
                PokemonGender genderItem = null;
                switch (position) {
                    case 0: genderItem = PokemonGender.FEMALE; break;
                    case 1: genderItem = PokemonGender.MALE; break;
                    case 2: genderItem = PokemonGender.GENDERLESS; break;
                }
                binding.setGenderData(genderItem);
            }
        });
    }

    private void displaySpeciesData(PokemonSpeciesData pokemonSpeciesData) {
        binding.setSpeciesData(pokemonSpeciesData);

        Type firstType = pokemonSpeciesData.getTypes().get(0);
        binding.tvType1.setVisibility(View.VISIBLE);
        binding.tvType1.setText(firstType.getValue());
        binding.tvType1.setBackgroundResource(firstType.getColorId());
        if(pokemonSpeciesData.getTypes().size() > 1) {
            Type secondType = pokemonSpeciesData.getTypes().get(1);
            binding.tvType2.setVisibility(View.VISIBLE);
            binding.tvType2.setText(secondType.getValue());
            binding.tvType2.setBackgroundResource(secondType.getColorId());
        } else {
            binding.tvType2.setVisibility(View.GONE);
        }
    }

    @Override
    protected void parseArguments(Bundle bundle) {

    }

    @Override
    public Class<CreatePokemonViewModel> getViewModelClass() {
        return CreatePokemonViewModel.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_pokemon;
    }

    @Override
    public String getActionBarText() {
        return getString(R.string.create_pokemon_action_bar_title);
    }

}
