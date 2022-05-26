package com.example.centrodebellezagala;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * Clase que muestra el menu de la aplicacion para usuarios ya registrados
 * @autor: Ronnie Mascaro Troncoso
 */


public class Main4Menu extends AppCompatActivity
{
    public TextView txt_nombre;
    public EditText edt_nombre;

    public FirebaseAuth mAuth;
    public String usuarioUID;
    public String nombreUsuarioLogeado;
    public FirebaseUser User;
    public String emailUser;


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4_menu);
        mAuth = FirebaseAuth.getInstance();
        mAuth.getCurrentUser();
        User = mAuth.getCurrentUser();
        usuarioUID = User.getUid();
        emailUser = User.getEmail();
        usuarioUID =  mAuth.getUid();
        txt_nombre = (TextView) findViewById(R.id.txt_nombre);

    }


    // METODOS

    //Metodo que nos dirige a coger cita
    public void coger_cita(View view)
    {
        Toast.makeText(this, "Escoge el dia y la hora", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Main4Menu.this, Main5CogerCita.class);
        startActivity(intent);
    }


    //Metodo que nos dirige a ver nuestras citas, donde tambien podremos cancelarlas
    public void ver_cita(View view)
    {

    }


    //Metodo que nos dirige a ver informacion sobre el centro de belleza
    public void tu_conocenos(View view)
    {

    }


    //Metodo que nos dirige a salir de la sesion
    public void ir_a_la_salida(View view)
    {
        mAuth.signOut();
        Intent intent = new Intent(Main4Menu.this, Main1Logueo.class);
        startActivity(intent);
        this.finish();
    }



}
