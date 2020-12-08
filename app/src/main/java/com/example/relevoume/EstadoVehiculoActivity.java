package com.example.relevoume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;

import com.example.relevoume.adapter.AdapterEstadoVehiculo;
import com.example.relevoume.beans.EstadoVehiculo;
import com.example.relevoume.beans.Vehiculo;
import com.example.relevoume.estado_vehiculo.EstadoVehiculoContract;
import com.example.relevoume.estado_vehiculo.EstadoVehiculoPresenter;

import java.util.ArrayList;

public class EstadoVehiculoActivity extends AppCompatActivity implements EstadoVehiculoContract.View {

    private RecyclerView recyclerView;
    private ImageButton btn;
    private Context context;
    private EstadoVehiculoPresenter estadoVehiculoPresenter;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_vehiculo);

        inicializar();

        /**
         * Recibe los datos del RelevoActivity
         */
        Bundle bundle = getIntent().getExtras();
        String matricula = bundle.getString("matricula");
        String img = bundle.getString("foto");
        int id = bundle.getInt("id");
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMatricula(matricula);

        /*Orientación del Recycler View*/
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        estadoVehiculoPresenter = new EstadoVehiculoPresenter(this);
        estadoVehiculoPresenter.getEstadoVehiculo();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Enviar la mtrícula y la foto para que se muestre correctamente en el RelevoActivity
                 */
                context = v.getContext();
                Intent i = new Intent(context, RelevoActivity.class);
                i.putExtra("matricula", matricula);
                i.putExtra("foto", img);
                i.putExtra("id", id);
                context.startActivities(new Intent[]{i});

            }
        });

    }

    /**
     * Agregar el AdapterEstadoVehiculo al recyclerView
     * @param listEstadoVehiculo
     */
    @Override
    public void listarEstadoVehiculo(ArrayList<EstadoVehiculo> listEstadoVehiculo) {
      adapter = new AdapterEstadoVehiculo(listEstadoVehiculo);
      recyclerView.setAdapter(adapter);
    }

    /**
     * Inicializa los elementos
     */
    private void inicializar(){
        recyclerView = findViewById(R.id.idRecycleEstado);
        btn = findViewById(R.id.imageButtonAtrasEstado);
    }
}