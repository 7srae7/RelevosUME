package com.example.relevoume.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.relevoume.R;
import com.example.relevoume.beans.Documentacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdaperDocumentacion extends RecyclerView.Adapter<AdaperDocumentacion.DocumentacionViewHolder> {

    /**
     * Adapter que muestra el listado de la documentación.
     **/

    private ArrayList<Documentacion> listDocumentacion;

    public static class DocumentacionViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private TextView textView;

        public DocumentacionViewHolder(View view) {
            super(view);
            checkBox = view.findViewById(R.id.checkDocumentacion);
            textView = view.findViewById(R.id.textViewDocu);
        }
    }

    public AdaperDocumentacion(ArrayList<Documentacion> listDocumentacion) {
        this.listDocumentacion = listDocumentacion;
    }

    @NonNull
    @Override
    public DocumentacionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fila_checkbox, parent, false);
        return new DocumentacionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentacionViewHolder holder, int position) {
        holder.textView.setText(listDocumentacion.get(position).getDocumento());
    }

    @Override
    public int getItemCount() {
        return listDocumentacion.size();
    }
}
