package com.example.relevoume.documentacion;

import com.example.relevoume.beans.Documentacion;
import com.example.relevoume.beans.Vehiculo;

import java.util.ArrayList;

public class DocumentacionPresenter implements DocumentacionContract.Presenter {

    private DocumentacionContract.View view;
    private DocumentacionContract.Model model;

    public DocumentacionPresenter(DocumentacionContract.View view) {
        this.view = view;
        this.model = new DocumentacionModel();
    }


    @Override
    public void getDocumentacion(Vehiculo vehiculo) {
        this.model.getListDocumentacion(new DocumentacionContract.Model.OnListDocumentacion() {
            @Override
            public void onFinished(ArrayList<Documentacion> listDocumentacion) {
                view.lisarDocumentacion(listDocumentacion);
            }

            @Override
            public void onFailure(String error) {

            }
        }, vehiculo);
    }

    @Override
    public void getTodaDocumentacion() {
        this.model.getListTodaDocumentacion(new DocumentacionContract.Model.OnListTodaDocumentacion() {
            @Override
            public void onFinished(ArrayList<Documentacion> listDocumentacion) {
                view.lisarDocumentacion(listDocumentacion);
            }
        });
    }
}
