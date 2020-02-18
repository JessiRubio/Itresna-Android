package com.example.itresna_android;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class PCops extends AppCompatActivity {

    Spinner comboBox;
    String[] arrayCombobox;
    ArrayAdapter<String> adapter;
    RecyclerView reyclerViewCops;
    AdaptadorRecyclerPCops adaptadorRecycler;
    ArrayList<Cops> listaCops = new ArrayList<Cops>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_pcops);

        reyclerViewCops = findViewById(R.id.recyclerViewPCops);
        comboBox = findViewById(R.id.spinnerPCops);

        //Hacemos lo relacionado con el comboBox(Spinner)
        arrayCombobox = new String[] {
                "David", "El", "Cajas", "También", "Conocido", "Como", "David"
        };
        adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_texto, arrayCombobox);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comboBox.setAdapter(adapter);

        comboBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(comboBox.getSelectedItem() == "Cajas"){
                    Toast.makeText(getApplicationContext(), "David el Cajas", Toast.LENGTH_SHORT).show();
                    Cops prueba1 = new Cops("app_logo", "Iberdrola", "1. señal");
                    Cops prueba2 = new Cops("logo", "Accenture", "1. señal");
                    Cops prueba3 = new Cops("logo", "Ibermatica", "1. señal");
                    Cops prueba4 = new Cops("logo", "Ibernautica", "1. señal");
                    listaCops.clear();
                    listaCops.add(prueba1);
                    listaCops.add(prueba2);
                    listaCops.add(prueba3);
                    listaCops.add(prueba4);
                    generarDatosRecyler(listaCops);
                }
                else if (comboBox.getSelectedItem() == "David"){
                    Toast.makeText(getApplicationContext(), "David el David", Toast.LENGTH_SHORT).show();
                    Cops prueba1 = new Cops("logo", "Iberdrola", "1. señal");
                    Cops prueba2 = new Cops("logo", "Accenture", "1. señal");
                    Cops prueba3 = new Cops("logo", "Ibermatica", "1. señal");
                    Cops prueba4 = new Cops("logo", "Ibernautica", "1. señal");
                    listaCops.clear();
                    listaCops.add(prueba1);
                    listaCops.add(prueba2);
                    listaCops.add(prueba3);
                    listaCops.add(prueba4);
                    generarDatosRecyler(listaCops);
                }
                else if (comboBox.getSelectedItem() == "El"){
                    Toast.makeText(getApplicationContext(), "David el El", Toast.LENGTH_SHORT).show();
                    Cops prueba1 = new Cops("app_logo", "Iberdrola", "1. señal");
                    Cops prueba2 = new Cops("app_logo", "Accenture", "1. señal");
                    Cops prueba3 = new Cops("app_logo", "Ibermatica", "1. señal");
                    Cops prueba4 = new Cops("app_logo", "Ibernautica", "1. señal");
                    listaCops.clear();
                    listaCops.add(prueba1);
                    listaCops.add(prueba2);
                    listaCops.add(prueba3);
                    listaCops.add(prueba4);
                    generarDatosRecyler(listaCops);
                } else{
                    Toast.makeText(getApplicationContext(), "David el Deseleccionado", Toast.LENGTH_SHORT).show();
                    Cops prueba1 = new Cops("logo", "Iberdrola", "1. señal");
                    Cops prueba2 = new Cops("logo", "Accenture", "1. señal");
                    Cops prueba3 = new Cops("logo", "Ibermatica", "1. señal");
                    Cops prueba4 = new Cops("logo", "Ibernautica", "1. señal");
                    listaCops.clear();
                    listaCops.add(prueba1);
                    listaCops.add(prueba2);
                    listaCops.add(prueba3);
                    listaCops.add(prueba4);
                    generarDatosRecyler(listaCops);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        menu.findItem(R.id.menu_nombre_usuario).setTitle("xDDDD");

        // Creamos un nuevo ActionBar que va a ser un personalizado
        // para poder alinear el logo a la izquierda
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        //Aquí añadimos el custom ActionBar
        View mActionBarView = getLayoutInflater().inflate(R.layout.actionbar_personalizado, null);
        actionBar.setCustomView(mActionBarView);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.menuitem_admin) {
            //Aquí el código de cuando se clique en admin
        } else if (item.getItemId() == R.id.menuitem_idioma){
            //Aquí el código de cuando se clique en cambiar idioma
        } else if (item.getItemId() == R.id.menuitem_cerrar_sesion){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    public void generarDatosRecyler(ArrayList <Cops> listaCops){
        // Este ajuste mejora la performance, solo si el
        // contenido del recycler no altera su tamaño
        reyclerViewCops.setHasFixedSize(true);

        // Colocamos 3 columnas en el recyclerView
        reyclerViewCops.setLayoutManager (new GridLayoutManager(this, 2));

        // Especificamos el adaptador para el recycler
        adaptadorRecycler = new AdaptadorRecyclerPCops(listaCops);
        reyclerViewCops.setAdapter(adaptadorRecycler);
    }
}
