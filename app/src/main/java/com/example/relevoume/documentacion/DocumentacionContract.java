package com.example.relevoume.documentacion;

import com.example.relevoume.beans.Documentacion;
import com.example.relevoume.beans.Vehiculo;

import java.util.ArrayList;

public interface DocumentacionContract {
    public interface View {
        void lisarDocumentacion(ArrayList<Documentacion> listDocumentacion);
    }

    public interface Presenter {
        void getDocumentacion(Vehiculo vehiculo);

        void getTodaDocumentacion();
    }


    public interface Model {

        interface OnListDocumentacion {
            void onFinished(ArrayList<Documentacion> listDocumentacion);

            void onFailure(String error);
        }

        interface OnListTodaDocumentacion {
            void onFinished(ArrayList<Documentacion> listDocumentacion);
        }

        void getListTodaDocumentacion(OnListTodaDocumentacion onListTodaDocumentacion);

        void getListDocumentacion(OnListDocumentacion onListDocumentacion, Vehiculo vehiculo);
    }
}
