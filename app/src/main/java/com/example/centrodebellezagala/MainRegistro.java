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

public class MainRegistro extends AppCompatActivity
{

    private EditText edt_correo = null;
    private EditText edt_clave = null;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registro);
        edt_correo = (EditText) findViewById(R.id.edt_correo);
        edt_clave = (EditText) findViewById(R.id.edt_clave);
        mAuth = FirebaseAuth.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("prueba3");
        myRef.setValue("valor3");

    }


    /*@Override
    public void onStart()
    {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            currentUser.reload();
        }
    }*/


    public void ir_a_iniciaSesion(View view)
    {
        String correo = String.valueOf(edt_correo.getText());
        String clave = String.valueOf(edt_clave.getText());
        mAuth.signInWithEmailAndPassword(correo, clave)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("firebasedb", "signInWithEmail:success");
                            Toast.makeText(MainRegistro.this, "signInWithEmail:exito", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Intent intent = new Intent(MainRegistro.this, MainRegistrate.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("firebasedb", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainRegistro.this, "signInWithEmail:malo", Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }
                    }
                });
    }



    public void ir_a_registrarse(View view)
    {
        String correo = String.valueOf(edt_correo.getText());
        String clave = String.valueOf(edt_clave.getText());
        mAuth.signInWithEmailAndPassword(correo, clave)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("firebasedb", "signInWithEmail:success");
                            Toast.makeText(MainRegistro.this, "signInWithEmail:failure", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            Intent intent = new Intent(MainRegistro.this, MainRegistrate.class);
                            startActivity(intent);
                        }
                        else
                        {
                            // If sign in fails, display a message to the user.
                            Log.i("firebasedb", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainRegistro.this, "signInWithEmail:failure", Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }
                    }
                });
    }





}
