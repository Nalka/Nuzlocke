package com.barkatit.nuzlocke.data.repository.database;

import androidx.room.TypeConverter;

import com.barkatit.nuzlocke.data.model.PokemonGender;

import java.util.Date;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static PokemonGender fromIntValue(int value) {
        return PokemonGender.getGenderFromIntegerValue(value);
    }

    @TypeConverter
    public static int genderToIntValue(PokemonGender gender) {
        return PokemonGender.getIntegerValueFromGender(gender);
    }
}
