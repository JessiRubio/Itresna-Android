package com.example.itresna_android;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.example.itresna_android.senales.ModificarSenal;
import com.example.itresna_android.senales.PSenales;
import com.squareup.picasso.Picasso;


public class AdaptadorRecyclerSenales extends RecyclerView.Adapter<AdaptadorRecyclerSenales.ViewHolder> {
    // Colocamos el xml del elemento selector

    //Datos de prueba
     boolean liked;
     ArrayList<Senal> listaSenales;
     ArrayList<Etiqueta> listaEtiquetas;
     ArrayList<Likes> listalikes;
     int likes;
     int cantidadLikes;
    ImageView ivPortada;

        public AdaptadorRecyclerSenales(ArrayList<Senal> ls, ArrayList<Etiqueta> le, ArrayList<Likes> ll){
            // Aqui tendria que ir la lista
            this.listaSenales = ls;
            this.listaEtiquetas = le;
            this.listalikes = ll;
            likes = 0;
        }
        @NonNull
        @Override
        public AdaptadorRecyclerSenales.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elementoselecto_senales, parent, false);
            return new AdaptadorRecyclerSenales.ViewHolder(v);
        }


        // Aqui ponemos los elementos que se muestran en pantalla
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            Button btnComentario = holder.itemView.findViewById(R.id.botonComentarios);
            btnComentario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.itemView.getContext(), popUpcomentarios.class);
                    Aplication myApplication = (Aplication) holder.itemView.getContext().getApplicationContext();
                    myApplication.cod_senal= listaSenales.get(position).cod_senal;
                    holder.itemView.getContext().startActivity(intent);
                }
            });


            Button btnEliminar = holder.itemView.findViewById(R.id.botonBasura);
            Button btnEditar = holder.itemView.findViewById(R.id.botonLapiz);
            ImageView btnLike = holder.itemView.findViewById(R.id.imageCorazon);
            //Creamos los textos e imagenes que van a cargarse
            final TextView tvLikes = holder.itemView.findViewById(R.id.tvLike);
            final TextView tvTitulo = holder.itemView.findViewById(R.id.tvTitulo);
            ivPortada = holder.itemView.findViewById(R.id.ivPortada);

            likes = 0;

            for (int i = 0; i<PSenales.likes.size(); i++){
                if(PSenales.likes.get(i).cod_org == listaSenales.get(position).cod_org &&
                        PSenales.likes.get(i).cod_cop == listaSenales.get(position).cod_cop &&
                        PSenales.likes.get(i).cod_esp == listaSenales.get(position).cod_esp &&
                        PSenales.likes.get(i).cod_senal == listaSenales.get(position).cod_senal){
                    likes ++;
                    cantidadLikes=likes;
                }
            }
            //Cargamos los datos
            tvTitulo.setText(listaSenales.get(position).desc_senal);
            tvLikes.setText(String.valueOf(likes));
                /**ivPortada.setImageDrawable(listaSenales.get(position).img_senal);**/
            Picasso.get()
                    .load(listaSenales.get(position).img_senal)
                    .into(ivPortada);
            System.out.println("Foto señal -->>>>"+listaSenales.get(position).img_senal);
            ivPortada.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse(String.valueOf(listaSenales.get(holder.getAdapterPosition()).enlace)));
                    holder.itemView.getContext().startActivity( browse );
                }
            });
            btnEliminar.setVisibility(View.INVISIBLE);
            btnEditar.setVisibility(View.INVISIBLE);


            int cod_senal = Integer.parseInt(listaSenales.get(position).cod_senal);
            int cod_cop = Integer.parseInt(listaSenales.get(position).cod_cop);
            int cod_esp = Integer.parseInt(listaSenales.get(position).cod_esp);
            int cod_org = Integer.parseInt(listaSenales.get(position).cod_org);
            String cod_usuarioSenal = listaSenales.get(position).cod_usuario;


            if (Login.permisos.size()==0){
               if (Login.usuario.get(0).tip_usuario.equals("1")){
                   btnEliminar.setVisibility(View.VISIBLE);
                   btnEditar.setVisibility(View.VISIBLE);
               }
            }
            else{
                for (int i=0;i<Login.permisos.size();i++){

                    System.out.println("TIPO DE USAURIO "+Login.usuario.get(0).tip_usuario);
                    if (Login.usuario.get(0).tip_usuario.equals("1") || Login.usuario.get(0).cod_usuario.equals(cod_usuarioSenal) || Login.permisos.get(i).cod_cop.equals(Integer.toString(cod_cop)) && Login.permisos.get(i).ind_admin.equals("1") ){
                        btnEliminar.setVisibility(View.VISIBLE);
                        btnEditar.setVisibility(View.VISIBLE);
                    }

                }
            }


            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Aqui se borra la señal

                    final int cod_senal= Integer.parseInt(listaSenales.get(position).cod_senal);
                    final int cod_cop = Integer.parseInt(listaSenales.get(position).cod_cop);
                    final int cod_esp = Integer.parseInt(listaSenales.get(position).cod_esp);
                    final int cod_org = Integer.parseInt(listaSenales.get(position).cod_org);

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


                    int cod_senal = Integer.parseInt(listaSenales.get(position).cod_senal);
                    int cod_cop = Integer.parseInt(listaSenales.get(position).cod_cop);
                    int cod_esp = Integer.parseInt(listaSenales.get(position).cod_esp);
                    int cod_org = Integer.parseInt(listaSenales.get(position).cod_org);
                    String url = listaSenales.get(position).enlace;
                    String desc = listaSenales.get(position).desc_senal;




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

                    likes = 0;
                    for (int i = 0; i<PSenales.likes.size(); i++){
                        if(PSenales.likes.get(i).cod_org == listaSenales.get(position).cod_org &&
                                PSenales.likes.get(i).cod_cop == listaSenales.get(position).cod_cop &&
                                PSenales.likes.get(i).cod_esp == listaSenales.get(position).cod_esp &&
                                PSenales.likes.get(i).cod_senal == listaSenales.get(position).cod_senal){
                            likes ++;
                            cantidadLikes=likes;
                        }
                    }

                    //Aqui se gestionan los likes de la señal
                    final int cod_senal = Integer.parseInt(listaSenales.get(position).cod_senal);
                    final int cod_cop = Integer.parseInt(listaSenales.get(position).cod_cop);
                    final int cod_esp = Integer.parseInt(listaSenales.get(position).cod_esp);
                    final int cod_org = Integer.parseInt(listaSenales.get(position).cod_org);
                    final String cod_usuario = Login.usuario.get(0).cod_usuario;

                    System.out.println("DATOS PASADOOOOS "+cod_senal+" "+cod_cop+" "+cod_esp+" "+cod_org+" "+cod_usuario);
                    System.out.println("TAMAÑO ARRAYLIST "+PSenales.likes.size());


                    if (PSenales.likes.size()==0){
                        liked=false;
                    }

                    else {
                        //Se conprueba si el usuario actual ha dado tiene like
                        for (int i = 0; i< PSenales.likes.size(); i++){
                            System.out.println("El arraylist "+PSenales.likes.get(i).cod_usuario +" "+PSenales.likes.get(i).cod_senal );
                            if (PSenales.likes.get(i).cod_usuario.equals(cod_usuario) && PSenales.likes.get(i).cod_senal.equals(Integer.toString(cod_senal))){
                                //System.out.println("true");
                                liked=true;
                                break;
                            }
                            else {
                                liked=false;
                                //System.out.println("false");
                            }
                        }
                    }



                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {

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
                                for (int i = 0; i< PSenales.likes.size(); i++){
                                    if (PSenales.likes.get(i).cod_usuario.equals(cod_usuario) && PSenales.likes.get(i).cod_senal.equals(Integer.toString(cod_senal))){
                                        PSenales.likes.remove(i);

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
                    }, 500);






                }
            });
            
        }

    @Override
        public int getItemCount() {
            return listaSenales.size();
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


