package com.example.centrodebellezagala.clases;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Objects;

public class CitaDatosCompletos implements Serializable
{
    private String correoCliente;
    private String nombre;
    private String apellidos;
    private String tratamientos;
    private String fecha;
    private String hora;
    //-------------------------------------------------------------



    public CitaDatosCompletos()
    {
        this.correoCliente = "";
        this.nombre = "";
        this.apellidos = "";
        this.tratamientos = "";
        this.fecha = "";
        this.hora = "";
    }


    public CitaDatosCompletos(String correoCliente, String nombre, String apellidos, String tratamientos, String fecha, String hora)
    {
        this.correoCliente = correoCliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.tratamientos = tratamientos;
        this.fecha = fecha;
        this.hora = hora;
    }


    //---------------------------------------------------------------------------------------------------


    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTratamientos() {
        return tratamientos;
    }

    public void setTratamientos(String tratamientos) {
        this.tratamientos = tratamientos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    //-------------------------------------------------------------------------------------------


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CitaDatosCompletos)) return false;
        CitaDatosCompletos citas = (CitaDatosCompletos) o;
        return correoCliente.equals(citas.correoCliente) && fecha.equals(citas.fecha) && hora.equals(citas.hora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(correoCliente, fecha, hora);
    }
    //-------------------------------------------------------------------------------------------




    @Override
    public String toString() {
        return "CitaDatosCompletos{" +
                "correoCliente='" + correoCliente + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", tratamientos='" + tratamientos + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }


}
