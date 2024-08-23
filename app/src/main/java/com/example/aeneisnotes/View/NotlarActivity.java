package com.example.aeneisnotes.View;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.aeneisnotes.Adapter.MyAdapter;
import com.example.aeneisnotes.Model.Notlar;
import com.example.aeneisnotes.R;
import com.example.aeneisnotes.RoomDb.NotlarDao;
import com.example.aeneisnotes.RoomDb.NotlarDatabase;

import java.util.List;

public class NotlarActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NotlarDao notlarDao;
    NotlarDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notlar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db= Room.databaseBuilder(getApplicationContext(),NotlarDatabase.class,"Notlar")
                .allowMainThreadQueries()
                .build();
        notlarDao=db.notlarDao();

        List<Notlar> notlar=notlarDao.getAll();

        MyAdapter myAdapter=new MyAdapter(NotlarActivity.this,notlar);
        recyclerView.setAdapter(myAdapter);


    }
}