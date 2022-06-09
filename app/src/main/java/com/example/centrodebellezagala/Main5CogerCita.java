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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.centrodebellezagala.clases.CitaDatosCompletos;
import com.example.centrodebellezagala.clases.Citas;
import com.example.centrodebellezagala.clases.Clientes;
import com.example.centrodebellezagala.controladores.CitaFirebaseController;
import com.example.centrodebellezagala.controladores.ClienteFirebaseController;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

public class Main5CogerCita extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private TextView txt_tratamientos, txt_fecha, txt_hora;
    private Spinner sp_tratamientos, sp_hora;
    private EditText edt_fecha;
    private FirebaseAuth mAuth;

    CitaDatosCompletos cit;
    Clientes c;



    // Recoger variables a nivel de clase

    private String tratamientos, fecha, hora, correoCliente;
    private Clientes clienteLogueado;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
        else{
            Toast.makeText(Main5CogerCita.this, " Debes autenticarte primero ", Toast.LENGTH_SHORT).show();
            FirebaseUser user = mAuth.getCurrentUser();
            //updateUI(user);
            Intent intent = new Intent(Main5CogerCita.this, Main1Logueo.class);
            startActivity(intent);
        }
    }



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
        clienteLogueado = (Main4Menu.clienteLogueado);


        if(sp_tratamientos != null)
        {
            String[] tratamiento = {"< Selecciona un tipo de sesion >", " PELUQUERIA ", " COLORIMETRIA DE CABELLO ", " MANICURA ", " PEDICURA ",
                                    " MAQUILLAJE ", " LIMPIEZA FACIAL ", " APLICACION DE PESTAÑAS ", " TRATAMIENTOS CORPORALES "};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.estilospinner, tratamiento);
            sp_tratamientos.setAdapter(adapter);
            sp_tratamientos.setOnItemSelectedListener(this);
        }

        if(sp_hora != null)
        {
            String[] horas = {"< Selecciona una hora >", " 09:00 hrs - 10:00 hrs ", " 10:00 hrs - 11:00 hrs ", " 11:00 hrs - 12:00 hrs ", " 12:00 hrs - 13:00 hrs ",
                            " 13:00 hrs - 14:00 hrs ", " 17:00 hrs - 18:00 hrs ", " 18:00 hrs - 19:00 hrs ", " 19:00 hrs - 20:00 hrs ", " 20:00 hrs - 21:00 hrs "};
            ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.estilospinner1, horas);
            sp_hora.setAdapter(adapter1);
            sp_hora.setOnItemSelectedListener(this);
        }
    }




    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
    {
        Spinner spinner = (Spinner) adapterView;

        if (spinner.getId() == R.id.sp_tratamientos)
        {
            tratamientos = adapterView.getItemAtPosition(i).toString();
        }
        else if (spinner.getId() == R.id.sp_hora)
        {
            hora = adapterView.getItemAtPosition(i).toString();
        }

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
        String texto_mes = String.valueOf(mes + 1);
        String texto_dia = String.valueOf(dia);
        fecha = texto_dia + "/" + texto_mes + "/" + texto_anyo;
        edt_fecha.setText(fecha);
    }


    public void guardar_Cita(View view)
    {
        if(guardarCitaValidacion())
        {
            fecha = String.valueOf(edt_fecha.getText());
            correoCliente = mAuth.getCurrentUser().getEmail();
            cit = new CitaDatosCompletos(correoCliente, clienteLogueado.getNombre(), clienteLogueado.getApellidos(), tratamientos, fecha, hora);
            new CitaFirebaseController().guardar_Cita(new CitaFirebaseController.CitaStatus() {
                @Override
                public void citaIsLoaded(List<CitaDatosCompletos> citas, List<String> keys) {

                }

                @Override
                public void citaIsAdd() {
                    Toast.makeText(Main5CogerCita.this, " Cita guardada correctamente ", Toast.LENGTH_LONG).show();
                    FirebaseUser user = mAuth.getCurrentUser();
                    Intent intent = new Intent(Main5CogerCita.this, Main4Menu.class);
                    startActivity(intent);
                }

                @Override
                public void citaIsUpdate() {

                }

                @Override
                public void citaIsDelete() {

                }
            }, cit);
        }



    }


    private boolean guardarCitaValidacion()
    {
        boolean retorno = true;

        if (sp_tratamientos.getSelectedItemPosition() == 0)
        {
            Toast.makeText(Main5CogerCita.this, "Debes seleccionar una sesion ", Toast.LENGTH_LONG).show();
        }
        if(edt_fecha.getText().toString().isEmpty())
        {
            edt_fecha.setError(" Debes elegir una fecha ");
            retorno = false;
        }
        if (sp_hora.getSelectedItemPosition() == 0)
        {
            Toast.makeText(Main5CogerCita.this, "Debes seleccionar una hora ", Toast.LENGTH_LONG).show();
        }

        return retorno;
    }


}
