package com.example.relevoume.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Vehiculo {
    private int idVehiculo;
    private String matricula;
    private int kilometros;
    private Documentacion documentacion;
    private Herramienta herramienta;
    private String url;

    public Vehiculo() {
    }

    public Vehiculo(int idVehiculo, String matricula, int kilometros, Documentacion documentacion, Herramienta herramienta, String url) {
        this.idVehiculo = idVehiculo;
        this.matricula = matricula;
        this.kilometros = kilometros;
        this.documentacion = documentacion;
        this.herramienta = herramienta;
        this.url = url;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getKilometros() {
        return kilometros;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }


    public Documentacion getDocumentacion() {
        return documentacion;
    }

    public void setDocumentacion(Documentacion documentacion) {
        this.documentacion = documentacion;
    }

    public Herramienta getHerramienta() {
        return herramienta;
    }

    public void setHerramienta(Herramienta herramienta) {
        this.herramienta = herramienta;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static ArrayList<Vehiculo> getArrayListFromJSon(JSONArray datos) {
        ArrayList<Vehiculo> lista = null;
        Vehiculo vehiculo = null;
        try {
            if (datos != null && datos.length() > 0) {
                lista = new ArrayList<Vehiculo>();
            }
            for (int i = 0; i < datos.length(); i++) {
                JSONObject json_data = datos.getJSONObject(i);
                vehiculo = new Vehiculo();
                vehiculo.setIdVehiculo(json_data.getInt("id_vehiculo"));
                vehiculo.setMatricula(json_data.getString("matricula"));
                vehiculo.setKilometros(json_data.getInt("kilometros"));
                Documentacion documentacion = new Documentacion();
                documentacion.setIdDocumentacion(json_data.getInt("id_documentacion"));
                vehiculo.setDocumentacion(documentacion);
                Herramienta herramienta = new Herramienta();
                herramienta.setIdHerramienta(json_data.getInt("id_herramienta"));
                vehiculo.setHerramienta(herramienta);
                vehiculo.setUrl(json_data.getString("url"));
                lista.add(vehiculo);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }
}