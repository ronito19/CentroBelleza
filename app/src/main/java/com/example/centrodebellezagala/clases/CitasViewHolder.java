package com.example.centrodebellezagala.clases;

import android.view.View;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import com.example.centrodebellezagala.Main7MostrarDetallesCitas;
import com.example.centrodebellezagala.R;

public class CitasViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public static final String EXTRA_OBJETO_CITA = "es.ronito.citasViewHolder.objeto_cita";
    public static final String EXTRA_OBJETO_IMG_CITA = "es.ronito.citasViewHolder.img_cita";
    public static final String EXTRA_OBJETO_CITA_KEY = "es.ronito.citasViewHolder.objeto_cita_key";


    public TextView txt_rv_cita_correo = null;
    public TextView txt_rv_cita_nombre = null;
    public TextView txt_rv_cita_apellidos = null;
    public TextView txt_rv_cita_tratamiento = null;
    public TextView txt_rv_cita_dia = null;
    public TextView txt_rv_cita_hora = null;
    public ImageView img_rv_cita_cita = null;
    ListaCitasAdapter lcAdapter;


    public CitasViewHolder(@NonNull View itemView, ListaCitasAdapter lcAdapter)
    {
        super(itemView);
        txt_rv_cita_correo = (TextView) itemView.findViewById(R.id.txt_rv_cita_correo);
        txt_rv_cita_nombre = (TextView) itemView.findViewById(R.id.txt_rv_cita_nombre);
        txt_rv_cita_apellidos = (TextView) itemView.findViewById(R.id.txt_rv_cita_apellidos);
        txt_rv_cita_tratamiento = (TextView) itemView.findViewById(R.id.txt_rv_cita_tratamiento) ;
        txt_rv_cita_dia = (TextView) itemView.findViewById(R.id.txt_rv_cita_dia);
        txt_rv_cita_hora = (TextView) itemView.findViewById(R.id.txt_rv_cita_hora);
        img_rv_cita_cita = (ImageView) itemView.findViewById(R.id.img_rv_cita_cita);

        this.lcAdapter = lcAdapter;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        int mPosition = getAdapterPosition();
        List<CitaDatosCompletos> citas = this.lcAdapter.getListaCitas();
        List<String> keys = this.lcAdapter.getKeys();
        CitaDatosCompletos cita = citas.get(mPosition);
        String key = keys.get(mPosition);

        Intent intent = new Intent(lcAdapter.getC(), Main7MostrarDetallesCitas.class);
        intent.putExtra(EXTRA_OBJETO_CITA, cita);
        intent.putExtra(EXTRA_OBJETO_CITA_KEY, key);
        lcAdapter.getC().startActivity(intent);
    }
}
