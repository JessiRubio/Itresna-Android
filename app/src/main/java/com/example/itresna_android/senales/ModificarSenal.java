package com.example.itresna_android.Senales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import com.example.itresna_android.ConexionBD;
import com.example.itresna_android.R;

public class ModificarSenal extends AppCompatActivity {

    Button btnModificar, btnCancelar;
    EditText etUrl, etDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_senal);

        btnModificar= findViewById(R.id.btnModificar);
        btnCancelar=findViewById(R.id.btnCancelar);
        etUrl=findViewById(R.id.etUrl);
        etDesc=findViewById(R.id.etDesc);

        final int cod_senal=getIntent().getIntExtra("valor1",0);
        final int cod_cop=getIntent().getIntExtra("valor2",0);
        final int cod_esp=getIntent().getIntExtra("valor3",0);
        final int cod_org=getIntent().getIntExtra("valor4",0);
        final String url=getIntent().getStringExtra("valor5");
        final String desc=getIntent().getStringExtra("valor6");

        //System.out.println("Modificar datos: "+cod_senal+" "+cod_cop+" "+cod_esp+" "+cod_org+" "+url+" "+desc);
        etUrl.setText(url);
        etDesc.setText(desc);


        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String urlModificado=etUrl.getText().toString();
                final String descModificado=etDesc.getText().toString();

                StringRequest stringRequest =new StringRequest(Request.Method.POST,
                        ConexionBD.URL_ModificarSenal,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject =new JSONObject(response);
                                    //Toast.makeText(getApplicationContext(), jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                //Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_LONG).show();
                                //Toast.makeText(getApplicationContext(), "volley error",Toast.LENGTH_LONG).show();

                            }

                        }

                ){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params =new HashMap<>();
                        params.put("cod_senal", String.valueOf(cod_senal));
                        params.put("cod_cop", String.valueOf(cod_cop));
                        params.put("cod_esp", String.valueOf(cod_esp));
                        params.put("cod_org", String.valueOf(cod_org));
                        params.put("enlace", urlModificado);
                        params.put("desc_senal", descModificado);

                        return params;
                    }
                };

                RequestQueue requestQueue= Volley.newRequestQueue(ModificarSenal.this);
                requestQueue.add(stringRequest);

                //Espera un segundo y medio y vuelve a la pantalla PSenales
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {

                        Intent intent = new Intent(ModificarSenal.this, PSenales.class);
                        startActivity(intent);


                    }
                }, 1500);


            }
        });


        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ModificarSenal.this, PSenales.class);
                startActivity(intent);


            }
        });




    }
}
