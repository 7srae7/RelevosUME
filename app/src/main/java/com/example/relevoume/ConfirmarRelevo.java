package com.example.relevoume;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.relevoume.beans.Autobomba;
import com.example.relevoume.beans.Relevo;
import com.example.relevoume.beans.Vehiculo;
import com.example.relevoume.relevo.RelevoContrac;
import com.example.relevoume.relevo.RelevoPresenter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ConfirmarRelevo extends AppCompatActivity implements RelevoContrac.ViewConfirmarRelevo {

    private ImageButton btnAtras;
    private TextView txtMatricula;
    private TextInputEditText nombreImput;
    private TextInputEditText empleoInput;
    private TextInputEditText kilometrosInput;
    private EditText ediNovedades;
    private Button btnConfirmar;
    private RelevoPresenter relevoPresenter;
    private TextInputLayout textNombre;
    private TextInputLayout textEmpleo;
    private TextInputLayout textkilometros;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_relevo);

        inicializar();

        /**
         * Recibe los datos en el Bundle del RelevoActivity
         *
         */
        Bundle bundle = getIntent().getExtras();
        String matricula = bundle.getString("matricula");
        int id = bundle.getInt("id");
        String url = bundle.getString("foto");
        txtMatricula.setText(matricula);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Estos parámetros son enviados al pulsar el boton para podrer mostrar los datos
                // correctamente en el RelevoActivity
                context = v.getContext();
                Intent i = new Intent(context, RelevoActivity.class);
                i.putExtra("matricula", matricula);
                i.putExtra("foto", url);
                i.putExtra("id", id);
                context.startActivities(new Intent[]{i});

            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Confirma el relevo, utiliza el id y url recibidos en el bundle para confirmar el
                 * relevo
                 */
                Relevo relevo = new Relevo();
                relevo.setNombre(nombreImput.getText().toString());
                relevo.setEmpleo(empleoInput.getText().toString());
                relevo.setNovedades(ediNovedades.getText().toString());
                relevo.setFechaRelevo(fechaActual());
                Vehiculo vehiculo = new Vehiculo();
                Autobomba autobomba = new Autobomba();
                /**
                 * En este if hace la distinción entre un vehículo normal y una autobomba
                 * mediante la url
                 */
                if (url.equals("Amarok")) {
                    vehiculo.setIdVehiculo(id);
                    relevo.setVehiculo(vehiculo);
                } else if (url.equals("Autobomba")) {
                    autobomba.setIdAutobomba(id);
                    relevo.setAutobomba(autobomba);
                }
                if (relevo.getEmpleo().equals("") || relevo.getNombre().equals("") || kilometrosInput.getText().equals("")) {
                    validate(v);
                } else {
                    relevo.setKilometros(Integer.parseInt(kilometrosInput.getText().toString()));
                    relevoPresenter.confirmarRelevo(relevo);
                }


            }
        });
    }

    /**
     * Inicializa todos los elementos
     */
    private void inicializar() {
        btnAtras = findViewById(R.id.imageButtonAtrasConf);
        txtMatricula = findViewById(R.id.txtViewMatriculaRelevoConfi);
        nombreImput = findViewById(R.id.nombreInput);
        empleoInput = findViewById(R.id.empleoInput);
        kilometrosInput = findViewById(R.id.kilometrosInput);
        ediNovedades = findViewById(R.id.ediNovedades);
        btnConfirmar = findViewById(R.id.btnRelevar);
        textNombre = findViewById(R.id.text_nombre);
        textEmpleo = findViewById(R.id.text_empleo);
        textkilometros = findViewById(R.id.text_kilometros);
        relevoPresenter = new RelevoPresenter(this);
    }

    /**
     * Notificación cuando el relevo se realiza correctamente
     *
     * @param relevo
     */
    @Override
    public void relevoCorrecto(Relevo relevo) {
        Toast.makeText(this, "Relevo realizado correctamente", Toast.LENGTH_LONG).show();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }

    /**
     * Devuelve la fecha actual
     *
     * @return
     */
    public String fechaActual() {
        Calendar fecha = new GregorianCalendar();
        int anio = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);

        String date = dia + "/" + mes + "/" + anio;
        return date;
    }

    /**
     * Valida los campos
     *
     * @param view
     */
    public void validate(View view) {
        String nombre = null;
        if (TextUtils.isEmpty(nombreImput.getText())) {
            nombre = getString(R.string.obligatorio);
        }
        toggleTextInputLayoutError(textNombre, nombre);

        String empleo = null;
        if (TextUtils.isEmpty(empleoInput.getText())) {
            empleo = getString(R.string.obligatorio);
        }
        toggleTextInputLayoutError(textEmpleo, empleo);

        String kilometros = null;
        if (TextUtils.isEmpty(kilometrosInput.getText())) {
            kilometros = getString(R.string.obligatorio);
        }
        toggleTextInputLayoutError(textkilometros, kilometros);
        clearFocus();
    }

    /**
     * Borra el mensaje de error cuando el usuario a introducido los parámetros
     *
     * @param textInputLayout
     * @param msg
     */
    private static void toggleTextInputLayoutError(@NonNull TextInputLayout textInputLayout,
                                                   String msg) {
        textInputLayout.setError(msg);
        if (msg == null) {
            textInputLayout.setErrorEnabled(false);
        } else {
            textInputLayout.setErrorEnabled(true);
        }
    }

    private void clearFocus() {
        View view = this.getCurrentFocus();
        if (view != null && view instanceof EditText) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context
                    .INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            view.clearFocus();
        }
    }

}