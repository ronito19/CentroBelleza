package com.example.centrodebellezagala;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;



// Clase DataPicker2 para la fecha de la cita que editaremos en el MainDetalles de la cita
public class DatePicker2Fragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        super.onCreateDialog(savedInstanceState);
        final Calendar c = Calendar.getInstance();
        int anyo = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        int dia = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog fecha = new DatePickerDialog(getActivity(), this, anyo, mes, dia);

        return fecha;
    }



    @Override
    public void onDateSet(DatePicker datePicker, int anyo, int mes, int dia)
    {
        Main7MostrarDetallesCitas activity7 = (Main7MostrarDetallesCitas) getActivity();
        activity7.actualizar_Fecha(anyo, mes, dia);
    }
}
