package com.example.itresna_android.senales;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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
import com.example.itresna_android.Likes;
import com.example.itresna_android.Login;
import com.example.itresna_android.R;
import com.example.itresna_android.Senales.ModificarSenal;
import com.example.itresna_android.Senales.PSenales;


public class AdaptadorRecyclerSenales extends RecyclerView.Adapter<AdaptadorRecyclerSenales.ViewHolder> {
        // Colocamos el xml del elemento selector

        //Datos de prueba
         boolean liked;
         int likes=5;

        public AdaptadorRecyclerSenales(){

            // Aqui tendria que ir la lista


        }
        @NonNull
        @Override
        public AdaptadorRecyclerSenales.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elementoselecto_senales, parent, false);
            return new AdaptadorRecyclerSenales.ViewHolder(v);
        }


        // Aqui ponemos los elementos que se muestran en pantalla
        @Override
        public void onBindViewHolder(final AdaptadorRecyclerSenales.ViewHolder holder, final int position) {
            Button btnComentario = holder.itemView.findViewById(R.id.botonComentarios);
            btnComentario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.itemView.getContext(), popUpcomentarios.class);
                    holder.itemView.getContext().startActivity(intent);
                }
            });


            Button btnEliminar = holder.itemView.findViewById(R.id.botonBasura);
            Button btnEditar = holder.itemView.findViewById(R.id.botonLapiz);
            ImageView btnLike = holder.itemView.findViewById(R.id.imageCorazon);
            final TextView tvLikes = holder.itemView.findViewById(R.id.textViewLike);

            tvLikes.setText(String.valueOf(likes));
            btnEliminar.setVisibility(View.INVISIBLE);
            btnEditar.setVisibility(View.INVISIBLE);

           //Datos de prueba,
            int cod_senal= 2;
            int cod_cop=1;
            int cod_esp=1;
            int cod_org=1;
            String cod_usuarioSenal="jon";

            for (int i = 0; i< Login.permisos.size(); i++){

                if (Login.usuario.get(0).getTip_usuario().equals("1") || Login.usuario.get(0).getCod_usuario().equals(cod_usuarioSenal) || Login.permisos.get(i).getCod_cop().equals(Integer.toString(cod_cop)) && Login.permisos.get(i).getInd_admin().equals("1") ){
                    btnEliminar.setVisibility(View.VISIBLE);
                    btnEditar.setVisibility(View.VISIBLE);
                }

            }







            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Aqui se borra la señal

                    //Datos de prueba, una vez se carguen las señales, los datos se cogeran de ahi
                    final int cod_senal= 2;
                    final int cod_cop=1;
                    final int cod_esp=1;
                    final int cod_org=1;

                    StringRequest stringRequest =new StringRequest(Request.Method.POST,
                            ConexionBD.URL_BorrarSenal,
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
                            return params;
                        }
                    };

                    RequestQueue requestQueue= Volley.newRequestQueue(holder.itemView.getContext());
                    requestQueue.add(stringRequest);

                }
            });


            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Aqui solamente se envian los datos de la señal a la pantalla ModificarSenal

                    //Datos de prueba
                    int cod_senal=1;
                    int cod_cop=1;
                    int cod_esp=1;
                    int cod_org=1;
                    String url="htpps:";
                    String desc="holaa";

                    Intent intent = new Intent(holder.itemView.getContext(), ModificarSenal.class);
                    int valor1  = cod_senal;
                    intent.putExtra("valor1", valor1 );
                    int valor2 =cod_cop;
                    intent.putExtra("valor2", valor2 );
                    int valor3=cod_esp;
                    intent.putExtra("valor3", valor3 );
                    int valor4=cod_org;
                    intent.putExtra("valor4", valor4 );
                    String valor5  = url;
                    intent.putExtra("valor5", valor5 );
                    String valor6  = desc;
                    intent.putExtra("valor6", valor6 );
                    holder.itemView.getContext().startActivity(intent);

                }
            });


            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //Aqui se gestionan los likes de la señal
                    //Datos de prueba
                    final int cod_senal= 1;
                    final int cod_cop=1;
                    final int cod_esp=1;
                    final int cod_org=1;
                    final String cod_usuario="jon";

                    //Se conprueba si el usuario actual ha dado tiene like
                    for (int i=0;i<PSenales.likes.size();i++){
                        //System.out.println("El arraylist "+PSenales.likes.get(i).cod_usuario +" "+PSenales.likes.get(i).cod_senal );
                        if (PSenales.likes.get(i).cod_usuario.equals(cod_usuario) && PSenales.likes.get(i).cod_senal.equals(Integer.toString(cod_senal))){
                            System.out.println("true");
                            liked=true;
                        }
                        else {
                            liked=false;
                            System.out.println("false");
                        }
                    }

                    if (liked==true){
                        likes--;
                        tvLikes.setText(String.valueOf(likes));
                        liked=false;

                        //Se borra el like en la base de datos
                        StringRequest stringRequest =new StringRequest(Request.Method.POST,
                                ConexionBD.URL_BorrarLike,
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
                                params.put("cod_senal", String.valueOf(cod_senal));
                                params.put("cod_cop", String.valueOf(cod_cop));
                                params.put("cod_esp", String.valueOf(cod_esp));
                                params.put("cod_org", String.valueOf(cod_org));
                                params.put("cod_usuario", cod_usuario);
                                return params;
                            }
                        };
                        RequestQueue requestQueue= Volley.newRequestQueue(holder.itemView.getContext());
                        requestQueue.add(stringRequest);

                        //Elimina el like del arraylist
                        for (int i=0;i<PSenales.likes.size();i++){
                            if (PSenales.likes.get(i).cod_usuario.equals(cod_usuario) && PSenales.likes.get(i).cod_senal.equals(Integer.toString(cod_senal))){
                                PSenales.likes.remove(i);
                                System.out.println();
                            }
                        }
                    }

                    else if (liked==false){
                        likes++;
                        tvLikes.setText(String.valueOf(likes));
                        liked=true;
                        //Se crea el like en la base de datos

                        StringRequest stringRequest =new StringRequest(Request.Method.POST,
                                ConexionBD.URL_CrearLike,
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
                                params.put("cod_senal", String.valueOf(cod_senal));
                                params.put("cod_cop", String.valueOf(cod_cop));
                                params.put("cod_esp", String.valueOf(cod_esp));
                                params.put("cod_org", String.valueOf(cod_org));
                                params.put("cod_usuario", cod_usuario);
                                return params;
                            }
                        };
                        RequestQueue requestQueue= Volley.newRequestQueue(holder.itemView.getContext());
                        requestQueue.add(stringRequest);

                        //Añade el like al arraylist
                        Likes L = new Likes(String.valueOf(cod_senal), String.valueOf(cod_cop), String.valueOf(cod_esp), String.valueOf(cod_org), cod_usuario);
                        PSenales.likes.add(L);
                    }


                }
            });
            
        }

        @Override
        public int getItemCount() {
            return 8;
        }

        // Esto es necesario
        static class ViewHolder extends RecyclerView.ViewHolder {
            // Aqui tambien ponemos los elementos del elemento selector
            private TextView nombreCop;
            private ImageView imgRecycler;
            private  TextView senal;
            ViewHolder(View v) {
                super(v);
                imgRecycler = v.findViewById(R.id.imgRecyclerPCops);
                nombreCop = v.findViewById(R.id.txtNomEmpresaRecyclerPCops);
                //senal = v.findViewById(R.id.txtSenalRecyclerPCops);
            }
        }







    }


