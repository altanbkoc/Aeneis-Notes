package com.example.aeneisnotes.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Notlar implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int id;


    @ColumnInfo(name = "baslik")
    public String baslik;


    @ColumnInfo(name = "metin")
    public String metin;


    public Notlar(String baslik,String metin){
        this.baslik=baslik;
        this.metin=metin;
    }

}
