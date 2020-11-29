package com.example.relevoume.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.relevoume.R;
import com.example.relevoume.beans.Vehiculo;
import com.example.relevoume.list_vehiculo.ListVehiculoPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class  AdapterVehiculo extends
        RecyclerView.Adapter<AdapterVehiculo.VehiculosViewHolder> implements View.OnClickListener {

    /**
     * Adpater que muestra los vehículos con foto y matrícula.
     */

    private ArrayList<Vehiculo> listVehiculo;
    private View.OnClickListener listener;

    public void  setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        this.listener.onClick(v);
    }


    public static class VehiculosViewHolder extends RecyclerView.ViewHolder{

        private ImageView imagen;
        private TextView matricula;


        public VehiculosViewHolder(View v) {
            super(v);
            imagen = v.findViewById(R.id.cardImg);
            matricula = v.findViewById(R.id.cardMatricula);
        }

    }

    public AdapterVehiculo(ArrayList<Vehiculo> listVehiculo){
        this.listVehiculo = listVehiculo;
    }


    @Override
    public VehiculosViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fila_matriculas, parent, false);

        v.setOnClickListener(this);

         return new VehiculosViewHolder(v);
    }

    @Override
    public void onBindViewHolder( VehiculosViewHolder holder, int position) {
        String urlImagen = "http://192.168.0.10:8080/RelevosUME/images/" +
                listVehiculo.get(position).getUrl() + ".png";
        Picasso.get().load(urlImagen).into(holder.imagen);
        holder.matricula.setText(listVehiculo.get(position).getMatricula());

    }

    @Override
    public int getItemCount() {
        return listVehiculo.size();
    }
}
