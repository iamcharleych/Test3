package com.chaplin.test3.core.di.modules;

import androidx.room.Room;
import com.chaplin.test3.App;
import com.chaplin.test3.data.db.AppDatabase;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public abstract class DBModule {

    @Provides
    @Singleton
    static AppDatabase provideDatabase(App context) {
        return Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DATABASE_NAME).build();
    }
}
