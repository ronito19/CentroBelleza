package com.example.centrodebellezagala;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.centrodebellezagala.clases.Citas;
import com.example.centrodebellezagala.controladores.CitaFirebaseController;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.core.view.View;

import java.util.List;

public class Main5CogerCita extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private TextView txt_tratamientos;
    private Spinner sp_tratamientos;
    private TextView txt_fecha;
    private EditText edt_fecha;
    private TextView txt_hora;
    private EditText edt_hora;
    private FirebaseAuth mAuth;

    Citas cit;



    // Recoger variables a nivel de clase

    private String tratamientos;
    private String fecha;
    private String hora;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5_coger_cita);
        txt_tratamientos = (TextView) findViewById(R.id.txt_tratamientos);
        sp_tratamientos = (Spinner) findViewById(R.id.sp_tratamientos);
        txt_fecha = (TextView) findViewById(R.id.txt_fecha);
        edt_fecha = (EditText) findViewById(R.id.edt_fecha);
        txt_hora = (TextView) findViewById(R.id.txt_hora);
        edt_hora = (EditText) findViewById(R.id.edt_hora);
        mAuth = FirebaseAuth.getInstance();


        if(sp_tratamientos != null)
        {
            String[] tratamientos = {"<Selecciona un tipo de sesion>", " PELUQUERIA ", " MANICURA ", " PEDICURA ", " MAQUILLAJE ",
                                    " LIMPIEZA FACIAL ", " TRATAMIENTOS GENERALES "};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.estilospinner, tratamientos);
            sp_tratamientos.setAdapter(adapter);
            sp_tratamientos.setOnItemSelectedListener(this);
        }
    }




    @Override
    public void onItemSelected(AdapterView<?> adapterView, android.view.View view, int i, long l) {
        tratamientos = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView)
    {

    }



    public void escoger_fecha(android.view.View view)
    {
        DatePickerFragment calendario1 = new DatePickerFragment();
        calendario1.show(getSupportFragmentManager(), "DatePicker");
    }


    public void escoger_hora(android.view.View view)
    {
        TimePickerFragment reloj1 = new TimePickerFragment();
        reloj1.show(getSupportFragmentManager(), "TimePicker");
    }


    public void seleccionar_Fecha(int anyo, int mes, int dia)
    {
        String texto_anyo = String.valueOf(anyo);
        String texto_mes = String.valueOf(mes);
        String texto_dia = String.valueOf(dia);
        fecha = texto_dia + "/" + texto_mes + "/" + texto_anyo;
        edt_fecha.setText(fecha);
    }

    public void seleccionar_Hora(int horas, int minutos)
    {
        String texto_hora = "";
        String texto_minutos = "";
        if(horas < 10)
        {
            texto_hora = "0" + String.valueOf(horas);
        }
        else
        {
            texto_hora = String.valueOf(horas);
        }
        if(minutos < 10)
        {
            texto_minutos = "0" + String.valueOf(minutos);
        }
        else
        {
            texto_minutos = String.valueOf(minutos);
        }

        hora = texto_hora + ":" + texto_minutos;
        edt_hora.setText(hora);
    }

    public void guardar_Cita(android.view.View view) {


        tratamientos = sp_tratamientos.toString();
        fecha = String.valueOf(edt_fecha.getText());
        hora = String.valueOf(edt_hora.getText());

        cit = new Citas(tratamientos, fecha, hora);

        new CitaFirebaseController().guardar_Cita(new CitaFirebaseController.CitaStatus()
        {
            @Override
            public void citaIsLoaded(List<Citas> citas, List<String> keys)
            {

            }

            @Override
            public void citaIsAdd()
            {
                // aquí hay que poner cuando se haya insertado bien qué hacer
                Toast.makeText(Main5CogerCita.this," Cita guardada correctamente ",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Main5CogerCita.this, Main4Menu.class);
                startActivity(intent);

            }

            @Override
            public void citaIsUpdate()
            {

            }

            @Override
            public void citaIsDelete()
            {

            }

        }, cit);
    }
}
