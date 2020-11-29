package com.example.relevoume.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Autobomba {

    private int idAutobomba;
    private String matricula;
    private int kilometros;
    private String url;
    private Documentacion documentacion;
    private Herramienta herramienta;


    public Autobomba() {
    }

    public Autobomba(int idAutobomba, String matricula, int kilometros, String url, Documentacion documentacion, Herramienta herramienta) {
        this.idAutobomba = idAutobomba;
        this.matricula = matricula;
        this.kilometros = kilometros;
        this.url = url;
        this.documentacion = documentacion;
        this.herramienta = herramienta;
    }

    public int getIdAutobomba() {
        return idAutobomba;
    }

    public void setIdAutobomba(int idAutobomba) {
        this.idAutobomba = idAutobomba;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public static ArrayList<Autobomba> getArrayListFromJSon(JSONArray datos) {
        ArrayList<Autobomba> lista = null;
        Autobomba autobomba = null;
        try{
            if(datos != null && datos.length()>0){
                lista = new ArrayList<>();
            }
            for(int i = 0;i<datos.length();i++){
                JSONObject json_data = datos.getJSONObject(i);
                autobomba = new Autobomba();
                autobomba.setIdAutobomba(json_data.getInt("id_autobomba"));
                autobomba.setMatricula(json_data.getString("matricula"));
                autobomba.setKilometros(json_data.getInt("kilometros"));
                autobomba.setUrl(json_data.getString("url"));
                Documentacion documentacion = new Documentacion();
                documentacion.setIdDocumentacion(json_data.getInt("id_documentacion"));
                autobomba.setDocumentacion(documentacion);
                Herramienta herramienta = new Herramienta();
                herramienta.setIdHerramienta(json_data.getInt("id_herramienta"));
                autobomba.setHerramienta(herramienta);
                lista.add(autobomba);

            }
        }catch (JSONException e){
            e.printStackTrace();
        }

        return lista;
    }
}
