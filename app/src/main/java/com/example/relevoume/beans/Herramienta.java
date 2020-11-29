package com.example.relevoume.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Herramienta {

    private int idHerramienta;
    private String nombreHerramienta;


    public Herramienta() {

    }
    public Herramienta(int idHerramienta, String nombreHerramienta) {
        this.idHerramienta = idHerramienta;
        this.nombreHerramienta = nombreHerramienta;
    }

    public String getNombreHerramienta() {
        return nombreHerramienta;
    }

    public void setNombreHerramienta(String nombreHerramienta) {
        this.nombreHerramienta = nombreHerramienta;
    }

    public int getIdHerramienta() {
        return idHerramienta;
    }

    public void setIdHerramienta(int idHerramienta) {
        this.idHerramienta = idHerramienta;
    }


    public static ArrayList<Herramienta> getArrayListFromJSon(JSONArray datos) {
        ArrayList<Herramienta> lista = null;
        Herramienta herramienta = null;
        try {
            if (datos != null && datos.length() > 0) {
                lista = new ArrayList<Herramienta>();
            }
            for (int i = 0; i < datos.length(); i++) {
                JSONObject json_data = datos.getJSONObject(i);
                herramienta = new Herramienta();
                herramienta.setIdHerramienta(json_data.getInt("id_herramienta"));
                herramienta.setNombreHerramienta(json_data.getString("nombre_herramienta"));
                lista.add(herramienta);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }
}