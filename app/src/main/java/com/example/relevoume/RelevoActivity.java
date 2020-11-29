package com.example.relevoume;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.text.LineBreaker;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.relevoume.beans.Documentacion;
import com.example.relevoume.beans.Relevo;
import com.example.relevoume.beans.Vehiculo;
import com.example.relevoume.relevo.RelevoContrac;
import com.example.relevoume.relevo.RelevoPresenter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import android.text.method.ScrollingMovementMethod;

import static maes.tech.intentanim.CustomIntent.customType;


public class RelevoActivity extends AppCompatActivity implements RelevoContrac.View {

    private TextView txtViewMatricula;
    private ImageView imgViewVehiculo;
    private ImageButton imageButtonFlecha;
    private RelevoPresenter relevoPresenter;
    private TextView txtViewNombre;
    private TextView txtViewNovedades;
    private Button btnRelevar;
    private Button btnDocumentacion;
    private Button btnHerramienta;
    private Button btnEstadoVehiculo;
    private Context context;
    private TextView txtViewFecha;
    private Transition transition;
    public static  final long DURATION_TRANSITION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relevo);
        /**
         * Animación para la transición del RelevoActivity al MainActivity
         */
        Fade fadIn = new Fade(Fade.IN);
        fadIn.setDuration(RelevoActivity.DURATION_TRANSITION);
        fadIn.setInterpolator(new DecelerateInterpolator());
        getWindow().setEnterTransition(fadIn);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        relevoPresenter = new RelevoPresenter(this);

        inicializar();

        /**
         * Recibe los datos del MainActivity
         */
        Bundle bundle = getIntent().getExtras();
        String matricula = bundle.getString("matricula");
        txtViewMatricula.setText(matricula);
        String img = bundle.getString("foto");
        String urlImagen = "http://192.168.0.10:8080/RelevoVehiculos/images/" +
                img + ".png";
        Picasso.get().load(urlImagen).into(imgViewVehiculo);
        int id = bundle.getInt("id");

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMatricula(matricula);
        vehiculo.setUrl(img);
        relevoPresenter.getUltimoRelevoVehiculo(vehiculo);


        imageButtonFlecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transition = new Slide(Gravity.START);
                Intent i = new Intent(RelevoActivity.this, MainActivity.class);
                iniciarActividadSecundaria(i);

            }
        });

        btnRelevar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = v.getContext();
                Intent i = new Intent(context, ConfirmarRelevo.class);
                i.putExtra("matricula", matricula);
                i.putExtra("foto", img);
                i.putExtra("id", id);
                context.startActivities(new Intent[]{i});
            }
        });

        btnDocumentacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = v.getContext();
                Intent i = new Intent(context, DocumentacionActivity.class);
                i.putExtra("matricula", matricula);
                i.putExtra("foto", img);
                context.startActivities(new Intent[]{i});
            }
        });

        btnHerramienta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context = v.getContext();
                Intent i = new Intent(context, HerramientaActivity.class);
                i.putExtra("matricula", matricula);
                i.putExtra("foto", img);
                context.startActivities(new Intent[]{i});
            }
        });

        btnEstadoVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                context = v.getContext();
                Intent i = new Intent(context, EstadoVehiculoActivity.class);
                i.putExtra("matricula", matricula);
                i.putExtra("foto", img);
                context.startActivities(new Intent[]{i});
            }
        });
    }

    /**
     * Método que realiza una animación entre Activitys
     * @param i
     */
    private void iniciarActividadSecundaria(Intent i){
        getWindow().setAllowReturnTransitionOverlap(false);
        transition.setDuration(DURATION_TRANSITION);
        transition.setInterpolator(new DecelerateInterpolator());
        getWindow().setExitTransition(transition);
        startActivity(i, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle());
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void listarRelevos(ArrayList<Relevo> listRelevo) {
        for (Relevo ultimoRelevo : listRelevo) {
            txtViewNombre.setText("Nombre: " + ultimoRelevo.getNombre());
            txtViewNovedades.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
            txtViewNovedades.setMovementMethod(new ScrollingMovementMethod());
            txtViewNovedades.setText("NOVEDADES:\n " + ultimoRelevo.getNovedades());
            txtViewFecha.setText("Fecha: " + ultimoRelevo.getFechaRelevo());
        }
    }

    /**
     * Inicializa los elementos
     */
    public void inicializar() {

        txtViewMatricula = findViewById(R.id.txtViewMatriculaRelevo);
        imgViewVehiculo = findViewById(R.id.imgRelevo);
        imageButtonFlecha = findViewById(R.id.imageButtonFlechaRelevo);
        txtViewNombre = findViewById(R.id.txtViwNombreRelevo);
        txtViewNovedades = findViewById(R.id.txtViewNovedades);
        btnRelevar = findViewById(R.id.btnRelevar);
        btnDocumentacion = findViewById(R.id.btnDocuRelevo);
        btnHerramienta = findViewById(R.id.btnHerraRele);
        btnEstadoVehiculo = findViewById(R.id.btnEstadoRelevo);
        txtViewFecha = findViewById(R.id.txtViwFechaRelevo);
    }
}