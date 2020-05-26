package com.example.itresna_android.senales;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.example.itresna_android.Aplication;
import com.example.itresna_android.ConexionBD;
import com.example.itresna_android.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewSenal extends Activity {

    private Button btn_cancelar;
    private Button btn_Anadir;

    private EditText url;
    private EditText descripcion;
    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_senal);

        //Creamos el PopUp
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //Le damos medidas
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width), (int)(height/2));

        //Se las asignamos
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        //Funcionalidades pop up
        btn_cancelar = findViewById(R.id.btnCancelarSenal);
        btn_Anadir = findViewById(R.id.btnAnadirSenal);

        url = findViewById(R.id.urlEntregada);
        descripcion = findViewById(R.id.descripcionEntregada);
        myWebView = (WebView) findViewById(R.id.webview);

        url.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus && (url.getText().toString()!="")){
                    myWebView.loadUrl(url.getText().toString());
                }
            }
        });



        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btn_Anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int cod_org, cod_cop, cod_esp;
                Aplication myAplication = (Aplication) getApplication();
                cod_org = Integer.parseInt(myAplication.codOrg);
                cod_esp = Integer.parseInt(myAplication.codEspacio);
                cod_cop = Integer.parseInt(myAplication.cod_cop);
                final String cod_usuario = myAplication.Usuariologueado;
                final String desc_senal = descripcion.getText().toString();

                StringRequest stringRequest =new StringRequest(Request.Method.POST,
                        ConexionBD.URL_CrearSenal,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject =new JSONObject(response);
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
                ){
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params =new HashMap<>();
                        params.put("cod_cop", String.valueOf(cod_cop));
                        params.put("cod_esp", String.valueOf(cod_esp));
                        params.put("cod_org", String.valueOf(cod_org));
                        params.put("cod_usuario", cod_usuario);
                        params.put("enlace", url.getText().toString());
                        params.put("desc_senal", desc_senal);
                        FileOutputStream photo = null;
                        try {
                            photo = new FileOutputStream(Environment.getExternalStorageDirectory() + "/itresna/" +myWebView.getTitle() + ".jpg" );
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        params.put("img_senal", String.valueOf(myWebView.getFavicon().compress(Bitmap.CompressFormat.JPEG, 100, photo)));
                        params.put("titulo", myWebView.getTitle());
                        return params;
                    }
                };
                RequestQueue requestQueue= Volley.newRequestQueue(NewSenal.this);
                requestQueue.add(stringRequest);
            }
        });
    }
}
