package com.project.fifamanagerdata.favoritesPlayerRoomDB;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities  = {PlayerDataModel.class}, version = 1)
public abstract class PlayerDB extends RoomDatabase {
    private static PlayerDB INSTANCE;

    public abstract PlayerDao playerDao();

    public static synchronized PlayerDB getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PlayerDB.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
