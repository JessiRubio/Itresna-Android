package com.example.itresna_android;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class pantalla_principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal);

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
}
