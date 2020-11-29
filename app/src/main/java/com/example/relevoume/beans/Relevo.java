package com.example.relevoume.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class Relevo {


    private int idRelevo;
    private String novedades;
    private String fechaRelevo;
    private Vehiculo vehiculo;
    private Autobomba autobomba;
    private int kilometros;
    private String nombre;
    private String empleo;

    public Relevo() {
    }

    public Relevo(int idRelevo, String novedades, String fechaRelevo, Vehiculo vehiculo, String nombre, String empleo, Autobomba autobomba, int kilometros) {
        this.idRelevo = idRelevo;
        this.novedades = novedades;
        this.fechaRelevo = fechaRelevo;
        this.vehiculo = vehiculo;
        this.nombre = nombre;
        this.empleo = empleo;
        this.autobomba = autobomba;
        this.kilometros = kilometros;
    }

    public int getKilometros() {
        return kilometros;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }

    public int getIdRelevo() {
        return idRelevo;
    }

    public void setIdRelevo(int idRelevo) {
        this.idRelevo = idRelevo;
    }

    public String getNovedades() {
        return novedades;
    }

    public void setNovedades(String novedades) {
        this.novedades = novedades;
    }

    public String getFechaRelevo() {
        return fechaRelevo;
    }

    public void setFechaRelevo(String fechaRelevo) {
        this.fechaRelevo = fechaRelevo;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmpleo() {
        return empleo;
    }

    public void setEmpleo(String empleo) {
        this.empleo = empleo;
    }

    public Autobomba getAutobomba() {
        return autobomba;
    }

    public void setAutobomba(Autobomba autobomba) {
        this.autobomba = autobomba;
    }

    public static ArrayList<Relevo> getArrayListFromJSon(JSONArray datos) {
        ArrayList<Relevo> lista = null;
        Relevo relevo = null;
        try {
            if (datos != null && datos.length() > 0) {
                lista = new ArrayList<Relevo>();
            }
            for (int i = 0; i < datos.length(); i++) {
                JSONObject json_data = datos.getJSONObject(i);
                relevo = new Relevo();
                relevo.setIdRelevo(json_data.getInt("id_relevo"));
                relevo.setNovedades(json_data.getString("novedades"));
                relevo.setNombre(json_data.getString("nombre"));
                relevo.setEmpleo(json_data.getString("empleo"));
                relevo.setFechaRelevo(json_data.getString("fecha_relevo"));
                relevo.setKilometros(json_data.getInt("kilometros"));
                Vehiculo idVehiculo = new Vehiculo();
                idVehiculo.setIdVehiculo(json_data.getInt("id_vehiculo"));
                relevo.setVehiculo(idVehiculo);

                Autobomba idAutobomba = new Autobomba();
                idAutobomba.setIdAutobomba(json_data.getInt("id_veh_pesado"));
                relevo.setAutobomba(idAutobomba);

                lista.add(relevo);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lista;

    }
}
