package com.example.itresna_android.recuperarContrasena;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.itresna_android.ConexionBD;
import com.example.itresna_android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RecuperarContrasena extends AppCompatActivity {

    TextView tvMiraEmail;
    Button btnEnviar;
    EditText etEmail;
    String nombreCargado, cod_usuarioCargado, sarbideaCargado, ape1Cargado,ape2Cargado ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena);

        tvMiraEmail=findViewById(R.id.textViewMiraEmail);
        btnEnviar=findViewById(R.id.buttonEnviar);
        etEmail=findViewById(R.id.editTextEmail);

        tvMiraEmail.setVisibility(View.INVISIBLE);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RecuperarContrasena();

                tvMiraEmail.setVisibility(View.VISIBLE);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {

                        // Envia el correo
                        final ProgressDialog dialog = new ProgressDialog(RecuperarContrasena.this);
                        dialog.setTitle("Enviando email");
                        dialog.setMessage("Espera por favor");
                        dialog.show();
                        Thread sender = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    // Se introduce el email y la contraseña. Este email enviara los correos.
                                    GMailSender sender = new GMailSender("iTresna.TX@gmail.com", "iTresna1234");
                                    // Asuto, mensaje, quien lo envia, a quien lo envia
                                    sender.sendMail("iTresna",
                                            "Hola "+ nombreCargado+" "+ape1Cargado+" "+ape2Cargado+  "\n\n\nGracias por ponerte en contacto con nosotros. Tu contraseña es " +sarbideaCargado,
                                            "iTresna.TX@gmail.com", etEmail.getText().toString() );
                                    dialog.dismiss();
                                } catch (Exception e) {
                                    Log.d("mylog", "Error: " + e.getMessage());
                                }
                            }
                        });
                        sender.start();

                    }
                }, 1000);







                /*

                cod_usuarioCargado=jsonarrayCops.getString("cod_usuario");
                                sarbideaCargado=jsonarrayCops.getString("sarbidea");
                                nombreCargado=jsonarrayCops.getString("nombre");
                                ape1Cargado=jsonarrayCops.getString("ape1");
                                ape2Cargado=jsonarrayCops.getString("ape2");
                 */






            }
        });


    }

    public void RecuperarContrasena(){

        //final String cod_org= String.valueOf(getIntent().getStringExtra("valor1"));
        final String cod_usuario= etEmail.getText().toString();
        System.out.println(etEmail.getText().toString());

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                ConexionBD.URL_RecuperarContrasena,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonarray  = new JSONArray(response);


                            for(int i=0; i < jsonarray.length(); i++) {
                                JSONObject jsonobject = jsonarray.getJSONObject(i);

                                cod_usuarioCargado=jsonobject.getString("cod_usuario");
                                sarbideaCargado=jsonobject.getString("sarbidea");
                                nombreCargado=jsonobject.getString("nombre");
                                ape1Cargado=jsonobject.getString("ape1");
                                ape2Cargado=jsonobject.getString("ape2");

                                System.out.println("DATOOOOOOS "+cod_usuarioCargado +" "+sarbideaCargado +" "+nombreCargado +" "+ape1Cargado+" "+ape2Cargado);


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

        RequestQueue requestQueue= Volley.newRequestQueue(RecuperarContrasena.this);
        requestQueue.add(stringRequest);

    }






}
