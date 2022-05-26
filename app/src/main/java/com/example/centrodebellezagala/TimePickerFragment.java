package com.example.centrodebellezagala;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        super.onCreateDialog(savedInstanceState);
        Calendar c1 = Calendar.getInstance();
        int horas = c1.get(Calendar.HOUR_OF_DAY);
        int minutos = c1.get(Calendar.MINUTE);
        TimePickerDialog hora = new TimePickerDialog(getActivity(), this, horas, minutos, true);
        return hora;

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int horas, int minutos)
    {
        Main5CogerCita activity51 = (Main5CogerCita) getActivity();
        activity51.seleccionar_Hora(horas, minutos);

    }
}
