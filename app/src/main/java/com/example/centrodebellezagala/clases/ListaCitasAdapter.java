package com.example.centrodebellezagala.clases;

import static com.example.centrodebellezagala.utilidades.ImagenesBlobBitmap.decodeSampledBitmapFrombyteArray;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.centrodebellezagala.R;
import com.example.centrodebellezagala.utilidades.ImagenesFirebase;

import java.util.ArrayList;
import java.util.List;

public class ListaCitasAdapter extends RecyclerView.Adapter<CitasViewHolder>
{
    private Context c;
    private List<CitaDatosCompletos> listaCitas;
    private List<String> keys;
    private LayoutInflater mInflater;


    public void setC(Context c) {
        this.c = c;
        this.listaCitas = new ArrayList<CitaDatosCompletos>();
    }


    public ListaCitasAdapter(Context c, List<CitaDatosCompletos> listaCitas,List<String> keys) {
        this.c = c;
        this.listaCitas = listaCitas;
        this.keys = keys;
        mInflater = LayoutInflater.from(c);
    }


    public Context getC()
    {
        return c;
    }


    public List<String> getKeys()
    {
        return keys;
    }


    public void setKeys(List<String> keys)
    {
        this.keys = keys;
    }


    public List<CitaDatosCompletos> getListaCitas()
    {
        return listaCitas;
    }


    public void setListaCitas(List<CitaDatosCompletos> listaCitas)
    {
        this.listaCitas = listaCitas;
        notifyDataSetChanged();
    }

    public ListaCitasAdapter(Context c)
    {
        this.c = c;
        mInflater = LayoutInflater.from(c);
    }

    @NonNull
    @Override
    public CitasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View mItemView = mInflater.inflate(R.layout.item_recyclerview_cita, parent, false);
        return new CitasViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CitasViewHolder holder, int position)
    {
        if(listaCitas != null)
        {
            CitaDatosCompletos cita_actual = listaCitas.get(position);
            holder.txt_rv_cita_correo.setText(" Correo: " + cita_actual.getCorreoCliente());
            holder.txt_rv_cita_nombre.setText(" Nombre: " + cita_actual.getNombre());
            holder.txt_rv_cita_apellidos.setText(" Apellidos: " + cita_actual.getApellidos());
            holder.txt_rv_cita_tratamiento.setText(" Tratamiento: " + cita_actual.getTratamientos());
            holder.txt_rv_cita_dia.setText(" Dia: " + cita_actual.getFecha());
            holder.txt_rv_cita_hora.setText(" Hora: " + cita_actual.getHora());


        }
    }


    @Override
    public int getItemCount()
    {
        if (listaCitas != null)
            return listaCitas.size();

        else return 0;
    }
}
