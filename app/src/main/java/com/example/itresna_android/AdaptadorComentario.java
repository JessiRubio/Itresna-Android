package com.example.itresna_android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itresna_android.R;

public class AdaptadorComentario extends RecyclerView.Adapter<AdaptadorComentario.ViewHolder>{

    public AdaptadorComentario() {
        // Aqui tendria que ir la lista
    }

    @NonNull
    @Override
    public AdaptadorComentario.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elementoselecto_comentarios, parent, false);
        return new AdaptadorComentario.ViewHolder(v);
    }

    // Aqui ponemos los elementos que se muestran en pantalla
    @Override
    public void onBindViewHolder(final AdaptadorComentario.ViewHolder holder, final int position) {
        TextView Nombre = holder.itemView.findViewById(R.id.textAutor);
        TextView Comentario = holder.itemView.findViewById(R.id.textoComentario);
    }

    //Aqui tenemos que poner el arraylist size
    @Override
    public int getItemCount() {
        return 8;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}