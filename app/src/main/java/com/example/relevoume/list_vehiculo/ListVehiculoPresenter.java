package com.example.relevoume.list_vehiculo;

import com.example.relevoume.beans.Vehiculo;

import java.util.ArrayList;

public class ListVehiculoPresenter implements ListVehiculoContract.Presenter {

    private ListVehiculoContract.View vista;
    private ListVehiculoContract.Model model;
    private ListVehiculoContract.Presenter presenter;

    public ListVehiculoPresenter(){
        this.model = new ListVehiculoModel();
    }

    public ListVehiculoPresenter(ListVehiculoContract.View  v){
        this.vista = v;
        this.model = new ListVehiculoModel();
    }

    @Override
    public void getMatriculas() {
        this.model.getVehiculosMatricula(new ListVehiculoContract.Model.OnListVehiculoListener() {
            @Override
            public void onFinished(ArrayList<Vehiculo> listVehiculo) {
                vista.listarVehiculos(listVehiculo);
            }

            @Override
            public void onFailure(String error) {

            }
        });
    }

    @Override
    public void getBuscarVehiculo(Vehiculo vehiculo) {
        this.model.getBuscarVehiculoMatricula(new ListVehiculoContract.Model.OnVehiculoMatriculaListener() {
            @Override
            public void onFinished(ArrayList<Vehiculo> listVehiculo) {
                vista.buscarVehiculos(listVehiculo);
            }
        }, vehiculo);
    }
}
