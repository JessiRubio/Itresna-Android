package com.example.itresna_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class popUpcomentarios extends AppCompatActivity {
    RecyclerView reyclerViewseñales;
    AdaptadorComentario adaptadorRecycler;
    //ArrayList<Cops> listaComentarios = new ArrayList<Cops>();
    ArrayList<Comentario> comentarios = new ArrayList<>();
    String cod_orgActual;
    String cod_espActual;
    String cod_copActual;
    String cod_senalActual;
    //Datos de prueba para cargar los comentarios, una vez el recycler señales funcione, se cogerán de ahí los datos.
    Aplication myApplication;
    Button boton;
    EditText comentarioEscrito;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_upcomentarios);
        cargarComentarios();
        System.out.println("TAMAÑO DE LA LISTA DE COMENTARIOS -->"+comentarios.size());


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                reyclerViewseñales = findViewById(R.id.recyclerViewComentarios);
                reyclerViewseñales.setLayoutManager (new LinearLayoutManager(getApplicationContext()));
                // Especificamos el adaptador para el recycler
                adaptadorRecycler = new AdaptadorComentario(comentarios);
                reyclerViewseñales.setAdapter(adaptadorRecycler);
            }
        }, 100);
        boton = findViewById(R.id.enviarComentario);

    }




    public void cargarComentarios(){
        myApplication= (Aplication) this.getApplication();
        cod_orgActual= myApplication.codOrg;
        cod_espActual= myApplication.codEspacio;
        cod_copActual= myApplication.cod_cop;
        cod_senalActual= myApplication.cod_senal;
        System.out.println("SEÑALLL -->"+cod_senalActual);

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                ConexionBD.URL_Comentario,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonarray  = new JSONArray(response);

                            for(int i=0; i < jsonarray.length(); i++) {
                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                String cod_comentarioCargado    = jsonobject.getString("cod_comentario");
                                String cod_senalCargado     = jsonobject.getString("cod_senal");
                                String cod_copCargado  = jsonobject.getString("cod_cop");
                                String cod_espCargado  = jsonobject.getString("cod_esp");
                                String cod_orgCargado = jsonobject.getString("cod_org");
                                String cod_usuarioCargado = jsonobject.getString("cod_usuario ");
                                String comentarioCargado = jsonobject.getString("comentario ");

                                System.out.println("Comenmtarios: "+cod_comentarioCargado+" "+cod_senalCargado+" "+cod_copCargado+" "+cod_espCargado+" "+cod_orgCargado+" "+cod_usuarioCargado+" "+comentarioCargado);

                                //Se guardan en el arraylist
                                Comentario C = new Comentario(cod_comentarioCargado,cod_senalCargado,cod_copCargado,cod_espCargado,cod_orgCargado,cod_usuarioCargado,comentarioCargado);
                                comentarios.add(C);

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
                params.put("cod_senal", cod_senalActual);
                return params;
            }

        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }





}
