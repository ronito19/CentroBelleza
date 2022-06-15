package com.example.centrodebellezagala;

import static com.example.centrodebellezagala.clases.CitasViewHolder.EXTRA_OBJETO_CITA;
import static com.example.centrodebellezagala.clases.CitasViewHolder.EXTRA_OBJETO_CITA_KEY;
import static com.example.centrodebellezagala.clases.CitasViewHolder.EXTRA_OBJETO_IMG_CITA;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.centrodebellezagala.clases.CitaDatosCompletos;
import com.example.centrodebellezagala.controladores.CitaFirebaseController;
import com.example.centrodebellezagala.utilidades.ImagenesFirebase;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;


/**
 * Clase donde podemos editar, borrar y actualizar las citas de los clientes
 * @autor: Ronnie Mascaro Troncoso
 */



public class Main7MostrarDetallesCitas extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private EditText edt_detalles_correo, edt_detalles_nombre, edt_detalles_apellidos, edt_detalles_fecha;
    private Spinner sp_detalles_tratamiento, sp_detalles_hora;
    private ImageView img_detalles_foto_cita;
    private String key;
    private CitaDatosCompletos cit;

    private String tratamientos, fecha, hora;



    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7_mostrar_detalles_citas);

        edt_detalles_correo = (EditText) findViewById(R.id.edt_detalles_correo);
        edt_detalles_nombre = (EditText) findViewById(R.id.edt_detalles_nombre);
        edt_detalles_apellidos = (EditText) findViewById(R.id.edt_detalles_apellidos);
        sp_detalles_tratamiento = (Spinner) findViewById(R.id.sp_detalles_tratamiento);
        edt_detalles_fecha = (EditText) findViewById(R.id.edt_detalles_fecha);
        sp_detalles_hora = (Spinner) findViewById(R.id.sp_detalles_hora);
        img_detalles_foto_cita = (ImageView) findViewById(R.id.img_detalles_foto_cita);

        Intent intent = getIntent();

        if(intent != null)
        {
            cit = (CitaDatosCompletos) intent.getSerializableExtra(EXTRA_OBJETO_CITA);
            key = intent.getStringExtra(EXTRA_OBJETO_CITA_KEY);
            edt_detalles_correo.setText(cit.getCorreoCliente());
            edt_detalles_nombre.setText(cit.getNombre());
            edt_detalles_apellidos.setText(cit.getApellidos());
            String sp_detalles_tratamientos = cit.getTratamientos();

            edt_detalles_fecha.setText(cit.getFecha());
        }

        if(sp_detalles_tratamiento != null)
        {
            String[] tratamiento = {"< Selecciona un tipo de sesion >", " PELUQUERIA ", " COLORIMETRIA DE CABELLO ", " MANICURA ", " PEDICURA ",
                                    " MAQUILLAJE ", " LIMPIEZA FACIAL ", " APLICACION DE PESTAÑAS ", " TRATAMIENTOS CORPORALES "};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.estilospinner, tratamiento);
            sp_detalles_tratamiento.setAdapter(adapter);
            sp_detalles_tratamiento.setOnItemSelectedListener(this);
        }

        if(sp_detalles_hora != null)
        {
            String[] horas = {"< Selecciona una hora >", " 09:00 hrs - 10:00 hrs ", " 10:00 hrs - 11:00 hrs ", " 11:00 hrs - 12:00 hrs ", " 12:00 hrs - 13:00 hrs ",
                    " 13:00 hrs - 14:00 hrs ", " 17:00 hrs - 18:00 hrs ", " 18:00 hrs - 19:00 hrs ", " 19:00 hrs - 20:00 hrs ", " 20:00 hrs - 21:00 hrs "};
            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.estilospinner1, horas);
            sp_detalles_hora.setAdapter(adapter1);
            sp_detalles_hora.setOnItemSelectedListener(this);
        }

    }


    public void actualizar_Fecha(int anyo, int mes, int dia)
    {
        String texto_anyo = String.valueOf(anyo);
        String texto_mes = String.valueOf(mes + 1);
        String texto_dia = String.valueOf(dia);
        fecha = texto_dia + "/" + texto_mes + "/" + texto_anyo;
        edt_detalles_fecha.setText(fecha);
    }


    public void escoger_fecha(View view)
    {
        DatePicker2Fragment calendario1 = new DatePicker2Fragment();
        calendario1.show(getSupportFragmentManager(), "DatePicker");
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

        new ImagenesFirebase().borrarFoto(new ImagenesFirebase.FotoStatus()
        {
            @Override
            public void FotoIsDownload(byte[] bytes) {
            }
            @Override
            public void FotoIsDelete() {
            }
            @Override
            public void FotoIsUpload() {
                Toast.makeText(Main7MostrarDetallesCitas.this, " foto eliminada correcto ", Toast.LENGTH_LONG).show();
            }
        },cit.getFoto());
    }
    //---------------------------------------------------------------------------------------------------------


    public void actualizar_cita(View view)
    {
        String correoCliente = String.valueOf(edt_detalles_correo.getText());
        String nombre = String.valueOf(edt_detalles_nombre.getText());
        String apellidos = String.valueOf(edt_detalles_apellidos.getText());
        String fecha = String.valueOf(edt_detalles_fecha.getText());

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


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        Spinner spinner = (Spinner) adapterView;
        if(spinner.getId() == R.id.sp_detalles_tratamiento)
        {
            tratamientos = adapterView.getItemAtPosition(i).toString();
        }
        else if(spinner.getId() == R.id.sp_detalles_hora)
        {
            hora = adapterView.getItemAtPosition(i).toString();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }


    public void atras(View view)
    {
        Intent intent = new Intent(this, Main6MostrasCitas.class);
        startActivity(intent);
    }


    public void salir(View view)
    {
        Intent intent = new Intent(this, Main1Logueo.class);
        startActivity(intent);
        FirebaseAuth.getInstance().signOut();
    }



}
