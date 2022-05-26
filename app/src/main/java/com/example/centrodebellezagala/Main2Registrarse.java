package com.example.centrodebellezagala;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.centrodebellezagala.clases.Clientes;
import com.example.centrodebellezagala.controladores.ClienteFirebaseController;
import com.example.centrodebellezagala.utilidades.ImagenesFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.List;


/**
 * Clase para registrarse, si el usuario no tiene cuenta, puede guardar sus datos
 * @author: Ronnie Mascaro Troncoso
 */

public class Main2Registrarse extends AppCompatActivity
{
    private EditText edt_nombre;
    private EditText edt_apellidos;
    private EditText edt_edad;
    private EditText edt_telefono;
    private EditText edt_correo;
    private EditText edt_clave;
    private Button bt_registrarse;
    private ImageView img_registrarse;
    private FirebaseAuth mAuth;


    Clientes cli;
    public static final int NUEVA_IMAGEN = 1;
    Uri imagen_seleccionada = null;



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
            Toast.makeText(Main2Registrarse.this, " Debes autenticarte ", Toast.LENGTH_SHORT).show();
            FirebaseUser user = mAuth.getCurrentUser();
            //updateUI(user);
            Intent intent = new Intent(Main2Registrarse.this, Main3Bienvenida.class);
            startActivity(intent);
        }
    }



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
        edt_clave = (EditText) findViewById(R.id.edt_clave1);
        img_registrarse = (ImageView) findViewById(R.id.img_registrarse);
        mAuth = FirebaseAuth.getInstance();

    }



    public void insertar_cliente(View view)
    {
        if(validarFormularioRegistro())
        {

            String nombre = String.valueOf(edt_nombre.getText());
            String apellidos = String.valueOf(edt_apellidos.getText());
            Integer edad = Integer.parseInt(String.valueOf(edt_edad.getText()));
            String telefono = String.valueOf(edt_telefono.getText());
            String correo = String.valueOf(edt_correo.getText());
            String clave = String.valueOf(edt_clave.getText());


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

                cli = new Clientes(nombre, apellidos, edad, telefono, correo, clave, email + "/" + ".png");
            } else
            {
                cli = new Clientes(nombre, apellidos, edad, telefono, correo, clave, null);
            }
            new ClienteFirebaseController().insertarCliente(new ClienteFirebaseController.ClienteStatus()
            {
                @Override
                public void clienteIsLoaded(List<Clientes> clientes, List<String> keys)
                {

                }

                @Override
                public void clienteIsAdd()
                {
                    // aquí hay que poner cuando se haya insertado bien qué hacer
                    Toast.makeText(Main2Registrarse.this, " El cliente se ha registrado correctamente, BIENVENIDO ", Toast.LENGTH_LONG).show();
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
            }, cli);
        }
    }


        public boolean validarFormularioRegistro()
        {
            boolean retorno = true;

            String nombre = edt_nombre.getText().toString();
            String apellidos = edt_apellidos.getText().toString();
            int edad = Integer.valueOf(edt_edad.getText().toString());
            String telefono = edt_telefono.getText().toString();
            String correo = edt_correo.getText().toString();
            String clave = edt_clave.getText().toString();

            if(nombre.isEmpty())
            {
                edt_nombre.setError(" Debes rellenar este campo ");
                retorno = false;
            }
            if(apellidos.isEmpty())
            {
                edt_apellidos.setError(" Debes rellenar este campo ");
                retorno = false;
            }
            if(edad == 0)
            {
                edt_edad.setError(" Debes rellenar este campo ");
                retorno = false;
            }
            if(telefono.isEmpty())
            {
                edt_telefono.setError(" Debes rellenar este campo ");
                retorno = false;
            }
            if(correo.isEmpty())
            {
                edt_correo.setError(" Debes rellenar este campo ");
                retorno = false;
            }
            if(clave.isEmpty())
            {
                edt_clave.setError(" Debes rellenar este campo ");
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



