package com.example.relevoume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Transition;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.relevoume.adapter.AdaperDocumentacion;
import com.example.relevoume.beans.Documentacion;
import com.example.relevoume.beans.Vehiculo;
import com.example.relevoume.documentacion.DocumentacionContract;
import com.example.relevoume.documentacion.DocumentacionPresenter;

import java.util.ArrayList;

public class DocumentacionActivity extends AppCompatActivity implements DocumentacionContract.View {

    private RecyclerView recyclerView;
    private ImageButton btn;
    private RecyclerView.Adapter adapter;
    private DocumentacionPresenter documentacionPresenter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentacion);

        inicializar();

        /**
         * Recibe los datos del RelevoActivity
         */
        Bundle bundle = getIntent().getExtras();
        String matricula = bundle.getString("matricula");
        String img = bundle.getString("foto");
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMatricula(matricula);

        documentacionPresenter = new DocumentacionPresenter(this);
        documentacionPresenter.getDocumentacion(vehiculo);

        /*Orientación del Recycler View*/
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
     * Agregar el adapter de documentación al recyclerView
     * @param listDocumentacion
     */
    @Override
    public void lisarDocumentacion(ArrayList<Documentacion> listDocumentacion) {
        adapter = new AdaperDocumentacion(listDocumentacion);
        recyclerView.setAdapter(adapter);
    }

    /**
     * Inicializa los elementos
     */
    public void inicializar() {
        recyclerView = findViewById(R.id.idRecycleDocu);
        btn = findViewById(R.id.imageButtonAtrasDocu);
    }
}