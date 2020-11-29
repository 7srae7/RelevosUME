package com.example.relevoume.list_vehiculo;

import com.example.relevoume.beans.Vehiculo;
import com.example.relevoume.ws.ApiClient;
import com.example.relevoume.ws.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListVehiculoModel implements ListVehiculoContract.Model {

    private ArrayList<Vehiculo> listVehiculo;

    /**
     * Lista todas las matrículas y fotografía de los vehículos
     * @param onListVehiculoListener
     */
    @Override
    public void getVehiculosMatricula(final OnListVehiculoListener onListVehiculoListener) {
        ApiInterface apiService = ApiClient.getClient()
                .create(ApiInterface.class);

       Call<ArrayList<Vehiculo>> call = apiService.getListMatricula();
       call.enqueue(new Callback<ArrayList<Vehiculo>>() {
           @Override
           public void onResponse(Call<ArrayList<Vehiculo>> call, Response<ArrayList<Vehiculo>> response) {
               listVehiculo = response.body();
               if(listVehiculo != null && listVehiculo.size() > 0){
                   onListVehiculoListener.onFinished(listVehiculo);
               }
           }

           @Override
           public void onFailure(Call<ArrayList<Vehiculo>> call, Throwable t) {

           }
       });
    }

    /**
     * Busca por matrícula un vehículo
     * @param onVehiculoMatriculaListener
     * @param vehiculo
     */
    @Override
    public void getBuscarVehiculoMatricula(OnVehiculoMatriculaListener onVehiculoMatriculaListener, Vehiculo vehiculo) {
        ApiInterface apiService = ApiClient.getClient()
                .create(ApiInterface.class);

        Call<ArrayList<Vehiculo>> call = apiService.getBuscarMatricula(vehiculo);
        call.enqueue(new Callback<ArrayList<Vehiculo>>() {
            @Override
            public void onResponse(Call<ArrayList<Vehiculo>> call, Response<ArrayList<Vehiculo>> response) {
                listVehiculo = response.body();
                if(listVehiculo != null && listVehiculo.size() > 0){
                    onVehiculoMatriculaListener.onFinished(listVehiculo);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Vehiculo>> call, Throwable t) {

            }
        });
    }
}
