package com.example.aeneisnotes.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.aeneisnotes.Model.Notlar;
import com.example.aeneisnotes.R;
import com.example.aeneisnotes.RoomDb.NotlarDao;
import com.example.aeneisnotes.RoomDb.NotlarDatabase;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DuzenleActivity extends AppCompatActivity {

    NotlarDao notlarDao;
    NotlarDatabase db;
    EditText textBaslik;
    EditText textMetin;
    int id=99;
    CompositeDisposable compositeDisposable=new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_duzenle);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textBaslik=findViewById(R.id.textBaslik);
        textMetin=findViewById(R.id.textMetin);


        db= Room.databaseBuilder(getApplicationContext(),NotlarDatabase.class,"Notlar")
//                .allowMainThreadQueries()
                .build();
        notlarDao=db.notlarDao();

        Intent intent=getIntent();
        Notlar myNote = (Notlar) getIntent().getSerializableExtra("myNote");

        textBaslik.setText(myNote.baslik.toString());
        textMetin.setText(myNote.metin.toString());
        id=myNote.id;


    }

    private void handleResponse(){

        Toast.makeText(DuzenleActivity.this, "Not başarıyla değiştirildi!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(DuzenleActivity.this, NotlarActivity.class);
        startActivity(intent);
        finish();
    }

    public void duzenle(View view){

        String baslik = textBaslik.getText().toString().trim();
        String metin = textMetin.getText().toString().trim();

        if (baslik.isEmpty() || metin.isEmpty()) {
            Toast.makeText(DuzenleActivity.this, "Boş alan bırakamazsınız!", Toast.LENGTH_SHORT).show();
        } else {
            compositeDisposable.add(notlarDao.updateById(id,baslik,metin)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(DuzenleActivity.this::handleResponse)
            );
//            notlarDao.updateById(id, baslik, metin);

        }

    }

    private void handleResponse2(){
        Intent intent = new Intent(DuzenleActivity.this, NotlarActivity.class);
        startActivity(intent);
        finish();
    }

    public void sil(View view){

        try {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Not Silme");
            alert.setMessage("Bu notu silmek istediğinize emin misiniz?\nBu işlem geri alınamaz!");
            alert.setPositiveButton("Evet", (dialog, which) -> {
                compositeDisposable.add(notlarDao.deleteById(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(DuzenleActivity.this::handleResponse2)
                );
            });
            alert.setNegativeButton("Hayır", (dialog, which) -> dialog.dismiss());


            alert.show();

        } catch (Exception e) {
            Toast.makeText(DuzenleActivity.this, "Bir hata meydana geldi!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }


}