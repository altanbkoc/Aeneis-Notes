package com.example.aeneisnotes.RoomDb;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.aeneisnotes.Model.Notlar;

@Database(entities = {Notlar.class},version = 1)
public abstract class NotlarDatabase extends RoomDatabase {
    public abstract NotlarDao notlarDao();
}
