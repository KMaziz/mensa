package com.example.mensas.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {Mensa.class}, version = 1, exportSchema = false)
public  abstract class AppDatabase extends RoomDatabase  {

    public abstract MensenDao mensenDao();
    private static AppDatabase INSTANCE;


    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "Mensa")
//Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class)
                    // To simplify the exercise, allow queries on the main thread.
                    // Don't do this on a real app!
                    .allowMainThreadQueries()
                    // recreate the database if necessary
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}



