package com.example.relevoume.estado_vehiculo;

import com.example.relevoume.beans.EstadoVehiculo;
import com.example.relevoume.beans.Vehiculo;

import java.util.ArrayList;

public class EstadoVehiculoPresenter implements EstadoVehiculoContract.Presenter {

    private EstadoVehiculoContract.View view;
    private EstadoVehiculoContract.Model model;

    public EstadoVehiculoPresenter(EstadoVehiculoContract.View view) {
        this.view = view;
        this.model = new EstadoVehiculoModel();
    }

    @Override
    public void getEstadoVehiculo() {
        this.model.getListEstadoVehiculo(new EstadoVehiculoContract.Model.OnListEstadoVehiculo() {
            @Override
            public void onFinished(ArrayList<EstadoVehiculo> listEstadoVehiculo) {
                view.listarEstadoVehiculo(listEstadoVehiculo);
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }
}
