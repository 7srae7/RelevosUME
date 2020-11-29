package com.example.relevoume.estado_vehiculo;

import com.example.relevoume.beans.EstadoVehiculo;
import com.example.relevoume.beans.Vehiculo;
import com.example.relevoume.ws.ApiClient;
import com.example.relevoume.ws.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EstadoVehiculoModel implements EstadoVehiculoContract.Model {

    private ArrayList<EstadoVehiculo> listEstadoVehiculo;

    /**
     * Lista todos los elementos que hay que revisar de un vehículo.
     * @param onListEstadoVehiculo
     */

    @Override
    public void getListEstadoVehiculo(OnListEstadoVehiculo onListEstadoVehiculo) {
        ApiInterface apiService = ApiClient.getClient()
                .create(ApiInterface.class);
        Call<ArrayList<EstadoVehiculo>> call = apiService.getEstadoVehiculo();
        call.enqueue(new Callback<ArrayList<EstadoVehiculo>>() {
            @Override
            public void onResponse(Call<ArrayList<EstadoVehiculo>> call, Response<ArrayList<EstadoVehiculo>> response) {
                listEstadoVehiculo = response.body();
                if (listEstadoVehiculo != null && listEstadoVehiculo.size() > 0) {
                    onListEstadoVehiculo.onFinished(listEstadoVehiculo);
                }else {
                    onListEstadoVehiculo.onFailure("Error al listar");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<EstadoVehiculo>> call, Throwable t) {

            }
        });
    }
}
