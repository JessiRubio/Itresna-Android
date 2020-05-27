package com.example.itresna_android;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import android.os.Handler;
import android.os.SystemClock;

import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


import java.util.ArrayList;

import java.util.Locale;

/** Clase generica para gestionar las cops.**/

public class PCops extends AppCompatActivity {
    int contador =0;
    String eslogan;
    Spinner comboBox;
    ArrayList <String> arrayCombobox= new ArrayList<String>();
    ArrayAdapter<String> adapter;
    RecyclerView reyclerViewCops;
    AdaptadorRecyclerPCops adaptadorRecycler;
    ArrayList<Cops> listaCops = new ArrayList<>();
    RadioButton rb_cast;
    RadioButton rb_eus;
    RadioButton rb_eng;
    TextView tEslogan;
    ArrayList<Espacio> espacios = new ArrayList<>();
    ArrayList<Cop> cops = new ArrayList<>();
    Aplication myApplication;
    String espacioSeleccionado;

    private Context mContext;
    private LinearLayout mRelativeLayout;
    private PopupWindow mPopupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_pcops);

        myApplication = (Aplication) getApplication();
        eslogan = myApplication.eslogan;
        espacios = myApplication.espacios;
        cops = myApplication.cops;
        for (int i = 0; espacios.size()>i;i++){
            System.out.println("CODIGO DE ESPACIO " + i + " == " + espacios.get(i).cod_esp+ "desc_esp: "+espacios.get(i).desc_esp);
        }
        reyclerViewCops = findViewById(R.id.recyclerViewPCops);
        comboBox = findViewById(R.id.spinnerPCops);
        tEslogan=findViewById(R.id.txtEsloganPCops);
            for(int i=0;espacios.size()>i;i++){
                System.out.println("desc_esp: "+espacios.get(i).desc_esp);
                arrayCombobox.add(espacios.get(i).desc_esp);
            }
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_texto,arrayCombobox);
            adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            comboBox.setAdapter(adaptador);
            espacioSeleccionado = comboBox.getSelectedItem().toString();


                cargarCops();
                Toast.makeText(getApplicationContext(),"Me han pulsado",Toast.LENGTH_LONG).show();
                final String[] codEspacio = {""};
                boolean encontradoEspacio = false;

                for (int i = 0; espacios.size()>i && !encontradoEspacio; i++){
                    Toast.makeText(getApplicationContext(),"Entro en for " +espacios.size(),Toast.LENGTH_LONG).show();
                    if (espacios.get(i).desc_esp.equals(comboBox.getSelectedItem().toString())){
                        codEspacio[0] = espacios.get(i).cod_esp;
                        myApplication.decripcionEspacio = espacios.get(i).desc_esp;
                        myApplication.codEspacio = espacios.get(i).cod_esp;
                        encontradoEspacio = true;
                        Toast.makeText(getApplicationContext(),"Entro en codigo espacio",Toast.LENGTH_LONG).show();
                    }
                }

                listaCops.clear();
                for (int i=0;cops.size()>i;i++){
                    if (cops.get(i).cod_esp.equals(codEspacio[0])){
                        System.out.println("genero Cop = "+i);
                        Cops prueba1 = new Cops(cops.get(i).img_cop, cops.get(i).desc_cop);
                        listaCops.add(prueba1);
                    }
                }
                generarDatosRecyler(listaCops);

        tEslogan.setText(eslogan);

        comboBox.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(contador != 0) {
                    boolean encontradoEspacio = false;
                    espacioSeleccionado = comboBox.getSelectedItem().toString();
                    System.out.println("COPS QUE TENEMOS EN EL ARRAY DE ESPACIOS = "+espacios.size());
                    for (int i = 0; espacios.size() > i && !encontradoEspacio; i++) {
                        System.out.println("Entro");
                        if (espacios.get(i).desc_esp.equals(espacioSeleccionado)) {
                            espacioSeleccionado = espacios.get(i).cod_esp;
                            myApplication.decripcionEspacio = espacios.get(i).desc_esp;
                            encontradoEspacio = true;
                            Toast.makeText(getApplicationContext(), "Entro en codigo espacio", Toast.LENGTH_LONG).show();
                        }
                    }
                        cargarCops();

                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                // acciones que se ejecutan tras los milisegundos
                                listaCops.clear();
                                for (int k=0;cops.size()>k;k++){
                                    System.out.println("COPS QUE TENEMOS EN EL ARRAY DE COPS = "+k);
                                    if (cops.get(k).cod_esp.equals(espacioSeleccionado)){
                                        System.out.println("genero Cop = "+k);
                                        Cops prueba1 = new Cops(cops.get(k).img_cop, cops.get(k).desc_cop);
                                        listaCops.add(prueba1);
                                    }
                                }
                                //SystemClock.sleep(250);
                                generarDatosRecyler(listaCops);
                            }
                        }, 2000);
                }
                contador = contador+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Get the application context
        mContext = getApplicationContext();

        // Get the widgets reference from XML layout
        mRelativeLayout =  findViewById(R.id.li);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        final String nombreUsuario = Login.usuario.get(0).nombre;
        menu.findItem(R.id.menu_nombre_usuario).setTitle(nombreUsuario);

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
            // Iniciamos la instancia del inflater
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

            // Inflamos la vista personlizada
            final View customView = inflater.inflate(R.layout.popup_idiomas,null);

            rb_cast = new RadioButton(customView.getContext());
            rb_cast = customView.findViewById(R.id.radio_castellano);

            rb_eus = new RadioButton(customView.getContext());
            rb_eus = customView.findViewById(R.id.radio_euskera);

            rb_eng = new RadioButton(customView.getContext());
            rb_eng = customView.findViewById(R.id.radio_ingles);

            // Iniciamos una nueva instancia del pop up
            mPopupWindow = new PopupWindow(
                    customView,
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT
            );

            // Elevar el pop up
            mPopupWindow.setElevation(5.0f);

            // Coger el id del botón del pop up
            ImageButton closeButton = customView.findViewById(R.id.ib_close);

            // Click listener del botón cerrar del pop up
            closeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Configuration config = new Configuration(Resources.getSystem().getConfiguration());

                    // Dismiss the popup window
                    if (rb_cast.isChecked()){
                        setIdioma("es");
                    } else if (rb_eus.isChecked()){
                        setIdioma("eu");
                    } else if (rb_eng.isChecked()){
                        setIdioma("en");
                    }
                    mPopupWindow.dismiss();
                }
            });
            // Centrar el pop up en mitad de la pantalla
            mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER,0,0);

        } else if (item.getItemId() == R.id.menuitem_cerrar_sesion){
            Intent intent = new Intent(this,Login.class);
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

    public void setIdioma(String lang) {
        Locale idioma = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = idioma;
        res.updateConfiguration(conf, dm);
        this.recreate();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void cargarCops(){
        Aplication myApplication = (Aplication) getApplication();
        final String cod_org= myApplication.codOrg;
        final String cod_espActual = espacioSeleccionado;
        System.out.println("ESPACIO SELECCIONADO" + espacioSeleccionado);
        if (contador>1){
            cops.clear();
        }

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                ConexionBD.URL_Cop,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonarrayCops  = new JSONArray(response);
                            for(int i=0; i < jsonarrayCops.length(); i++) {
                                JSONObject jsonobjectCops = jsonarrayCops.getJSONObject(i);
                                String cod_copCargado    = jsonobjectCops.getString("cod_cop");
                                String cod_espCargado     = jsonobjectCops.getString("cod_esp");
                                String cod_orgCargado  = jsonobjectCops.getString("cod_org");
                                String desc_copCargado  = jsonobjectCops.getString("desc_cop");
                                String img_copCargado = jsonobjectCops.getString("img_cop");
                                //String ind_cop_graficosCargado= jsonobjectCops.getString("ind_cop_graficos");

                                System.out.println("Cops: "+cod_copCargado +" "+cod_espCargado +" "+cod_orgCargado +" "+desc_copCargado+" "+img_copCargado);
                                //Se guardan en el arraylist
                                Cop C = new Cop(cod_copCargado, cod_espCargado, cod_orgCargado, desc_copCargado, img_copCargado);
                                cops.add(C);
                                Aplication myApplication = (Aplication) getApplication();
                                myApplication.cops = cops;
                                System.out.println("INSERTO COP " + i);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("cod_org", cod_org);
                params.put("cod_esp", cod_espActual);
                return params;
            }

        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }






}
