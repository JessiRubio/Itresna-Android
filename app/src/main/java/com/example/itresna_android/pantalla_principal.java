package com.example.itresna_android;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;



public class pantalla_principal extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        Toolbar barra = findViewById(R.id.appbar);
        setSupportActionBar(barra);
        return super.onCreateOptionsMenu(menu);
    }
}
