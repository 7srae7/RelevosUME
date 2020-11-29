package com.example.relevoume.relevo;

import com.example.relevoume.ConfirmarRelevo;
import com.example.relevoume.beans.Relevo;
import com.example.relevoume.beans.Vehiculo;

import java.util.ArrayList;

public class RelevoPresenter implements RelevoContrac.Presenter {

    private RelevoContrac.View view;
    private RelevoContrac.Model model;
    private RelevoContrac.ViewConfirmarRelevo v;

    public RelevoPresenter(RelevoContrac.View v){
        this.view = v;
        this.model = new RelevoModel();
    }

    public RelevoPresenter(RelevoContrac.ViewConfirmarRelevo v){
        this.v= v;
        this.model = new RelevoModel();
    }


    @Override
    public void getUltimoRelevoVehiculo(Vehiculo vehiculo) {
        model.getUlitimoRelevoVehiculoService(new RelevoContrac.Model.UltimoRelevoVehiculoListener() {
            @Override
            public void onFinished(ArrayList<Relevo> listRelevo) {
                view.listarRelevos(listRelevo);
            }

            @Override
            public void onFailure(String mensaje) {

            }
        }, vehiculo);
    }

    @Override
    public void confirmarRelevo(Relevo relevo) {
        model.getConfirmarRelevo(new RelevoContrac.Model.OnConfirmarRelevo() {
            @Override
            public void onFinished(Relevo relevo) {
                v.relevoCorrecto(relevo);
            }

            @Override
            public void onFailure(String error) {

            }
        }, relevo);
    }

    @Override
    public void getTodosRelevos() {
        model.getTodosRelevos(new RelevoContrac.Model.OnTodosRelevos() {
            @Override
            public void onFinished(ArrayList<Relevo> listRelevo) {
                view.listarRelevos(listRelevo);
            }
        });
    }
}
