package com.example.itresna_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class popUpcomentarios extends AppCompatActivity {
    RecyclerView reyclerViewseñales;
    AdaptadorComentario adaptadorRecycler;
    ArrayList<Cops> listaComentarios = new ArrayList<Cops>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_upcomentarios);
        reyclerViewseñales = findViewById(R.id.recyclerViewComentarios);
        reyclerViewseñales.setLayoutManager (new LinearLayoutManager(this));
        // Especificamos el adaptador para el recycler
        adaptadorRecycler = new AdaptadorComentario();
        reyclerViewseñales.setAdapter(adaptadorRecycler);
    }
}
