package com.example.itresna_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** Pantalla de carga del login a Pcops para gestionar le debug de carga de info **/
public class pantallaCarga extends AppCompatActivity {
    String eslogan;
    String espacioSeleccionado;
    ArrayList<Espacio> espaciosCarga = new ArrayList<>();
    public ArrayList<Cop> cops = new ArrayList<>();
    ArrayList<Cop> Fotos = new ArrayList<>();
    Bitmap bmp;
    int i;
    String fotoUrl;
    int porcentaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_carga);
        cargarDatos();
        cargarEspacios();
        final String nombreUsuario = String.valueOf(getIntent().getStringExtra("NombreUsuario"));
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Aplication myApplication = (Aplication) getApplication();
                myApplication.espacios = espaciosCarga;
                myApplication.espacioSeleccionado = espacioSeleccionado;
                myApplication.cops = cops;
                myApplication.eslogan = eslogan;
              //  cargarFotos();
                myApplication.copsFoto = Fotos;
                Intent intent = new Intent(getApplicationContext(), PCops.class);
                intent.putExtra("NombreUsuario", nombreUsuario);
                startActivity(intent);
            }
        }, 2000);

    }

    public void cargarDatos() {

        final String cod_org = String.valueOf(getIntent().getStringExtra("valor1"));
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                ConexionBD.URL_Org,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            if (!obj.getBoolean("error")) {
                                // tEslogan.setText(obj.getString("eslogan_org"));
                                eslogan = obj.getString("eslogan_org");


                            } else {

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                //params.put("cod_usuario", cod_usuario);
                params.put("cod_org", cod_org);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        //RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    public void cargarEspacios() {

        final String cod_org = String.valueOf(getIntent().getStringExtra("valor1"));
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                ConexionBD.URL_Esp,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonarray = new JSONArray(response);
                            for (int i = 0; i < jsonarray.length(); i++) {
                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                String cod_esp = jsonobject.getString("cod_esp");
                                String cod_org = jsonobject.getString("cod_org");
                                String desc_esp = jsonobject.getString("desc_esp");
                                String ind_esp_curacion = jsonobject.getString("ind_esp_curacion");
                                String orden = jsonobject.getString("orden");

                                Espacio E = new Espacio(cod_esp, cod_org, desc_esp, ind_esp_curacion, orden);
                                espaciosCarga.add(E);
                            }

                            //TEMPORAL
                            Intent intent = getIntent();
                            if (intent.getBooleanExtra("carga", false)) {
                                espacioSeleccionado = intent.getStringExtra("nombre_espacio");
                                for (int i = 0; espaciosCarga.size() > i; i++) {
                                    if (espaciosCarga.get(i).desc_esp.equals(espacioSeleccionado)) {
                                        espacioSeleccionado = espaciosCarga.get(i).cod_esp;
                                    }
                                }
                            } else {
                                espacioSeleccionado = espaciosCarga.get(0).cod_esp;
                            }
                            cargarCops();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                //params.put("cod_usuario", cod_usuario);
                params.put("cod_org", cod_org);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    public void cargarCops() {

        final String cod_org = String.valueOf(getIntent().getStringExtra("valor1"));
        final String cod_espActual = espacioSeleccionado;

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                ConexionBD.URL_Cop,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonarrayCops = new JSONArray(response);

                            for (int i = 0; i < jsonarrayCops.length(); i++) {
                                JSONObject jsonobjectCops = jsonarrayCops.getJSONObject(i);
                                String cod_copCargado = jsonobjectCops.getString("cod_cop");
                                String cod_espCargado = jsonobjectCops.getString("cod_esp");
                                String cod_orgCargado = jsonobjectCops.getString("cod_org");
                                String desc_copCargado = jsonobjectCops.getString("desc_cop");
                                String img_copCargado = jsonobjectCops.getString("img_cop");
                                //String ind_cop_graficosCargado= jsonobjectCops.getString("ind_cop_graficos");

                                Cop C = new Cop(cod_copCargado, cod_espCargado, cod_orgCargado, desc_copCargado, img_copCargado);
                                cops.add(C);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("cod_org", cod_org);
                params.put("cod_esp", cod_espActual);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void cargarFotos() {
        InputStream in = null;
        for (i = 0; cops.size() > i; i++) {
            fotoUrl = cops.get(i).img_cop;
            try {
                in = new java.net.URL(fotoUrl).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
            }
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Cop CopFoto = new Cop(cops.get(i).desc_cop, bmp);
                    Fotos.add(CopFoto);
                }
            }, 45);
        }

/*
        for(i = 0; cops.size()>i;i++){

            fotoUrl = cops.get(i).img_cop;
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    InputStream in = null;
                    try {
                        in = new java.net.URL(fotoUrl).openStream();
                    } catch (IOException e) {
                        System.out.println("Error NO SE PUDO COJER EL URL");
                    }
                    bmp = BitmapFactory.decodeStream(in);

                }
            });
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    Cop CopFoto = new Cop(cops.get(i).desc_cop, bmp);
                    System.out.println("INSERTO FOTO -->"+i);
                    Fotos.add(CopFoto);
                }
            }, 45);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("Error NO SE PUEDE ESPERAR");
                e.printStackTrace();
            }*/
        }

    }

