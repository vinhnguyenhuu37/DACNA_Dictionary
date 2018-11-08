package com.nor.yandex.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.nor.yandex.model.History;

/**
 * data access lưu history và favorite
 */
@Database(entities = {History.class}, version = 1)
public abstract class AppDao extends RoomDatabase {
    private static AppDao instance;

    public static AppDao getInstance(Context context) {
        if (instance == null) {
            // Yandex: database name
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDao.class, "Yandex")
                    // cho phép truy vấn trên main thread
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract HistoryDao historyDao();
}
