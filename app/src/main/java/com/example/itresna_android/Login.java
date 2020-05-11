package com.example.itresna_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class Login extends AppCompatActivity {
    Button btnEntrar;
    EditText etCod_usuario, etSarbidea;
    ArrayList<Permisos> permisos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etCod_usuario= findViewById(R.id.nombreUsuario);
        etSarbidea=findViewById(R.id.contraseñaUsuario);
        btnEntrar = findViewById(R.id.botonEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // metedos de prueba para verificar que funcionan insert y select
                //crear();
                Login();
                cargarPermisos();

            }
        });

    }

    public void Login(){

        //Datos de prueba
        //final String cod_usuario="jaime.corrales@gmail.com";
        //final String sarbidea="jaime123";

        final String cod_usuario= etCod_usuario.getText().toString();
        final String sarbidea= etSarbidea.getText().toString();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                ConexionBD.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            //System.out.println(response);
                            if(!obj.getBoolean("error")){

                                String cod_usuarioCargado=obj.getString("cod_usuario");
                                String tip_usuarioCargado=obj.getString("tip_usuario");
                                String cod_orgCargado=obj.getString("cod_org");
                                String sarbideaCargado=obj.getString("sarbidea");
                                String nombreCargado=obj.getString("nombre");
                                String ape1Cargado=obj.getString("ape1");
                                String ape2Cargado=obj.getString("ape2");


                                System.out.println("Usuario: "+cod_usuarioCargado+" "+tip_usuarioCargado+" "+cod_orgCargado+" "+sarbideaCargado+" "+nombreCargado+" "+ape1Cargado+
                                        " "+ape2Cargado);
                                //String cod_org=obj.getString("cod_org");
                                //Toast.makeText(getApplicationContext(),obj.getString("cod_usuario"), Toast.LENGTH_LONG).show();


                                Intent intent = new Intent(Login.this, PCops.class);
                                String valor1  = cod_orgCargado;
                                intent.putExtra("valor1", valor1 );
                                //String valor2  = cod_usuario;
                                //intent.putExtra("valor2", valor2 );
                                startActivity(intent);

                            }else{
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
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
                params.put("cod_usuario", cod_usuario);
                params.put("sarbidea", sarbidea);
                return params;
            }

        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        //RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    public void cargarPermisos(){

        final String cod_usuario= etCod_usuario.getText().toString();


        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                ConexionBD.URL_Permisos,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonarray  = new JSONArray(response);

                            for(int i=0; i < jsonarray.length(); i++) {
                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                String cod_usuarioCargado    = jsonobject.getString("cod_usuario");
                                String cod_copCargado     = jsonobject.getString("cod_cop");
                                String cod_espCargado  = jsonobject.getString("cod_esp");
                                String cod_orgCargado  = jsonobject.getString("cod_org");
                                String ind_adminCargado = jsonobject.getString("ind_admin");
                                //String ind_cop_graficosCargado= jsonobjectCops.getString("ind_cop_graficos");

                                System.out.println("Permisos: "+cod_usuarioCargado +" "+cod_copCargado +" "+cod_espCargado +" "+cod_orgCargado+" "+ind_adminCargado);
                                //Se guardan en el arraylist
                                Permisos P = new Permisos(cod_usuarioCargado, cod_copCargado, cod_espCargado, cod_orgCargado, ind_adminCargado);
                                permisos.add(P);

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
                params.put("cod_usuario", cod_usuario);
                return params;
            }

        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }





    //Metodo de prueba para insertar datos
    /*
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
     */

}
