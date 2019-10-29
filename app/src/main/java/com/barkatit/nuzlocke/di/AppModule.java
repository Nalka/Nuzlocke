package com.barkatit.nuzlocke.di;


import android.app.Application;

import androidx.room.Room;

import com.barkatit.nuzlocke.data.repository.Repository;
import com.barkatit.nuzlocke.data.repository.database.DatabaseRepository;
import com.barkatit.nuzlocke.data.repository.database.NuzlockeDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Singleton @Provides
    NuzlockeDatabase provideDatabase(Application app) {
        return Room.databaseBuilder(app, NuzlockeDatabase.class,NuzlockeDatabase.DATABASE_NAME).build();
    }

    @Provides
    DatabaseRepository provideDatabaseRepository(NuzlockeDatabase database) {
        return new DatabaseRepository(database.getPokemonDao());
    }
//
    @Provides
    Repository provideRepository(DatabaseRepository databaseRepository) {
        return new Repository(databaseRepository);
    }

}
