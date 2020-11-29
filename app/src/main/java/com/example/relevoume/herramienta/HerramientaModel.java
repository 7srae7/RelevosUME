package com.example.relevoume.herramienta;

import com.example.relevoume.beans.Herramienta;
import com.example.relevoume.beans.Vehiculo;
import com.example.relevoume.ws.ApiClient;
import com.example.relevoume.ws.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HerramientaModel implements HerramientaContract.Model {

    private ArrayList<Herramienta> listHerramienta;

    /**
     * Lista las herramientas que tiene cada vehículo, pasandole como parámetro la matrícula de
     * dicho vehículo
     * @param onListHerramienta
     * @param vehiculo
     */
    @Override
    public void getListHerramienta(OnListHerramienta onListHerramienta, Vehiculo vehiculo) {
        ApiInterface apiService = ApiClient.getClient()
                .create(ApiInterface.class);

        Call<ArrayList<Herramienta>> call = apiService.getHerramienta(vehiculo);
        call.enqueue(new Callback<ArrayList<Herramienta>>() {
            @Override
            public void onResponse(Call<ArrayList<Herramienta>> call, Response<ArrayList<Herramienta>> response) {
                listHerramienta = response.body();
                if (listHerramienta != null && listHerramienta.size() > 0) {
                    onListHerramienta.onFinished(listHerramienta);
                } else {
                    onListHerramienta.onFailure("Error al listar las herramientas");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Herramienta>> call, Throwable t) {

            }
        });
    }
}
