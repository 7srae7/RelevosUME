package com.example.relevoume.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.relevoume.R;
import com.example.relevoume.beans.Relevo;

import java.util.ArrayList;

public class AdapterRelevo extends RecyclerView.Adapter<AdapterRelevo.RelevoViewHolder> {

    /**
     * Adapter que muestra los últimos relevos realizados
     */

    private ArrayList<Relevo> listRelevo;

    public static class RelevoViewHolder extends RecyclerView.ViewHolder {
        private TextView textNombre;
        private TextView textEmpleo;
        private TextView textFecha;
        private TextView textNovedad;

        public RelevoViewHolder(View view) {
            super(view);
            textNombre = view.findViewById(R.id.cardNombre);
            textEmpleo = view.findViewById(R.id.cardEmpleo);
            textFecha = view.findViewById(R.id.carFecha);
            textNovedad = view.findViewById(R.id.cardNovedades);
        }

    }

    public AdapterRelevo(ArrayList<Relevo> listRelevo) {
        this.listRelevo = listRelevo;
    }

    @NonNull
    @Override
    public RelevoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_relevo, parent, false);
        return new RelevoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RelevoViewHolder holder, int position) {
        holder.textNombre.setText("Nombre: "+ listRelevo.get(position).getNombre());
        holder.textEmpleo.setText("Empleo: " +listRelevo.get(position).getEmpleo());
        holder.textFecha.setText("Fecha: "+listRelevo.get(position).getFechaRelevo());
        holder.textNovedad.setText("Novedades:\n  " +listRelevo.get(position).getNovedades());
    }

    @Override
    public int getItemCount() {
        return listRelevo.size();
    }
}
