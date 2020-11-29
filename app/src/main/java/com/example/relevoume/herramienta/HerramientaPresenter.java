package com.example.relevoume.herramienta;

import com.example.relevoume.beans.Herramienta;
import com.example.relevoume.beans.Vehiculo;

import java.util.ArrayList;

public class HerramientaPresenter implements HerramientaContract.Presenter {

    private HerramientaContract.View view;
    private HerramientaContract.Model model;

    public HerramientaPresenter(HerramientaContract.View view) {
        this.view = view;
        this.model = new HerramientaModel();
    }

    @Override
    public void getHerramineta(Vehiculo vehiculo) {
        this.model.getListHerramienta(new HerramientaContract.Model.OnListHerramienta() {
            @Override
            public void onFinished(ArrayList<Herramienta> listHerramienta) {
                view.listHerramienta(listHerramienta);
            }

            @Override
            public void onFailure(String error) {

            }
        }, vehiculo);
    }
}
