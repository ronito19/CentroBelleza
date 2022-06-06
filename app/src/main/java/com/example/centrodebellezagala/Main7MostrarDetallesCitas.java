package com.example.centrodebellezagala;

import static com.example.centrodebellezagala.clases.CitasViewHolder.EXTRA_OBJETO_CITA;
import static com.example.centrodebellezagala.clases.CitasViewHolder.EXTRA_OBJETO_CITA_KEY;
import static com.example.centrodebellezagala.clases.CitasViewHolder.EXTRA_OBJETO_IMG_CITA;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.centrodebellezagala.clases.CitaDatosCompletos;
import com.example.centrodebellezagala.controladores.CitaFirebaseController;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class Main7MostrarDetallesCitas extends AppCompatActivity
{
    private EditText edt_detalles_correo;
    private EditText edt_detalles_nombre;
    private EditText edt_detalles_apellidos;
    private EditText edt_detalles_tratamiento;
    private EditText edt_detalles_fecha;
    private EditText edt_detalles_hora;
    private ImageView img_detalles_foto_cita;
    private String key;
    private CitaDatosCompletos cit;



    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7_mostrar_detalles_citas);
        edt_detalles_correo = (EditText) findViewById(R.id.edt_detalles_correo);
        edt_detalles_nombre = (EditText) findViewById(R.id.edt_detalles_nombre);
        edt_detalles_apellidos = (EditText) findViewById(R.id.edt_detalles_apellidos);
        edt_detalles_tratamiento = (EditText) findViewById(R.id.edt_detalles_tratamiento);
        edt_detalles_fecha = (EditText) findViewById(R.id.edt_detalles_fecha);
        edt_detalles_hora = (EditText) findViewById(R.id.edtr_detalles_hora);
        img_detalles_foto_cita = (ImageView) findViewById(R.id.img_detalles_foto_cita);

        Intent intent = getIntent();

        if(intent != null)
        {
            cit = (CitaDatosCompletos) intent.getSerializableExtra(EXTRA_OBJETO_CITA);
            key = intent.getStringExtra(EXTRA_OBJETO_CITA_KEY);
            edt_detalles_correo.setText(cit.getCorreoCliente());
            edt_detalles_nombre.setText(cit.getNombre());
            edt_detalles_apellidos.setText(cit.getApellidos());
            edt_detalles_tratamiento.setText(cit.getTratamientos());
            edt_detalles_fecha.setText(cit.getFecha());
            edt_detalles_hora.setText(cit.getHora());
        }
    }


    public  void borrar_cita(View view)
    {
        new CitaFirebaseController().borrarCita(new CitaFirebaseController.CitaStatus()
        {
            @Override
            public void citaIsLoaded(List<CitaDatosCompletos> citas, List<String> keys)
            {

            }

            @Override
            public void citaIsAdd()
            {

            }

            @Override
            public void citaIsUpdate()
            {

            }

            @Override
            public void citaIsDelete()
            {
                Toast.makeText(Main7MostrarDetallesCitas.this, " Cita borrada correctamente ", Toast.LENGTH_SHORT).show();
                finish();
            }
        }, key);
    }
    //---------------------------------------------------------------------------------------------------------

    public void actualizar_cita(View view)
    {
        String correoCliente = String.valueOf(edt_detalles_correo.getText());
        String nombre = String.valueOf(edt_detalles_nombre.getText());
        String apellidos = String.valueOf(edt_detalles_apellidos.getText());
        String tratamientos = String.valueOf(edt_detalles_tratamiento.getText());
        String fecha = String.valueOf(edt_detalles_fecha.getText());
        String hora = String.valueOf(edt_detalles_hora.getText());
        CitaDatosCompletos cit = new CitaDatosCompletos(correoCliente, nombre, apellidos, tratamientos, fecha, hora);

        new CitaFirebaseController().actualizarCita(new CitaFirebaseController.CitaStatus()
        {
            @Override
            public void citaIsLoaded(List<CitaDatosCompletos> citas, List<String> keys)
            {

            }

            @Override
            public void citaIsAdd()
            {

            }

            @Override
            public void citaIsUpdate()
            {
                Toast.makeText(Main7MostrarDetallesCitas.this, " La cita se ha actualizado correctamente ", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void citaIsDelete()
            {

            }
        },key,cit);
    }
    //--------------------------------------------------------------------------------------------


    public void salir(View view)
    {
        Intent intent = new Intent(this, Main1Logueo.class);
        startActivity(intent);
        FirebaseAuth.getInstance().signOut();
    }


    }
