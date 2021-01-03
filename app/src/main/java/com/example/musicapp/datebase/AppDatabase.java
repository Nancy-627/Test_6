package com.example.musicapp.datebase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.musicapp.model.entities.SearchHistory;
import com.example.musicapp.model.entities.SearchHistoryDao;

@Database(entities = {SearchHistory.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SearchHistoryDao searchHistoryDao();
}