package com.example.centrodebellezagala;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.centrodebellezagala.clases.Clientes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Clase que muestra el menu principal de la aplicacion para usuarios ya registrados
 * @autor: Ronnie Mascaro Troncoso
 */


public class Main4Menu extends AppCompatActivity
{
    public TextView txt_nombre;
    public static Clientes clienteLogueado;
    public String usuarioUID, emailUser;
    public FirebaseUser User;
    public FirebaseAuth mAuth;
    private FirebaseDatabase db;
    private DatabaseReference myRef;
    public Button bt1, bt2;
    public ImageView img1, img2;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4_menu);
        mAuth = FirebaseAuth.getInstance();
        mAuth.getCurrentUser();
        db = FirebaseDatabase.getInstance();
        myRef = db.getReference("clientes");
        User = mAuth.getCurrentUser();
        usuarioUID = User.getUid();
        emailUser = User.getEmail();
        usuarioUID =  mAuth.getUid();
        bt1 = (Button) findViewById(R.id.bt_coger_cita);
        bt2 = (Button) findViewById(R.id.bt_conocenos);
        img1 = (ImageView) findViewById(R.id.img_coger_cita);
        img2 = (ImageView) findViewById(R.id.img_conocenos);
        txt_nombre = (TextView) findViewById(R.id.txt_nombre);
        ocultarBotonAdmin();
        cogerDatosUsuario();
    }


    // METODOS

    private void cogerDatosUsuario()
    {
        myRef.child(mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task)
            {
                if (!task.isSuccessful())
                {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else
                {
                    clienteLogueado = task.getResult().getValue(Clientes.class);
                    txt_nombre.setText(" Hola... " + clienteLogueado.getNombre() + " " + clienteLogueado.getApellidos());
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });



    }



    public void ocultarBotonAdmin()
    {
        FirebaseUser user = mAuth.getCurrentUser();

        if(user.getEmail().equalsIgnoreCase("administrador@gmail.com"))
        {
            bt1.setVisibility(View.GONE);
            bt2.setVisibility(View.GONE);
            img1.setVisibility(View.GONE);
            img2.setVisibility(View.GONE);
        }
        else
        {
            bt1.setVisibility(View.VISIBLE);
            bt2.setVisibility(View.VISIBLE);
            img1.setVisibility(View.VISIBLE);
            img2.setVisibility(View.VISIBLE);
        }
    }



    //Metodo que nos dirige a coger cita
    public void coger_cita(View view)
    {
        Toast.makeText(this, "Escoge el tratamiento, dia y la hora", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Main4Menu.this, Main5CogerCita.class);
        startActivity(intent);
    }


    //Metodo que nos dirige a ver nuestras citas
    public void ver_cita(View view)
    {
        Intent intent = new Intent(this, Main6MostrasCitas.class);
        startActivity(intent);
    }


    //Metodo que nos dirige a ver informacion sobre el centro de belleza
    public void tu_conocenos(View view)
    {
        Intent intent = new Intent(this, Main8Info.class);
        startActivity(intent);
    }


    //Metodo que nos dirige a salir de la sesion
    public void ir_a_la_salida(View view)
    {
        mAuth.signOut();
        Intent intent = new Intent(this, Main1Logueo.class);
        startActivity(intent);
        this.finish();
    }



}
