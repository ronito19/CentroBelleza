package com.example.centrodebellezagala.controladores;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.centrodebellezagala.clases.Modulo1CitaDatosCompletos;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CitaFirebaseController
{
    // Atributos
    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;
    private List<Modulo1CitaDatosCompletos> citas;
    //private FirebaseAuth mAuth;


    // Diferentes fases de las citas
    public interface CitaStatus
    {
        void citaIsLoaded(List<Modulo1CitaDatosCompletos> citas, List<String> keys);
        void citaIsAdd();
        void citaIsUpdate();
        void citaIsDelete();
    }


    // Conexion con la base de datos de firebase con la referencia citas
    public CitaFirebaseController()
    {
        this.mDatabase  = FirebaseDatabase.getInstance();
        this.myRef = mDatabase.getReference("citas");
        this.citas  = new ArrayList<Modulo1CitaDatosCompletos>();
    }






    // Metodos para obtener, guardar, borrar y actualizar citas
        public void obtenerCita(final CitaStatus citaStatus)
    {
        this.myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                citas.clear();
                List<String> keys = new ArrayList<String>();
                for(DataSnapshot keynode: snapshot.getChildren())
                {
                    DatabaseReference databaseReference = keynode.getRef();
                    ArrayList claves = new ArrayList();
                    claves.add(databaseReference.getKey());

                    keys.add(keynode.getKey());
                    Modulo1CitaDatosCompletos cit = keynode.getValue(Modulo1CitaDatosCompletos.class);
                    cit.setId(databaseReference.getKey());
                    citas.add(cit);
                }
                citaStatus.citaIsLoaded(citas,keys);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //---------------------------------------------------------------------------------
    public void guardar_Cita(final CitaStatus citaStatus, Modulo1CitaDatosCompletos cit)
    {


        this.myRef.push().setValue(cit).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // si todo va bien
                        citaStatus.citaIsAdd();
                        Log.i("firebasedb", " La cita se guardo correctamente");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // si hay un fallo
                        Log.i("firebasedb", " La cita no se pudo guardar correctamente");
                    }
                });
    }
    //---------------------------------------------------------------------------------
    public void borrarCita(final CitaStatus citaStatus, String key)
    {
        this.myRef.child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // si todo va bien
                        citaStatus.citaIsDelete();
                        Log.i("firebasedb", " La cita se elimino correctamente");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // si hay un fallo
                        Log.i("firebasedb", " La cita no se pudo eliminar correctamente");
                    }
                });
    }
    //---------------------------------------------------------------------------------
    public void actualizarCita(final CitaStatus citaStatus, String key, Modulo1CitaDatosCompletos cit)
    {
        Map<String, Object> nuevaCita = new HashMap<String,Object>();
        nuevaCita.put(key,cit);
        myRef.updateChildren(nuevaCita).addOnSuccessListener(new OnSuccessListener<Void>()
                {
                    @Override
                    public void onSuccess(Void aVoid)
                    {
                        // si va bien
                        citaStatus.citaIsUpdate();
                        Log.i("firebasedb", " La cita se actualizo correctamente");
                    }
                })
                .addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        // si hay un fallo
                        Log.i("firebasedb", " La cita no se pudo actualizar correctamente");
                    }
                });
    }
    //---------------------------------------------------------------------------------


}
