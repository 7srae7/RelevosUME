package com.example.relevoume.list_vehiculo;

import com.example.relevoume.beans.Vehiculo;

import java.util.ArrayList;

public interface ListVehiculoContract {

    public interface View {
        void listarVehiculos(ArrayList<Vehiculo> listVehiculo);

        void falloListarVehiculo(String mensaje);

        void buscarVehiculos(ArrayList<Vehiculo> listVehiculo);
    }

    public interface Presenter {
        void getMatriculas();

        void getBuscarVehiculo(Vehiculo vehiculo);
    }

    public interface Model {
        interface OnListVehiculoListener {
            void onFinished(ArrayList<Vehiculo> listVehiculo);

            void onFailure(String error);
        }

        interface OnVehiculoMatriculaListener {
            void onFinished(ArrayList<Vehiculo> listVehiculo);
        }

        void getVehiculosMatricula(OnListVehiculoListener onListVehiculoListener);

        void getBuscarVehiculoMatricula(OnVehiculoMatriculaListener onVehiculoMatriculaListener, Vehiculo vehiculo);
    }


}
