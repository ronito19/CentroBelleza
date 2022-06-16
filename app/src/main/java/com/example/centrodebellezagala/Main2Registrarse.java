package com.example.centrodebellezagala;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.centrodebellezagala.clases.Modulo2Clientes;
import com.example.centrodebellezagala.controladores.ClienteFirebaseController;
import com.example.centrodebellezagala.utilidades.ImagenesFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.List;


/**
 * Clase para registrarse, si el usuario no tiene cuenta, puede guardar sus datos
 * @autor: Ronnie Mascaro Troncoso
 */

public class Main2Registrarse extends AppCompatActivity
{
    // Atributos / declaraciones
    public EditText edt_nombre, edt_apellidos, edt_edad, edt_telefono, edt_correo, edt_clave1, edt_clave2;
    public ImageView img_registrarse;
    private FirebaseAuth mAuth;

    Modulo2Clientes cli;



    public static final int NUEVA_IMAGEN = 1;
    Uri imagen_seleccionada = null;


    // Estado de la aplicacion que se ejecuta si no se hace nada
    @Override
    protected void onResume()
    {
        super.onResume();
        FirebaseAuth.getInstance().signOut();
    }



    // Estado de la aplicacion que se ejecuta si todo esta bien
    @Override
    public void onStart()
    {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
        {
            Intent intent = new Intent(Main2Registrarse.this, Main1Logueo.class);
            startActivity(intent);
            finish();
        }
    }




    // Metodo para inicializar los datos del registro en la aplicacion
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_registrarse);

        edt_nombre = (EditText) findViewById(R.id.edt_nombre);
        edt_apellidos = (EditText) findViewById(R.id.edt_apellidos);
        edt_edad = (EditText) findViewById(R.id.edt_edad);
        edt_telefono = (EditText) findViewById(R.id.edt_telefono);
        edt_correo = (EditText) findViewById(R.id.edt_correo1);
        edt_clave1 = (EditText) findViewById(R.id.edt_clave1);
        edt_clave2 = (EditText) findViewById(R.id.edt_clave2);
        img_registrarse = (ImageView) findViewById(R.id.img_registrarse);
        mAuth = FirebaseAuth.getInstance();

    }




    // Metodo que registra un nuevo cliente
    public void insertar_cliente(View view)
    {
        if(validarFormularioRegistro())
        {

            String nombre = String.valueOf(edt_nombre.getText());
            String apellidos = String.valueOf(edt_apellidos.getText());
            Integer edad = Integer.parseInt(String.valueOf(edt_edad.getText()));
            String telefono = String.valueOf(edt_telefono.getText());
            String correo = String.valueOf(edt_correo.getText()).trim();
            String clave1 = String.valueOf(edt_clave1.getText());
            String clave2 = String.valueOf(edt_clave2.getText());

            mAuth.createUserWithEmailAndPassword(correo, clave1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
            {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if (task.isSuccessful())
                    {


                        if (imagen_seleccionada != null)
                        {
                            String email = mAuth.getCurrentUser().getEmail();
                            new ImagenesFirebase().subirFoto(new ImagenesFirebase.FotoStatus()
                            {
                                @Override
                                public void FotoIsDownload(byte[] bytes) {
                                }

                                @Override
                                public void FotoIsDelete() {
                                }

                                @Override
                                public void FotoIsUpload() {
                                    Toast.makeText(Main2Registrarse.this, " La foto se ha guardado correctamente ", Toast.LENGTH_LONG).show();
                                }

                            }, email, img_registrarse);

                            cli = new Modulo2Clientes(nombre, apellidos, edad, telefono, correo,email + "/" + ".png");
                        }
                        else
                        {
                            cli = new Modulo2Clientes(nombre, apellidos, edad, telefono, correo,null);
                        }
                        new ClienteFirebaseController().insertarCliente(new ClienteFirebaseController.ClienteStatus()
                        {
                            @Override
                            public void clienteIsLoaded(List<Modulo2Clientes> clientes, List<String> keys)
                            {

                            }

                            @Override
                            public void clienteIsAdd()
                            {
                                // Sign in success, update UI with the signed-in user's information
                                Log.i("firebasedb", "createUserWithEmail:success");
                                Toast.makeText(Main2Registrarse.this, " El cliente se ha registrado correctamente, BIENVENIDO ", Toast.LENGTH_LONG).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(Main2Registrarse.this, Main1Logueo.class);
                                startActivity(intent);

                            }

                            @Override
                            public void clienteIsUpdate()
                            {

                            }

                            @Override
                            public void clienteIsDelete()
                            {

                            }

                            @Override
                            public void clienteIsEncontrado(Modulo2Clientes cli)
                            {

                            }
                        }, cli,mAuth);
                    }
                    else
                    {
                        Log.i("firebasedb", "createUserWithEmail:failure", task.getException());
                        Toast.makeText(Main2Registrarse.this, "Autenticacion fallida", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }



        // Metodo de validacion de campos en el registro, que no esten vacios
        public boolean validarFormularioRegistro()
        {
            boolean retorno = true;

            if(edt_nombre.getText().toString().isEmpty())
            {
                edt_nombre.setError(" Debes rellenar este campo ");
                retorno = false;
            }
            if(edt_apellidos.getText().toString().isEmpty())
            {
                edt_apellidos.setError(" Debes rellenar este campo ");
                retorno = false;
            }
            if(edt_edad.getText().toString().equals(""))
            {
                edt_edad.setError(" Debes rellenar este campo ");
                retorno = false;
            }
            if(edt_telefono.getText().toString().isEmpty() || edt_telefono.length() < 9)
            {
                edt_telefono.setError(" Debes rellenar este campo con 9 caracteres ");
                retorno = false;
            }
            if(edt_correo.getText().toString().isEmpty() || !edt_correo.getText().toString().contains("@"))
            {
                edt_correo.setError(" Debes rellenar este campo con un correo valido ");
                retorno = false;
            }
            if(edt_clave1.getText().toString().isEmpty() || edt_clave1.length() < 6)
            {
                edt_clave1.setError(" Debes rellenar este campo con 6 caracteres ");
                retorno = false;
            }
            if(edt_clave2.getText().toString().isEmpty()  || !(edt_clave2.getText().toString().equals(edt_clave1.getText().toString())))
            {
                edt_clave2.setError(" Clave no valida, no coincide ");
                retorno = false;
            }

            return retorno;
        }



        //---------------------------------------------------------------------------------------------------------------


        //--------CODIGO PARA CAMBIAR LA IMAGEN----------------
        public void cambiar_imagen(View view)
        {
            Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
            getIntent.setType("image/*");

            Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickIntent.setType("image/*");

            Intent chooserIntent = Intent.createChooser(getIntent, " Selecciona una imagen ");
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});
            startActivityForResult(chooserIntent, NUEVA_IMAGEN);
        }


        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data)
        {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == NUEVA_IMAGEN && resultCode == Activity.RESULT_OK)
            {
                imagen_seleccionada = data.getData();
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imagen_seleccionada);
                    img_registrarse.setImageBitmap(bitmap);

                    //---------------------------------------------

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }



