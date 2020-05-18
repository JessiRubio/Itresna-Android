package com.example.itresna_android.senales;

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

import com.example.itresna_android.R;
import com.example.itresna_android.Senal;

import java.util.List;



    public class AdaptadorRecyclerSenales extends RecyclerView.Adapter<com.example.itresna_android.senales.AdaptadorRecyclerSenales.ViewHolder> {
        private LayoutInflater inflador;
        protected List<Senal> lista;
        private Context contexto;

        private View.OnClickListener onClickListener;

        public AdaptadorRecyclerSenales(Context c, List<Senal> l){
            inflador = (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
            this.lista = l;
            this.contexto = c;
        }

        // Esto es necesario
        public static class ViewHolder extends RecyclerView.ViewHolder {
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

        @NonNull
        @Override
        public com.example.itresna_android.senales.AdaptadorRecyclerSenales.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elementoselecto_senales, parent, false);
            v.setOnClickListener(onClickListener);
            return new com.example.itresna_android.senales.AdaptadorRecyclerSenales.ViewHolder(v);
        }

        // Aqui ponemos los elementos que se muestran en pantalla
        @Override
        public void onBindViewHolder(final com.example.itresna_android.senales.AdaptadorRecyclerSenales.ViewHolder holder, final int position) {
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
            return lista.size();
        }

        public void setOnItemClickListener (View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }
    }


