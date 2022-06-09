package com.example.centrodebellezagala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


/**
 * Clase que muestra el boton de bienvenida de la aplicacion para usuarios ya registrados
 * @autor: Ronnie Mascaro Troncoso
 */


    public class Main3Bienvenida extends AppCompatActivity
    {

        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main3_bienvenida);
        }


        public void ir_al_menu_principal(View view)
        {
            Toast.makeText(this, " Bienvenido al menu principal ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Main4Menu.class);
            startActivity(intent);

        }
    }