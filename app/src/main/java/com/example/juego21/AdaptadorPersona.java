package com.example.juego21;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorPersona extends RecyclerView.Adapter<AdaptadorPersona.viewHolderPersona> {

    private ArrayList<Persona> LP;

    public AdaptadorPersona(ArrayList<Persona> LP) {
        this.LP = LP;
    }

    @NonNull
    @Override
    public viewHolderPersona onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_persona,null,false);
        return  new viewHolderPersona(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderPersona holder, int position) {
        holder.nombre.setText(LP.get(position).getNombre());
        holder.numero.setText(String.valueOf(LP.get(position).getPuntuacion()));
        holder.img.setImageResource(R.drawable.indice2);
    }

    @Override
    public int getItemCount() {
        return LP.size();
    }

    public class viewHolderPersona extends RecyclerView.ViewHolder {
        TextView nombre,numero;
        ImageView img;
        public viewHolderPersona(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            numero = itemView.findViewById(R.id.numero);
            img = itemView.findViewById(R.id.img);
        }
    }
}

