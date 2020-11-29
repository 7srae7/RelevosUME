package com.example.relevoume.documentacion;

import com.example.relevoume.DocumentacionActivity;
import com.example.relevoume.beans.Documentacion;
import com.example.relevoume.beans.Vehiculo;
import com.example.relevoume.ws.ApiClient;
import com.example.relevoume.ws.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentacionModel implements DocumentacionContract.Model {

    private ArrayList<Documentacion> listDocumentacion;

    /**
     * Lista la documentación de un vehícula pasandole como parámetro la matrícula de dicho vehículo
     * @param onListDocumentacion
     * @param vehiculo
     */
    @Override
    public void getListDocumentacion(OnListDocumentacion onListDocumentacion, Vehiculo vehiculo) {
        ApiInterface apiService = ApiClient.getClient()
                .create(ApiInterface.class);

        Call<ArrayList<Documentacion>> call = apiService.getDocumentacion(vehiculo);
        call.enqueue(new Callback<ArrayList<Documentacion>>() {
            @Override
            public void onResponse(Call<ArrayList<Documentacion>> call, Response<ArrayList<Documentacion>> response) {
                listDocumentacion = response.body();
                if(listDocumentacion != null && listDocumentacion.size() > 0){
                    onListDocumentacion.onFinished(listDocumentacion);
                }else {
                    onListDocumentacion.onFailure("Error al listar la documentación");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Documentacion>> call, Throwable t) {

            }
        });

    }

    /**
     * Lista todos los documentos
     * @param onListTodaDocumentacion
     */
    @Override
    public void getListTodaDocumentacion(OnListTodaDocumentacion onListTodaDocumentacion) {
        ApiInterface apiService = ApiClient.getClient()
                .create(ApiInterface.class);

        Call<ArrayList<Documentacion>> call = apiService.getTodaDocumentacion();
        call.enqueue(new Callback<ArrayList<Documentacion>>() {
            @Override
            public void onResponse(Call<ArrayList<Documentacion>> call, Response<ArrayList<Documentacion>> response) {
                listDocumentacion = response.body();
                if(listDocumentacion != null && listDocumentacion.size() > 0){
                    onListTodaDocumentacion.onFinished(listDocumentacion);
                }else {

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Documentacion>> call, Throwable t) {

            }
        });
    }
}
