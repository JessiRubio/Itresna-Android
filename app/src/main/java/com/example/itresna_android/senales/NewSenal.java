package com.example.itresna_android.Senales;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.itresna_android.AdaptadorComentario;
import com.example.itresna_android.AdaptadorRecyclerSenales;
import com.example.itresna_android.Aplication;
import com.example.itresna_android.ConexionBD;
import com.example.itresna_android.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jibble.simpleftp.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;



public class NewSenal extends Activity {

    private Button btn_cancelar;
    private Button btn_Anadir;

    private EditText url;
    private EditText descripcion;
    private WebView myWebView;
    Bitmap bmp;
    String tituloPag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_senal);
        Permisos();
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED ) {
            Permisos();
        }

        //Creamos el PopUp
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //Le damos medidas
      /*  int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width), (int)(height/2));

        //Se las asignamos
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = 0;

        getWindow().setAttributes(params);*/

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
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            bmp = myWebView.getFavicon();
                            tituloPag=myWebView.getTitle();
                        }
                    }, 2000);
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
                final Aplication myAplication = (Aplication) getApplication();
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
                        Map<String, String> params = new HashMap<>();
                        params.put("cod_cop", String.valueOf(cod_cop));
                        params.put("cod_esp", String.valueOf(cod_esp));
                        params.put("cod_org", String.valueOf(cod_org));
                        params.put("cod_usuario", cod_usuario);
                        params.put("enlace", url.getText().toString());
                        params.put("desc_senal", desc_senal);
                        FileOutputStream photo = null;
                        SimpleFTP ftp = new SimpleFTP();

                        File root = Environment.getExternalStorageDirectory();
                       // File myDir = new File(root);
                        File myDir = root;
                        myDir.mkdirs();
                        String fname = "/Image-" + desc_senal+ ".png";
                        File file = new File(myDir, fname);
                        if (file.exists()) file.delete();
                        Log.i("LOAD", root + fname);
                        try {
                            FileOutputStream out = new FileOutputStream(file.getAbsolutePath());
                            bmp.compress(Bitmap.CompressFormat.PNG, 90, new FileOutputStream(file.getAbsolutePath()));
                            out.flush();
                            out.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        try {
                            ftp.connect("itresna.fptxurdinaga.in", 21, "itresna", "Abcd_1234");
                            // Set binary mode.
                            ftp.bin();

                            // Change to a new working directory on the FTP server.
                            ftp.cwd("/web/itresna.fptxurdinaga.in/public_html/media/Senales");

                            // Upload some files.
                            ftp.stor(file);

                            // Quit from the FTP server.
                            ftp.disconnect();

                            params.put("img_senal", "http://itresna.fptxurdinaga.in/media/Senales/" + "Image-" + desc_senal+ ".png ");
                            params.put("titulo", tituloPag);
                            System.out.println("Foto de la señal --> "+params.get("img_senal"));
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Errror -->"+e);
                        }

                        // Connect to an FTP server on port 21.
                        return params;
                             }
                };

                RequestQueue requestQueue= Volley.newRequestQueue(NewSenal.this);
                requestQueue.add(stringRequest);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Intent intent = new Intent(NewSenal.this, PSenales.class);
                        startActivity(intent);
                    }
                }, 1000);


            }
        });
    }
    private void Permisos(){
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED ) {
            if (ContextCompat.checkSelfPermission(NewSenal.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {



                    // El usuario no necesitas explicación, puedes solicitar el permiso:
                    ActivityCompat.requestPermissions(NewSenal.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            112);

                    //
                }
        } else {
            Log.i("Mensaje", "Tienes permiso para usar la camara.");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    ;
}
