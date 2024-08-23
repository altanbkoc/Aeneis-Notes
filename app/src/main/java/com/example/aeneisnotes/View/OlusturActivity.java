package com.example.aeneisnotes.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

public class OlusturActivity extends AppCompatActivity {

    NotlarDatabase db;
    NotlarDao notlarDao;

    EditText txtBaslik;
    EditText txtMetin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_olustur);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db= Room.databaseBuilder(getApplicationContext(),NotlarDatabase.class,"Notlar")
                .allowMainThreadQueries()
                .build();

        notlarDao=db.notlarDao();


        txtBaslik=findViewById(R.id.textBaslik);
        txtMetin=findViewById(R.id.textMetin);
    }


    public void olustur(View view){
        String baslik = txtBaslik.getText().toString().trim();
        String metin = txtMetin.getText().toString().trim();

        if (baslik.isEmpty() || metin.isEmpty())
        {
            Toast.makeText(OlusturActivity.this, "Boş alan bırakamazsınız!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Notlar notlar=new Notlar(txtBaslik.getText().toString(),txtMetin.getText().toString());
            notlarDao.insert(notlar);
            Toast.makeText(OlusturActivity.this, "Not başarıyla kaydedildi!", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(OlusturActivity.this,NotlarActivity.class);
            startActivity(intent);
            finish();
        }
    }


}