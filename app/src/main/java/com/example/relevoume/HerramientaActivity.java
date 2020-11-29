package com.example.relevoume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.example.relevoume.adapter.AdaperHerramienta;
import com.example.relevoume.beans.Herramienta;
import com.example.relevoume.beans.Vehiculo;
import com.example.relevoume.herramienta.HerramientaContract;
import com.example.relevoume.herramienta.HerramientaPresenter;

import java.util.ArrayList;

public class HerramientaActivity extends AppCompatActivity implements HerramientaContract.View {

    private RecyclerView recyclerView;
    private ImageButton btn;
    private RecyclerView.Adapter adapter;
    private HerramientaPresenter herramientaPresenter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herramienta);
        inicializar();

        /**
         * Recibe los datos del RelevoActivity
         */
        Bundle bundle = getIntent().getExtras();
        String matricula = bundle.getString("matricula");
        String img = bundle.getString("foto");
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMatricula(matricula);

        herramientaPresenter = new HerramientaPresenter(this);
        herramientaPresenter.getHerramineta(vehiculo);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

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
                context.startActivities(new Intent[]{i});
            }
        });
    }

    /**
     * Agregar el AdapterHerramienta al recyclerView
     * @param listHerramienta
     */
    @Override
    public void listHerramienta(ArrayList<Herramienta> listHerramienta) {
        adapter = new AdaperHerramienta(listHerramienta);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Inicializa los elementos
     */
    public void inicializar() {
        recyclerView = findViewById(R.id.idRecycleHerraa);
        btn = findViewById(R.id.imageButtonAtrasHerra);
    }
}