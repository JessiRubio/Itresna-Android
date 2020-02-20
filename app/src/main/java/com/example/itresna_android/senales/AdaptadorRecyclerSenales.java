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

import com.example.itresna_android.senales.PSenales;

import java.util.List;

import com.example.itresna_android.R;
import com.example.itresna_android.cops.AdaptadorRecyclerPCops;


public class AdaptadorRecyclerSenales extends RecyclerView.Adapter<AdaptadorRecyclerSenales.ViewHolder> {
        // Colocamos el xml del elemento selector
        public AdaptadorRecyclerSenales(){

        }
        @NonNull
        @Override
        public AdaptadorRecyclerSenales.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elementoselecto_senales, parent, false);
            return new AdaptadorRecyclerSenales.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
            Button comentario = holder.itemView.findViewById(R.id.botonComentarios);
            comentario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.itemView.getContext(), popUpcomentarios.class);
                    holder.itemView.getContext().startActivity(intent);
                }
            });
            Button eliminar = holder.itemView.findViewById(R.id.botonBasura);
            Button editar = holder.itemView.findViewById(R.id.botonLapiz);
            ImageView corazon = holder.itemView.findViewById(R.id.imageCorazon);
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


