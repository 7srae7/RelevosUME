package com.example.relevoume.relevo;

import com.example.relevoume.beans.Relevo;
import com.example.relevoume.beans.Vehiculo;

import java.util.ArrayList;

public interface RelevoContrac {
    public interface View {
        void listarRelevos(ArrayList<Relevo> listRelevo);
    }

    public interface ViewConfirmarRelevo {
        void relevoCorrecto(Relevo relevo);
    }

    public interface Presenter {
        void getUltimoRelevoVehiculo(Vehiculo vehiculo);

        void confirmarRelevo(Relevo relevo);

        void getTodosRelevos();

    }

    public interface Model {

        interface UltimoRelevoVehiculoListener {
            void onFinished(ArrayList<Relevo> listRelevo);

            void onFailure(String mensaje);
        }

        interface OnConfirmarRelevo {
            void onFinished(Relevo relevo);

            void onFailure(String error);
        }

        interface OnTodosRelevos{
            void onFinished(ArrayList<Relevo>  listRelevo);
        }

        void  getTodosRelevos(OnTodosRelevos onTodosRelevos);

        void getUlitimoRelevoVehiculoService(UltimoRelevoVehiculoListener ultimoRelevoVehiculoListener, Vehiculo vehiculo);

        void getConfirmarRelevo(OnConfirmarRelevo onConfirmarRelevo, Relevo relevo);
    }
}
