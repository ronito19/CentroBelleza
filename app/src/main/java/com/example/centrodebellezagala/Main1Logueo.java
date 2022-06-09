package com.example.centrodebellezagala;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Clase para loguearse, si el usuario ya tiene cuenta, puede loguearse para poder ingresar
 * @author: Ronnie Mascaro Troncoso
 */
public class Main1Logueo extends AppCompatActivity
{

    private EditText edt_correo, edt_clave;
    private FirebaseAuth mAuth;


    @Override
    protected void onResume()
    {
        super.onResume();
        FirebaseAuth.getInstance().signOut();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1_logueo);

        edt_correo = (EditText) findViewById(R.id.edt_correo);
        edt_clave = (EditText) findViewById(R.id.edt_clave);
        mAuth = FirebaseAuth.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("prueba3");
        myRef.setValue("valor3");

    }


    @Override
    public void onStart()
    {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            Intent intent = new Intent(this,Main4Menu.class);
            startActivity(intent);
            finish();
        }
    }


    public void Logueate(View view)
    {
        if(validarLogin())
        {
            String correo = String.valueOf(edt_correo.getText());
            String clave = String.valueOf(edt_clave.getText());
            mAuth.signInWithEmailAndPassword(correo, clave).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
            {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if (task.isSuccessful())
                    {
                        Log.i("firebasedb", "signInWithEmail:success");
                        Toast.makeText(Main1Logueo.this, "Logueado correctamente, ya puedes ingresar..!", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        //---------------------------------------------------------------------------------------------------------
                        //Toast.makeText(Main1Logueo.this, "Logueado correctamente, ya puedes ingresar..!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Main1Logueo.this, Main4Menu.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Log.i("firebasedb", "signInWithEmail:failure", task.getException());
                        Toast.makeText(Main1Logueo.this, "La cuenta no existe, debes RESISTRARTE..!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }



    public void registrarse(View view)
    {
        Toast.makeText(Main1Logueo.this, " Por favor, REGISTRATE AHORA... ", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Main1Logueo.this, Main2Registrarse.class);
        startActivity(intent);



    }


    public boolean validarLogin()
    {
        boolean retorno = true;

        if(edt_correo.getText().toString().isEmpty())
        {
            edt_correo.setError(" Debes rellenar este campo ");
            retorno = false;
        }
        if(edt_clave.getText().toString().isEmpty())
        {
            edt_clave.setError(" Debes rellenar este campo ");
            retorno = false;
        }

        return retorno;
    }





}
