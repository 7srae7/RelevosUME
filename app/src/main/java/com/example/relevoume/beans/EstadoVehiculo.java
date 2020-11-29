package com.example.relevoume.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EstadoVehiculo {
    private int idEstadoVehiculo;
    private String estado;

    public EstadoVehiculo(int idEstadoVehiculo, String estado) {
        this.idEstadoVehiculo = idEstadoVehiculo;
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public EstadoVehiculo() {
    }

    public int getIdEstadoVehiculo() {
        return idEstadoVehiculo;
    }

    public void setIdEstadoVehiculo(int idEstadoVehiculo) {
        this.idEstadoVehiculo = idEstadoVehiculo;
    }



    public static ArrayList<EstadoVehiculo> getArrayListFromJSon(JSONArray datos) {
        ArrayList<EstadoVehiculo> lista = null;
        EstadoVehiculo estadoVehiculo = null;
        try {
            if (datos != null && datos.length() > 0) {
                lista = new ArrayList<EstadoVehiculo>();
            }
            for (int i = 0; i < datos.length(); i++) {
                JSONObject json_data = datos.getJSONObject(i);
                estadoVehiculo = new EstadoVehiculo();
                estadoVehiculo.setIdEstadoVehiculo(json_data.getInt("id_estado_vehiculo"));
                estadoVehiculo.setEstado(json_data.getString("checklist"));
                lista.add(estadoVehiculo);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }
}