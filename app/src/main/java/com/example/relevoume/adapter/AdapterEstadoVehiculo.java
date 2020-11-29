package com.example.relevoume.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.relevoume.R;
import com.example.relevoume.beans.EstadoVehiculo;

import java.util.ArrayList;

public class AdapterEstadoVehiculo extends RecyclerView.Adapter<AdapterEstadoVehiculo.EstadoVehiculoViewHolder> {

    /**
     * Adapter que muestra el listado de la diferentes partes que hay que revisar a la hora de
     * hacer el relevo
     * **/

    private ArrayList<EstadoVehiculo> listEstadoVehiculo;

    public static class EstadoVehiculoViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private TextView textView;

        public EstadoVehiculoViewHolder(View view){
            super(view);
            checkBox = view.findViewById(R.id.checkDocumentacion);
            textView = view.findViewById(R.id.textViewDocu);
        }
    }

    public AdapterEstadoVehiculo(ArrayList<EstadoVehiculo> listEstadoVehiculo){
        this.listEstadoVehiculo = listEstadoVehiculo;
    }

    @NonNull
    @Override
    public EstadoVehiculoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fila_checkbox, parent, false);
        return new EstadoVehiculoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EstadoVehiculoViewHolder holder, int position) {
        holder.textView.setText(listEstadoVehiculo.get(position).getEstado());
    }

    @Override
    public int getItemCount() {
        return listEstadoVehiculo.size();
    }
}
