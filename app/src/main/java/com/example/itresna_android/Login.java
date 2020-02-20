package com.example.itresna_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    Button Entrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Entrar = findViewById(R.id.botonEntrar);
        Entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this,PCops.class);
                startActivity(intent);


                // metedos de prueba para verificar que funcionan insert y select
                //crear();
                //cargarUsuario();







            }
        });

    }


    public void crear(){
        final int tip_variable= 7;
        final String desc_variable="prueba";


        StringRequest stringRequest =new StringRequest(Request.Method.POST,
                ConexionBD.URL_REGISTER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject =new JSONObject(response);
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "volley error",Toast.LENGTH_LONG).show();

                    }

                }

        ){



            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params =new HashMap<>();
                params.put("tip_variable", String.valueOf(tip_variable));
                params.put("desc_variable", desc_variable);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    public void cargarUsuario(){

            final String cod_usuario="jaime.corrales@gmail.com";
            final String sarbidea="jaime123";

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                ConexionBD.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            System.out.println(response);
                            if(!obj.getBoolean("error")){

                                System.out.println(obj.getString("cod_usuario")+" "+obj.getString("tip_usuario")+" "+obj.getString("sarbidea"));

                            }else{
                                Toast.makeText(
                                        getApplicationContext(),
                                        obj.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();
                                System.out.println("errooorrrrr");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("errooorrrrr3");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                        System.out.println("errooorrrrr2");
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("cod_usuario", cod_usuario);
                params.put("sarbidea", sarbidea);
                return params;
            }

        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        //RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }
}
