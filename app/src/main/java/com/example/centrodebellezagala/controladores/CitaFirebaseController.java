package com.example.centrodebellezagala.controladores;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.centrodebellezagala.clases.Citas;
import com.example.centrodebellezagala.clases.Clientes;
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
    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;
    private List<Citas> citas;




    public interface CitaStatus
    {
        void citaIsLoaded(List<Citas> citas, List<String> keys);
        void citaIsAdd();
        void citaIsUpdate();
        void citaIsDelete();
    }

    public CitaFirebaseController() {
        this.mDatabase  = FirebaseDatabase.getInstance();
        this.myRef = mDatabase.getReference("citas");
        this.citas  = new ArrayList<Citas>();
    }

    public void obtenerCita(final CitaFirebaseController.CitaStatus citaStatus)
    {
        this.myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                citas.clear();
                List<String> keys = new ArrayList<String>();
                for(DataSnapshot keynode: snapshot.getChildren())
                {
                    keys.add(keynode.getKey());
                    Citas cit = keynode.getValue(Citas.class);
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
    public void guardar_Cita(final CitaStatus citaStatus, Citas cit)
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
    public void borrarCita(final CitaFirebaseController.CitaStatus citaStatus, String key)
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
    public void actualizarCita(final CitaFirebaseController.CitaStatus citaStatus, String key, Citas cit)
    {
        Map<String, Object> nuevaCita = new HashMap<String,Object>();
        nuevaCita.put(key,cit);
        myRef.updateChildren(nuevaCita).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // si todo va bien
                        citaStatus.citaIsUpdate();
                        Log.i("firebasedb", " La cita se actualizo correctamente");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // si hay un fallo
                        Log.i("firebasedb", " La cita no se pudo actualizar correctamente");
                    }
                });
    }
    //---------------------------------------------------------------------------------


}
