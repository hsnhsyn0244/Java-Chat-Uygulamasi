package com.example.mesajlasma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GirisActivity extends AppCompatActivity {

    EditText kullaniciAdieditText;
    Button kayitOlButton;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        tanimlama();
    }

    public void tanimlama(){
        kullaniciAdieditText = (EditText) findViewById(R.id.kullaniciAdieditText);
        kayitOlButton =(Button) findViewById(R.id.kayitOlButton);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();
        kayitOlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = kullaniciAdieditText.getText().toString();
                kullaniciAdieditText.setText("");
                ekle(username);
            }
        });


    }
    public void ekle(final String kAdi){
        reference.child("Kullanicilar").child(kAdi).child("kullaniciadi").setValue(kAdi).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "basariyla giris yaptiniz", Toast.LENGTH_LONG).show();
                    Intent intent =new Intent(GirisActivity.this,MainActivity.class);
                    intent.putExtra("kAdi",kAdi);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "giris yapılırken hata oluştu", Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}