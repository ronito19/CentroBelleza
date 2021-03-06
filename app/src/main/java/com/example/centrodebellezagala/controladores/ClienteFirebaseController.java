package com.example.centrodebellezagala.controladores;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.centrodebellezagala.clases.Modulo2Clientes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteFirebaseController
{
    // Atributos
    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;
    private List<Modulo2Clientes> clientes;
    private Modulo2Clientes cli;




    // Diferentes fases de los clientes
    public interface ClienteStatus
    {
        void clienteIsLoaded(List<Modulo2Clientes> clientes, List<String> keys);
        void clienteIsAdd();
        void clienteIsUpdate();
        void clienteIsDelete();
        void clienteIsEncontrado(Modulo2Clientes cli);
    }


    // Conexion con la base de datos con la referencia Clientes
    public ClienteFirebaseController()
    {
        this.mDatabase  = FirebaseDatabase.getInstance();
        this.myRef = mDatabase.getReference("clientes");
        this.clientes  = new ArrayList<Modulo2Clientes>();
    }



    // Metodos para obtener, insertar, borrar y actualizar un cliente
    public void obtenerCliente(final ClienteStatus clienteStatus)
    {
        this.myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clientes.clear();
                List<String> keys = new ArrayList<String>();
                for(DataSnapshot keynode: snapshot.getChildren())
                {
                    keys.add(keynode.getKey());
                    Modulo2Clientes cli = keynode.getValue(Modulo2Clientes.class);
                    clientes.add(cli);
                }
                clienteStatus.clienteIsLoaded(clientes,keys);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    //-----------------------------------------------------------------------------------



    public void obtener_un_Cliente(final ClienteStatus clienteStatus, String email)
    {
        this.myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clientes.clear();
                List<String> keys = new ArrayList<String>();
                for(DataSnapshot keynode: snapshot.getChildren())
                {
                    keys.add(keynode.getKey());
                    Modulo2Clientes cli = keynode.getValue(Modulo2Clientes.class);
                    if(cli.getNombre().equalsIgnoreCase(email))
                    {
                        clienteStatus.clienteIsEncontrado(cli);
                        return ;
                    }


                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //---------------------------------------------------------------------------------
    public void insertarCliente(final ClienteStatus clienteStatus, Modulo2Clientes cli, FirebaseAuth mAuth)
    {

        this.myRef.child(mAuth.getUid()).setValue(cli).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // si todo va bien
                clienteStatus.clienteIsAdd();
                Log.i("firebasedb", "El cliente se inserto correctamente");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // si hay un fallo
                        Log.i("firebasedb", "El cliente no se pudo insertar correctamente");
                    }
                });
    }
    //---------------------------------------------------------------------------------
    public void borrarCliente(final ClienteStatus clienteStatus, String key)
    {
        this.myRef.child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // si todo va bien
                clienteStatus.clienteIsDelete();
                Log.i("firebasedb", "El cliente se borro correctamente");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // si hay un fallo
                        Log.i("firebasedb", "El cliente no se pudo borrar correctamente");
                    }
                });
    }
    //---------------------------------------------------------------------------------
    public void actualizarCliente(final ClienteStatus clienteStatus, String key, Modulo2Clientes cli)
    {
        Map<String, Object> nuevoCliente = new HashMap<String,Object>();
        nuevoCliente.put(key,cli);
        myRef.updateChildren(nuevoCliente).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // si todo va bien
                clienteStatus.clienteIsUpdate();
                Log.i("firebasedb", "El cliente se actualizo correctamente");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // si hay un fallo
                        Log.i("firebasedb", "El cliente no se pudo actualizar correctamente");
                    }
                });
    }
    //---------------------------------------------------------------------------------


}
