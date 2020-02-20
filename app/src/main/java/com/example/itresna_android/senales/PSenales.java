package com.example.itresna_android.senales;

import android.content.Intent;
import android.os.Bundle;

import com.example.itresna_android.cops.Cops;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ArrayAdapter;

import com.example.itresna_android.R;

import java.util.ArrayList;

public class PSenales extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    RecyclerView reyclerViewseñales;
    AdaptadorRecyclerSenales adaptadorRecycler;
    ArrayList<Cops> listaCops = new ArrayList<Cops>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psenales);
        reyclerViewseñales = findViewById(R.id.recicler);
        reyclerViewseñales.setLayoutManager (new GridLayoutManager(this, 2));

        // Especificamos el adaptador para el recycler
        adaptadorRecycler = new AdaptadorRecyclerSenales();
        reyclerViewseñales.setAdapter(adaptadorRecycler);

        // Recogemos los valores que el usuario ha escogido
        Intent intent = getIntent();
        final String imgNombre = intent.getStringExtra("nombreImagen");
        String nombreEmpresa = intent.getStringExtra("nombre");
        String senal = intent.getStringExtra("senal");


    }
}
