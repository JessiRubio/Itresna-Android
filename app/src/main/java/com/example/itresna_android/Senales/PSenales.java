package com.example.itresna_android.Senales;

import android.content.Intent;
import android.os.Bundle;

import com.example.itresna_android.AdaptadorRecyclerSenales;
import com.example.itresna_android.Aplication;
import com.example.itresna_android.ConexionBD;
import com.example.itresna_android.Etiqueta;
import com.example.itresna_android.Likes;
import com.example.itresna_android.Senal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.View;
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

import com.example.itresna_android.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PSenales extends AppCompatActivity {


    RecyclerView reyclerViewseñales;
    AdaptadorRecyclerSenales adaptadorRecycler;
    ArrayList<Senal> senales = new ArrayList<>();
    ArrayList<Etiqueta> etiquetas = new ArrayList<>();
    public static ArrayList<Likes> likes = new ArrayList<>();
    String cod_org;
    String cod_esp;
    String cod_cop;

    private TextView tvEspacioSeleccionado;
    private TextView tvCopSeleccionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_psenales);

        reyclerViewseñales = findViewById(R.id.recicler);
        reyclerViewseñales.setLayoutManager (new GridLayoutManager(this, 2));

        // Recogemos los valores que el usuario ha escogido
        Intent intent = getIntent();
        final String imgNombre = intent.getStringExtra("nombreImagen");
        String nombreEmpresa = intent.getStringExtra("nombre");
        String senal = intent.getStringExtra("senal");
        final Aplication myApplication = (Aplication) getApplication();
        cod_org = myApplication.codOrg;
        cod_esp = myApplication.codEspacio;
        cod_cop = intent.getStringExtra("codigo");
        myApplication.cod_cop = cod_cop;
        System.out.println("ESTE ES EL CODIGO DE LA COP"+cod_cop);

        //Cargamos los valores de la bb que cumplen la selección
        cargarSenales();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                Log.e("Tag_log", senales.size() + "");
                /* Inicializamos los dos tv iniciales que contienen la inforamción de
        donde nos encontramos */
                tvEspacioSeleccionado = findViewById(R.id.tv_espacios);
                tvCopSeleccionada = findViewById(R.id.tv_cop);

                /*Le damos la información a cargar*/
                tvEspacioSeleccionado.setText(myApplication.decripcionEspacio);
                tvCopSeleccionada.setText(myApplication.descripcionCop);

                // Especificamos el adaptador para el recycler
                adaptadorRecycler = new AdaptadorRecyclerSenales(senales, etiquetas, likes);
                reyclerViewseñales.setAdapter(adaptadorRecycler);

            }
        }, 1500);

        FloatingActionButton fbnewsenal = findViewById(R.id.fb_newsenal);
        fbnewsenal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newsenal = new Intent(PSenales.this, NewSenal.class);
                startActivity(newsenal);
            }
        });


    }


    public void cargarSenales(){

        final String cod_orgActual= String.valueOf(cod_org);
        final String cod_espActual= String.valueOf(cod_esp);
        final String cod_copActual= String.valueOf(cod_cop);



        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                ConexionBD.URL_Senal,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonarray  = new JSONArray(response);

                            for(int i=0; i < jsonarray.length(); i++) {
                                JSONObject jsonobject = jsonarray.getJSONObject(i);

                                String cod_senalCargado    = jsonobject.getString("cod_senal");
                                String cod_copCargado     = jsonobject.getString("cod_cop");
                                String cod_espCargado  = jsonobject.getString("cod_esp");
                                String cod_orgCargado  = jsonobject.getString("cod_org");
                                String cod_etiquetaCargado = jsonobject.getString("cod_etiqueta");
                                String cod_usuarioCargado= jsonobject.getString("cod_usuario ");
                                String desc_senalCargado= jsonobject.getString("desc_senal ");
                                String enlaceCargado= jsonobject.getString("enlace ");
                                String fecha_horaCargado= jsonobject.getString("fecha_hora ");
                                String img_senalCargado= jsonobject.getString("img_senal ");
                                String tituloCargado= jsonobject.getString("titulo ");

                                System.out.println("Señales: "+cod_senalCargado +" "+cod_copCargado +" "+cod_espCargado +" "+cod_orgCargado+" " +
                                        cod_etiquetaCargado+" "+cod_usuarioCargado+" "+desc_senalCargado+" "+enlaceCargado+" "+fecha_horaCargado+" " +
                                        img_senalCargado+" "+tituloCargado);
                                //Se guardan en el arraylist
                                Senal S = new Senal(cod_senalCargado ,cod_copCargado ,cod_espCargado,cod_orgCargado,cod_etiquetaCargado,cod_usuarioCargado,
                                        desc_senalCargado,enlaceCargado,fecha_horaCargado,img_senalCargado,tituloCargado);
                                senales.add(S);

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
                params.put("cod_org", cod_orgActual);
                params.put("cod_esp", cod_espActual);
                params.put("cod_cop", cod_copActual);
                return params;
            }

        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        cargarEtiquetas();
        cargarLikes();

    }

    public void cargarEtiquetas(){

        final String cod_orgActual= String.valueOf(cod_org);
        final String cod_espActual= String.valueOf(cod_esp);
        final String cod_copActual= String.valueOf(cod_cop);


        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                ConexionBD.URL_Etiqueta,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonarray  = new JSONArray(response);

                            for(int i=0; i < jsonarray.length(); i++) {
                                JSONObject jsonobject = jsonarray.getJSONObject(i);

                                String cod_etiquetaCargado    = jsonobject.getString("cod_etiqueta");
                                String cod_copCargado     = jsonobject.getString("cod_cop");
                                String cod_espCargado  = jsonobject.getString("cod_esp");
                                String cod_orgCargado  = jsonobject.getString("cod_org");
                                String desc_etiquetaCargado = jsonobject.getString("desc_etiqueta");


                                System.out.println("Etiquetas: "+cod_etiquetaCargado +" "+cod_copCargado +" "+cod_espCargado +" "+cod_orgCargado+" " +
                                        desc_etiquetaCargado);

                                //Se guardan en el arraylist
                                Etiqueta E = new Etiqueta(cod_etiquetaCargado ,cod_copCargado ,cod_espCargado,cod_orgCargado,desc_etiquetaCargado);
                                etiquetas.add(E);

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
                params.put("cod_org", cod_orgActual);
                params.put("cod_esp", cod_espActual);
                params.put("cod_cop", cod_copActual);
                return params;
            }

        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    public void cargarLikes(){

        final String cod_orgActual= String.valueOf(cod_org);
        final String cod_espActual= String.valueOf(cod_esp);
        final String cod_copActual= String.valueOf(cod_cop);


        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                ConexionBD.URL_Likes,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {


                            JSONArray jsonarray  = new JSONArray(response);



                            for(int i=0; i < jsonarray.length(); i++) {
                                JSONObject jsonobject = jsonarray.getJSONObject(i);

                                String cod_senalCargado    = jsonobject.getString("cod_senal");
                                String cod_copCargado     = jsonobject.getString("cod_cop");
                                String cod_espCargado  = jsonobject.getString("cod_esp");
                                String cod_orgCargado  = jsonobject.getString("cod_org");
                                String cod_usuarioCargado = jsonobject.getString("cod_usuario");


                                System.out.println("Likes: "+cod_senalCargado +" "+cod_copCargado +" "+cod_espCargado +" "+cod_orgCargado+" " +
                                        cod_usuarioCargado);

                                //Se guardan en el arraylist
                                Likes L = new Likes(cod_senalCargado, cod_copCargado, cod_espCargado, cod_orgCargado, cod_usuarioCargado);
                                likes.add(L);

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
                params.put("cod_org", cod_orgActual);
                params.put("cod_esp", cod_espActual);
                params.put("cod_cop", cod_copActual);
                return params;
            }

        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}
