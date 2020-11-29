package com.example.relevoume.relevo;

import com.example.relevoume.beans.Relevo;
import com.example.relevoume.beans.Vehiculo;
import com.example.relevoume.ws.ApiClient;
import com.example.relevoume.ws.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RelevoModel implements RelevoContrac.Model {

    private ArrayList<Relevo> listRelevo;

    /**
     * Lista el último relevo realizado del vehículo que se le pasa por parámetro
     * @param ultimoRelevoVehiculoListener
     * @param vehiculo
     */
    @Override
    public void getUlitimoRelevoVehiculoService(UltimoRelevoVehiculoListener ultimoRelevoVehiculoListener, Vehiculo vehiculo) {
        ApiInterface apiService = ApiClient.getClient()
                .create(ApiInterface.class);

        Call<ArrayList<Relevo>> call = apiService.getUltimoRelevo(vehiculo);
        call.enqueue(new Callback<ArrayList<Relevo>>() {
            @Override
            public void onResponse(Call<ArrayList<Relevo>> call, Response<ArrayList<Relevo>> response) {
                listRelevo = response.body();
                if (listRelevo != null && listRelevo.size() > 0) {
                    ultimoRelevoVehiculoListener.onFinished(listRelevo);
                } else {
                    ultimoRelevoVehiculoListener.onFailure("Error al listar el último relevo");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Relevo>> call, Throwable t) {

            }
        });
    }

    /**
     * Guarda el relevo en la base de datos
     * @param onConfirmarRelevo
     * @param relevo
     */
    @Override
    public void getConfirmarRelevo(OnConfirmarRelevo onConfirmarRelevo, Relevo relevo) {
        ApiInterface apiService = ApiClient.getClient()
                .create(ApiInterface.class);

        Call<Relevo> call = apiService.releverVehiculo(relevo);
        call.enqueue(new Callback<Relevo>() {
            @Override
            public void onResponse(Call<Relevo> call, Response<Relevo> response) {
                Relevo relevo1 = response.body();
                if (relevo != null) {
                    onConfirmarRelevo.onFinished(relevo1);
                } else {
                    onConfirmarRelevo.onFailure("Error al realizar el relevo");
                }
            }

            @Override
            public void onFailure(Call<Relevo> call, Throwable t) {

            }
        });

    }

    /**
     * Lista todos los relevos realizados
     * @param onTodosRelevos
     */
    @Override
    public void getTodosRelevos(OnTodosRelevos onTodosRelevos) {
        ApiInterface apiService = ApiClient.getClient()
                .create(ApiInterface.class);

        Call<ArrayList<Relevo>> call = apiService.getTodosRelevos();
        call.enqueue(new Callback<ArrayList<Relevo>>() {
            @Override
            public void onResponse(Call<ArrayList<Relevo>> call, Response<ArrayList<Relevo>> response) {
                listRelevo = response.body();
                if (listRelevo != null && listRelevo.size() > 0) {
                    onTodosRelevos.onFinished(listRelevo);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Relevo>> call, Throwable t) {

            }
        });

    }
}
