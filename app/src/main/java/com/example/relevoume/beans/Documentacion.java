package com.example.relevoume.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Documentacion {

    private int idDocumentacion;
    private String documento;

    public Documentacion(int idDocumentacion, String documento) {
        this.idDocumentacion = idDocumentacion;
        this.documento = documento;
    }

    public Documentacion (){

    }

    public int getIdDocumentacion() {
        return idDocumentacion;
    }

    public void setIdDocumentacion(int idDocumentacion) {
        this.idDocumentacion = idDocumentacion;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public static ArrayList<Documentacion> getArrayListFromJSon(JSONArray datos) {
        ArrayList<Documentacion> lista = null;
        Documentacion documentacion = null;
        try {
            if (datos != null && datos.length() > 0) {
                lista = new ArrayList<Documentacion>();
            }
            for (int i = 0; i < datos.length(); i++) {
                JSONObject json_data = datos.getJSONObject(i);
                documentacion = new Documentacion();
                documentacion.setIdDocumentacion(json_data.getInt("id_documentacion"));
                documentacion.setDocumento(json_data.getString("documento1"));
                lista.add(documentacion);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;
    }

}
