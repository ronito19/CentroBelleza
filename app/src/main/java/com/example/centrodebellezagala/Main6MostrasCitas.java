package com.example.centrodebellezagala;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.centrodebellezagala.clases.CitaDatosCompletos;
import com.example.centrodebellezagala.clases.Citas;
import com.example.centrodebellezagala.clases.ListaCitasAdapter;
import com.example.centrodebellezagala.controladores.CitaFirebaseController;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * Clase que muestra todas las citas registradas asociadas al correo del cliente
 * @autor: Ronnie Mascaro Troncoso
 */



public class Main6MostrasCitas extends AppCompatActivity
{
    private RecyclerView rv_citas = null;
    private ListaCitasAdapter mAdapter;
    private ArrayList<CitaDatosCompletos> citas;
    private ArrayList<String> keys;
    private FirebaseAuth mAuth;
    private ArrayList<CitaDatosCompletos> misCitas;


    @Override
    public void onStart()
    {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            currentUser.reload();
        }
        else
        {
            Toast.makeText(Main6MostrasCitas.this, " Debes autenticarte primero ", Toast.LENGTH_SHORT).show();
            FirebaseUser user = mAuth.getCurrentUser();
            //updateUI(user);
            Intent intent = new Intent(Main6MostrasCitas.this, Main4Menu.class);
            startActivity(intent);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6_mostrar_citas);
        mAuth = FirebaseAuth.getInstance();
        rv_citas = findViewById(R.id.rv_citas);
        mAdapter = new ListaCitasAdapter(this);

        if(mAuth.getCurrentUser().getEmail().equalsIgnoreCase("administrador@gmail.com"))
        {
            new CitaFirebaseController().obtenerCita(new CitaFirebaseController.CitaStatus()
            {

                @Override
                public void citaIsLoaded(List<CitaDatosCompletos> citas, List<String> keys)
                {
                    mAdapter.setListaCitas(citas);
                    mAdapter.setKeys(keys);
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
        else
        {
            new CitaFirebaseController().obtenerCita(new CitaFirebaseController.CitaStatus()
            {

                @Override
                public void citaIsLoaded(List<CitaDatosCompletos> citas, List<String> keys)
                {
                    List<CitaDatosCompletos> misCitas = new ArrayList<CitaDatosCompletos>();
                    for (CitaDatosCompletos c: citas)
                    {
                        if(c.getCorreoCliente().equalsIgnoreCase(mAuth.getCurrentUser().getEmail()))
                        {
                            misCitas.add(c);
                        }
                    }
                    mAdapter.setListaCitas(misCitas);
                    mAdapter.setKeys(keys);
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

        //----------------------------------------------------------------------------------------
        rv_citas.setAdapter(mAdapter);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            rv_citas.setLayoutManager(new LinearLayoutManager(this));
        }
        else
        {

        }

    }


    public void salir(View view)
    {
        Intent intent = new Intent(this, Main1Logueo.class);
        startActivity(intent);
        FirebaseAuth.getInstance().signOut();
    }


    public void atras(View view)
    {
        Intent intent = new Intent(this, Main4Menu.class);
        startActivity(intent);
    }


}
