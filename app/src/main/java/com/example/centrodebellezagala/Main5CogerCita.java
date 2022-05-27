package com.example.centrodebellezagala;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.centrodebellezagala.clases.Citas;
import com.example.centrodebellezagala.controladores.CitaFirebaseController;
import com.google.firebase.auth.FirebaseAuth;


import java.util.List;

public class Main5CogerCita extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private TextView txt_tratamientos;
    private Spinner sp_tratamientos;
    private TextView txt_fecha;
    private EditText edt_fecha;
    private TextView txt_hora;
    private Spinner sp_hora;
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
        sp_hora = (Spinner) findViewById(R.id.sp_hora);
        mAuth = FirebaseAuth.getInstance();


        if(sp_tratamientos != null)
        {
            String[] tratamientos = {"<Selecciona un tipo de sesion>", " PELUQUERIA ", " MANICURA ", " PEDICURA ", " MAQUILLAJE ",
                                    " LIMPIEZA FACIAL ", " TRATAMIENTOS GENERALES "};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.estilospinner, tratamientos);
            sp_tratamientos.setAdapter(adapter);
            sp_tratamientos.setOnItemSelectedListener(this);
        }

        if(sp_hora != null)
        {
            String[] horas = {"<Selecciona una hora>", " 09:00 - 10:00 ", " 10:00 - 11:00 ", " 11:00 - 12:00 ", " 12:00 - 13:00 ",
                            " 13:00 - 14:00 ", " 17:00 - 18:00 ", " 18:00 - 19:00 ", " 19:00 - 20:00 ", " 20:00 - 21:00 "};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.estilospinner1, horas);
            sp_hora.setAdapter(adapter);
            sp_hora.setOnItemSelectedListener(this);
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



    public void escoger_fecha(View view)
    {
        DatePickerFragment calendario1 = new DatePickerFragment();
        calendario1.show(getSupportFragmentManager(), "DatePicker");
    }



    public void seleccionar_Fecha(int anyo, int mes, int dia)
    {
        String texto_anyo = String.valueOf(anyo);
        String texto_mes = String.valueOf(mes);
        String texto_dia = String.valueOf(dia);
        fecha = texto_dia + "/" + texto_mes + "/" + texto_anyo;
        edt_fecha.setText(fecha);
    }


    public void guardar_Cita(View view) {


        tratamientos = sp_tratamientos.toString();
        fecha = String.valueOf(edt_fecha.getText());
        hora = sp_hora.toString();

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
