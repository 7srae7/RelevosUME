package com.example.relevoume.herramienta;

import com.example.relevoume.beans.Herramienta;
import com.example.relevoume.beans.Vehiculo;

import java.util.ArrayList;

public interface HerramientaContract {
    public interface View {
        void listHerramienta(ArrayList<Herramienta> listHerramienta);
    }

    public interface Presenter {
        void getHerramineta(Vehiculo vehiculo);
    }

    public interface Model {
        interface OnListHerramienta{
            void onFinished(ArrayList<Herramienta> listHerramienta);
            void onFailure(String error);
        }
        void getListHerramienta(OnListHerramienta onListHerramienta, Vehiculo vehiculo);
    }
}
