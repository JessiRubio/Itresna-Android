package com.example.itresna_android.Senales;

import android.content.Intent;
import android.os.Bundle;

import com.example.itresna_android.AdaptadorRecyclerPCops;
import com.example.itresna_android.AdaptadorRecyclerSeñales;
import com.example.itresna_android.Cops;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ArrayAdapter;

import com.example.itresna_android.R;

import java.util.ArrayList;

public class PSenales extends AppCompatActivity {

    RecyclerView reyclerViewseñales;
    AdaptadorRecyclerSeñales adaptadorRecycler;
    ArrayList<Cops> listaSeñales = new ArrayList<Cops>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psenales);

        reyclerViewseñales = findViewById(R.id.recicler);
        reyclerViewseñales.setLayoutManager (new GridLayoutManager(this, 2));

        // Especificamos el adaptador para el recycler
        adaptadorRecycler = new AdaptadorRecyclerSeñales();
        reyclerViewseñales.setAdapter(adaptadorRecycler);

        // Recogemos los valores que el usuario ha escogido
        Intent intent = getIntent();
        final String imgNombre = intent.getStringExtra("nombreImagen");
        String nombreEmpresa = intent.getStringExtra("nombre");
        String senal = intent.getStringExtra("senal");
        String codigo = intent.getStringExtra("codigo");
        System.out.println("ESTE ES EL CODIGO DE LA COP"+codigo);

    }
}
