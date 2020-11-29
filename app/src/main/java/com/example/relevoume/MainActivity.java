package com.example.relevoume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import androidx.appcompat.widget.SearchView;

import com.example.relevoume.adapter.AdaperDocumentacion;
import com.example.relevoume.adapter.AdapterRelevo;
import com.example.relevoume.adapter.AdapterVehiculo;

import com.example.relevoume.beans.Documentacion;
import com.example.relevoume.beans.Relevo;
import com.example.relevoume.beans.Vehiculo;
import com.example.relevoume.documentacion.DocumentacionContract;
import com.example.relevoume.documentacion.DocumentacionPresenter;
import com.example.relevoume.list_vehiculo.ListVehiculoContract;
import com.example.relevoume.list_vehiculo.ListVehiculoPresenter;
import com.example.relevoume.relevo.RelevoContrac;
import com.example.relevoume.relevo.RelevoPresenter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ListVehiculoContract.View,
        DocumentacionContract.View, RelevoContrac.View {


    public RecyclerView recyclerView;
    public SearchView searchView;
    public Button btnDocumentacion;
    public Button btnVehiculo;
    public Button btnRelevo;
    private RecyclerView.Adapter adapter;
    private Context context;
    private ListVehiculoPresenter listVehiculoPresenter;
    private DocumentacionPresenter documentacionPresenter;
    private RelevoPresenter relevoPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Animación para la transición entre el RelevoActivity y MainActivity
         */
        Fade fadIn = new Fade(Fade.IN);
        fadIn.setDuration(RelevoActivity.DURATION_TRANSITION);
        fadIn.setInterpolator(new DecelerateInterpolator());
        getWindow().setEnterTransition(fadIn);
        getWindow().setAllowReturnTransitionOverlap(false);

        inicializar();

        // La pantalla del teléfono no cambia de tamaño cuando aparece el teclado
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        listVehiculoPresenter = new ListVehiculoPresenter(this);
        listVehiculoPresenter.getMatriculas();
        documentacionPresenter = new DocumentacionPresenter(this);
        relevoPresenter = new RelevoPresenter(this);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        /**
         * Buscador de vehículos por matrículas
         */
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String matricula) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String matricula) {
                if (matricula.length() > 0) {
                    Vehiculo vehiculo = new Vehiculo();
                    vehiculo.setMatricula(matricula);

                    listVehiculoPresenter.getBuscarVehiculo(vehiculo);

                } else {
                    listVehiculoPresenter.getMatriculas();
                }
                return true;
            }
        });

        /**
         * Lista todos los vehículos
         */
        btnVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listVehiculoPresenter.getMatriculas();
            }
        });

        /**
         * Lista toda la documentación de todos los vehículos
         */
        btnDocumentacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentacionPresenter.getTodaDocumentacion();
            }
        });

        /**
         * Lista todos los relevos realizados
         */
        btnRelevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relevoPresenter.getTodosRelevos();
            }
        });

    }

    /**
     * Inicializa los elementos
     */
    private void inicializar() {
        recyclerView = findViewById(R.id.recyclerVehiuclo);
        searchView = findViewById(R.id.buscadorId);
        btnDocumentacion = findViewById(R.id.btnDocumentacion);
        btnVehiculo = findViewById(R.id.btnVehiculo);
        btnRelevo = findViewById(R.id.btnRelevo);

    }

    /**
     * Agrega el AdapterVehiculo al recyclerView
     * @param listVehiculo
     */
    @Override
    public void listarVehiculos(ArrayList<Vehiculo> listVehiculo) {
        adapter = new AdapterVehiculo(listVehiculo);
        recyclerView.setAdapter(adapter);
        accionTextView(listVehiculo);

    }

    /**
     * Al pulsar una matrícula del recyclerView enviar los parámetros al RelevoActivity
     * @param listVehiculo
     */
    public void accionTextView(final ArrayList<Vehiculo> listVehiculo) {
        adapter = new AdapterVehiculo(listVehiculo);
        ((AdapterVehiculo) adapter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = v.getContext();
                Intent i = new Intent(context, RelevoActivity.class);
                i.putExtra("matricula", listVehiculo.get(recyclerView.getChildAdapterPosition(v)).getMatricula());
                i.putExtra("foto", listVehiculo.get(recyclerView.getChildAdapterPosition(v)).getUrl());
                i.putExtra("id", listVehiculo.get(recyclerView.getChildAdapterPosition(v)).getIdVehiculo());
                context.startActivities(new Intent[]{i});
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void falloListarVehiculo(String mensaje) {

    }

    /**
     * Lista los vehículo buscados y los agregar al adapter y este a su vez al recycler
     * Con el métod accionTextView() permíte cambiar de activity
     * @param listVehiculo
     */
    @Override
    public void buscarVehiculos(ArrayList<Vehiculo> listVehiculo) {
        if (listVehiculo.size() > 0 && listVehiculo != null) {
            adapter = new AdapterVehiculo(listVehiculo);
            recyclerView.setAdapter(adapter);
            accionTextView(listVehiculo);
        }


    }

    /**
     * Agrega el AdapterDocumentación al recyclerView
     * @param listDocumentacion
     */
    @Override
    public void lisarDocumentacion(ArrayList<Documentacion> listDocumentacion) {
        adapter = new AdaperDocumentacion(listDocumentacion);
        recyclerView.setAdapter(adapter);

    }

    /**
     * Agregar el AdapterRelevo al recyclerView
     * @param listRelevo
     */
    @Override
    public void listarRelevos(ArrayList<Relevo> listRelevo) {
        adapter = new AdapterRelevo(listRelevo);
        recyclerView.setAdapter(adapter);
    }
}