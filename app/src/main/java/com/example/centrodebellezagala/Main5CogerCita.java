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

import com.example.centrodebellezagala.clases.Modulo1CitaDatosCompletos;
import com.example.centrodebellezagala.clases.Modulo2Clientes;
import com.example.centrodebellezagala.clases.ListaCitasAdapter;
import com.example.centrodebellezagala.controladores.CitaFirebaseController;
import com.example.centrodebellezagala.controladores.CitaFirebaseController.CitaStatus;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Clase para coger una cita en el centro de belleza
 * @autor: Ronnie Mascaro Troncoso
 */


public class Main5CogerCita extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    // Atributos / declaraciones
    private TextView txt_tratamientos, txt_fecha, txt_hora;
    private Spinner sp_tratamientos, sp_hora;
    private EditText edt_fecha;
    private FirebaseAuth mAuth;
    private ListaCitasAdapter mAdapter;
    private DatabaseReference mDatabase;
    private List<Modulo1CitaDatosCompletos> citas2;


    Modulo1CitaDatosCompletos cit;
    Modulo2Clientes c;



    // Recoger variables a nivel de clase

    public String tratamientos, fecha, hora, correoCliente;
    public Modulo2Clientes clienteLogueado;



    // Estado de la aplicacion que se ejecuta si todo esta bien
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




    // Metodo para inicializar los datos para registrar una cita en la aplicacion
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5_coger_cita);
        txt_tratamientos = (TextView) findViewById(R.id.txt_tratamientos);
        sp_tratamientos = (Spinner) findViewById(R.id.sp_tratamientos);
        mAdapter = new ListaCitasAdapter(this);
        txt_fecha = (TextView) findViewById(R.id.txt_fecha);
        edt_fecha = (EditText) findViewById(R.id.edt_fecha);
        txt_hora = (TextView) findViewById(R.id.txt_hora);
        sp_hora = (Spinner) findViewById(R.id.sp_hora);
        mAuth = FirebaseAuth.getInstance();
        clienteLogueado = (Main4Menu.clienteLogueado);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        cargarCitas();

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





    // Metodo para coger los datos de los spinner de tratamientos y hora
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



    // Metodo del calendario de la fecha
    public void escoger_fecha(View view)
    {
        DatePickerFragment calendario1 = new DatePickerFragment();
        calendario1.show(getSupportFragmentManager(), "DatePicker");
    }



    // Metodo para el formato de la fecha
    public void seleccionar_Fecha(int anyo, int mes, int dia)
    {
        String texto_anyo = String.valueOf(anyo);
        String texto_mes = String.valueOf(mes + 1);
        String texto_dia = String.valueOf(dia);
        fecha = texto_dia + "/" + texto_mes + "/" + texto_anyo;
        edt_fecha.setText(fecha);
    }



    // Metodo para obtener las citas de los clientes
    public void cargarCitas()
    {
        new CitaFirebaseController().obtenerCita(new CitaStatus()
        {
            @Override
            public void citaIsLoaded(List<Modulo1CitaDatosCompletos> citas, List<String> keys)
            {

                citas2 = citas;
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

            }
        });

    }




    // Metodo de validacion de las citas si ya esta registrada
    public boolean validarCitasGuardadas()
    {
        boolean ValidadoOK = true;

        for (Modulo1CitaDatosCompletos cita : citas2)
        {
            if(sp_hora.getSelectedItem().toString().equals(cita.getHora()) &&
                    edt_fecha.getText().toString().equals(cita.getFecha()) &&
                    sp_tratamientos.getSelectedItem().toString().equals(cita.getTratamientos()))
            {
                Toast.makeText(Main5CogerCita.this, "Ya hay una cita con este tratamiento, fecha y hora seleccionada, debes tomar otra",Toast.LENGTH_LONG).show();
                ValidadoOK = false;
                return ValidadoOK;
            }
        }
            return ValidadoOK;
    }





    // Metodo para guardar una cita seleccionada
    public void guardar_Cita(View view)
    {
        boolean validar = validarCitasGuardadas();

        try {
            if (guardarCitaValidacion() && validar)
            {
                    fecha = String.valueOf(edt_fecha.getText());
                    correoCliente = mAuth.getCurrentUser().getEmail();
                    cit = new Modulo1CitaDatosCompletos(correoCliente, clienteLogueado.getNombre(), clienteLogueado.getApellidos(), tratamientos, fecha, hora);
                    new CitaFirebaseController().guardar_Cita(new CitaStatus()
                    {
                        // Cuando le hagamos clic en el boton de aceptar...


                        @Override
                        public void citaIsLoaded(List<Modulo1CitaDatosCompletos> citas, List<String> keys)
                        {

                        }

                        @Override
                        public void citaIsAdd()
                        {
                            Toast.makeText(Main5CogerCita.this, " Cita guardada correctamente ", Toast.LENGTH_LONG).show();
                            FirebaseUser user = mAuth.getCurrentUser();
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
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }




    // Metodo de validacion de los spinner de tratamientos y horas y del campo fecha en las citas
    private boolean guardarCitaValidacion() throws ParseException
    {
        boolean retorno = true;


            String date = edt_fecha.getText().toString();
            Date currentDate = Calendar.getInstance().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date strDate = sdf.parse(date);
            int prueba1 = strDate.compareTo(currentDate);
            int prueba2 = currentDate.compareTo(strDate);
            int prueba3 = strDate.compareTo(strDate);


        if (sp_tratamientos.getSelectedItemPosition() == 0)
        {
            Toast.makeText(Main5CogerCita.this, "Debes seleccionar una sesion ", Toast.LENGTH_LONG).show();
        }
        if(edt_fecha.getText().toString().isEmpty())
        {
            edt_fecha.setError(" La fecha no puede ser antes de hoy ");
            retorno = false;
        }
        if (sp_hora.getSelectedItemPosition() == 0)
        {
            Toast.makeText(Main5CogerCita.this, "Debes seleccionar una hora ", Toast.LENGTH_LONG).show();
        }

        return retorno;
    }


}
