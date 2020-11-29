package com.example.relevoume.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.relevoume.R;
import com.example.relevoume.beans.Documentacion;
import com.example.relevoume.beans.Herramienta;

import java.util.ArrayList;

public class AdaperHerramienta extends RecyclerView.Adapter<AdaperHerramienta.HerramientaViewHolder> {

    /**
     * Adapter que muestra el listado de herramientas
     **/

    private ArrayList<Herramienta> listHerramienta;

    public static class HerramientaViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private TextView textView;

        public HerramientaViewHolder(View view) {
            super(view);
            checkBox = view.findViewById(R.id.checHerramienta);
            textView = view.findViewById(R.id.textViewHerramienta);
        }
    }

    public AdaperHerramienta(ArrayList<Herramienta> listHerramienta) {
        this.listHerramienta = listHerramienta;
    }

    @NonNull
    @Override
    public HerramientaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fila_checkbox_herramienta, parent, false);
        return new HerramientaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HerramientaViewHolder holder, int position) {
            holder.textView.setText(listHerramienta.get(position).getNombreHerramienta());

    }

    @Override
    public int getItemCount() {
        return listHerramienta.size();
    }
}
