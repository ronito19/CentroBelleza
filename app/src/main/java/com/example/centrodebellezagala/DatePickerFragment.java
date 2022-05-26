package com.example.centrodebellezagala;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener
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
        Main5CogerCita activity5 = (Main5CogerCita) getActivity();
        activity5.seleccionar_Fecha(anyo, mes, dia);
    }
}
