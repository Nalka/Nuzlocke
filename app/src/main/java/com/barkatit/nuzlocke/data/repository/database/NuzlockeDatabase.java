package com.barkatit.nuzlocke.data.repository.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.barkatit.nuzlocke.data.model.Pokemon;

@Database(entities = {Pokemon.class}, version = NuzlockeDatabase.VERSION)
@TypeConverters({Converters.class})
public abstract class NuzlockeDatabase extends RoomDatabase {

    static final int VERSION = 1;
    public static String DATABASE_NAME = "nuzlocke_db";

    public abstract PokemonDao getPokemonDao();
}
