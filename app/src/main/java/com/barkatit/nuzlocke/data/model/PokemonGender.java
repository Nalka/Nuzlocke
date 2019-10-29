package com.barkatit.nuzlocke.data.model;

public enum PokemonGender {
    FEMALE,
    MALE,
    GENDERLESS;

    public static PokemonGender getGenderFromIntegerValue(int value) {
        switch (value) {
            case 1: return FEMALE;
            case 2: return MALE;
            default: return GENDERLESS;
        }
    }

    public static Integer getIntegerValueFromGender(PokemonGender gender) {
        if(gender == null)
            return 0;
        switch (gender) {
            case FEMALE: return 1;
            case MALE: return 2;
            default: return 0;
        }
    }
}
