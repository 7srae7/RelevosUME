package com.example.relevoume.estado_vehiculo;

import com.example.relevoume.beans.EstadoVehiculo;
import com.example.relevoume.beans.Vehiculo;

import java.util.ArrayList;

public class EstadoVehiculoContract {
    public interface View {
        void listarEstadoVehiculo(ArrayList<EstadoVehiculo> listEstadoVehiculo);
    }

    public interface Presenter {
        void getEstadoVehiculo();
    }

    public interface Model {
        interface OnListEstadoVehiculo {
            void onFinished(ArrayList<EstadoVehiculo> listEstaoVehiculo);

            void onFailure(String error);
        }

        void getListEstadoVehiculo(OnListEstadoVehiculo onListEstadoVehiculo);
    }
}
