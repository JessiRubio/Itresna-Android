package com.example.itresna_android;

import android.content.Context;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.itresna_android.Senales.PSenales;

import java.util.List;



    public class AdaptadorRecyclerSeñales extends RecyclerView.Adapter<com.example.itresna_android.AdaptadorRecyclerSeñales.ViewHolder> {
        // Colocamos el xml del elemento selector
        public AdaptadorRecyclerSeñales(){
            // Aqui tendria que ir la lista
        }
        @NonNull
        @Override
        public com.example.itresna_android.AdaptadorRecyclerSeñales.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elementoselecto_senales, parent, false);
            return new com.example.itresna_android.AdaptadorRecyclerSeñales.ViewHolder(v);
        }


        // Aqui ponemos los elementos que se muestran en pantalla
        @Override
        public void onBindViewHolder(final com.example.itresna_android.AdaptadorRecyclerSeñales.ViewHolder holder, final int position) {
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



           /*
            if(cod_ususario!=cod_usuario de señal || tip usuario !=1 || permisos ){

                btnEliminar.setVisibility(View.INVISIBLE);
                btnEditar.setVisibility(View.INVISIBLE);

            }*/



            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //aqui se borra la señal


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

                    //aqui se edita la señal

                }
            });


            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //aqui se gestionan los likes de la señal


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
                senal = v.findViewById(R.id.txtSenalRecyclerPCops);
            }
        }







    }


