package com.example.itresna_android.senales;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.itresna_android.R;

import java.util.ArrayList;

public class popUpcomentarios extends AppCompatActivity {
    RecyclerView reyclerViewseñales;
    AdaptadorComentario adaptadorRecycler;
    ArrayList<String> listaComentarios = new ArrayList<>();
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
