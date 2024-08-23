package com.example.aeneisnotes.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.aeneisnotes.Model.Notlar;
import com.example.aeneisnotes.R;
import com.example.aeneisnotes.RoomDb.NotlarDao;
import com.example.aeneisnotes.RoomDb.NotlarDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    NotlarDatabase db;
    NotlarDao notlarDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        db= Room.databaseBuilder(getApplicationContext(),NotlarDatabase.class,"Notlar")
                .allowMainThreadQueries()
                .build();

        notlarDao=db.notlarDao();


    }


    public void olustur(View view){
        Intent intent=new Intent(MainActivity.this,OlusturActivity.class);
        startActivity(intent);
    }

    public void notlar(View view){
        Intent intent=new Intent(MainActivity.this,NotlarActivity.class);
        startActivity(intent);


    }
}