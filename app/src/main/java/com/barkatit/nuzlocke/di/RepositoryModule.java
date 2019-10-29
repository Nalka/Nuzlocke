package com.barkatit.nuzlocke.di;

import android.app.Application;

import androidx.room.Room;

import com.barkatit.nuzlocke.data.repository.Repository;
import com.barkatit.nuzlocke.data.repository.database.DatabaseRepository;
import com.barkatit.nuzlocke.data.repository.database.NuzlockeDatabase;
import com.barkatit.nuzlocke.data.repository.database.PokemonDao;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

//@Module
public class RepositoryModule {

//    private NuzlockeDatabase database;

//    @Inject
//    public RepositoryModule(Application application) {
//        database = Room.databaseBuilder(application, NuzlockeDatabase.class, NuzlockeDatabase.DATABASE_NAME).build();
//    }
//
//    @Singleton
//    @Provides
//    NuzlockeDatabase providesDatabase() {
//        return database;
//    }



}
