package com.example.relevoume.ws;

import com.example.relevoume.DocumentacionActivity;
import com.example.relevoume.beans.Documentacion;
import com.example.relevoume.beans.EstadoVehiculo;
import com.example.relevoume.beans.Herramienta;
import com.example.relevoume.beans.Relevo;
import com.example.relevoume.beans.Vehiculo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiInterface {

    @GET("vehiculo")
    Call<ArrayList<Vehiculo>> getListMatricula();

    @POST("relevo/ultimoRelevoVehiculo")
    Call<ArrayList<Relevo>> getUltimoRelevo(
            @Body Vehiculo matricula
    );

    @POST("vehiculo/buscarMatricula")
    Call<ArrayList<Vehiculo>> getBuscarMatricula(
            @Body Vehiculo matricula
    );


    @PUT("relevo/relevar")
    Call<Relevo> releverVehiculo(
            @Body Relevo relevo
    );

    @POST("documentacion/buscaDocumentacion")
    Call<ArrayList<Documentacion>> getDocumentacion(
            @Body Vehiculo vehiculo
    );

    @POST("herramienta/buscaHerramienta")
    Call<ArrayList<Herramienta>> getHerramienta(
            @Body Vehiculo vehiculo
    );

    @GET("estadoVehiculo")
    Call<ArrayList<EstadoVehiculo>> getEstadoVehiculo();

    @GET("documentacion")
    Call<ArrayList<Documentacion>> getTodaDocumentacion();

    @GET("relevo/todosRelevo")
    Call<ArrayList<Relevo>> getTodosRelevos();
}
