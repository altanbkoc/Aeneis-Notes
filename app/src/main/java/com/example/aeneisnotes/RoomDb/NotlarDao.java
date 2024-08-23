package com.example.aeneisnotes.RoomDb;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.aeneisnotes.Model.Notlar;

import java.util.List;

@Dao
public interface NotlarDao {


    @Query("Select * From Notlar")
    List<Notlar> getAll();

    @Insert
    void insert(Notlar notlar);

    @Delete()
    void delete(Notlar notlar);

    @Update
    void update(Notlar notlar);

    @Query("DELETE FROM Notlar WHERE id = :id")
    void deleteById(int id);

    @Query("UPDATE Notlar SET baslik = :baslik, metin = :metin WHERE id = :id")
    void updateById(int id, String baslik, String metin);



}
