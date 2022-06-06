package com.example.centrodebellezagala.clases;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Objects;

public class CitaDatosCompletos implements Parcelable
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.correoCliente);
        dest.writeString(this.nombre);
        dest.writeString(this.apellidos);
        dest.writeString(this.tratamientos);
        dest.writeString(this.fecha);
        dest.writeString(this.hora);
    }

    public void readFromParcel(Parcel source) {
        this.correoCliente = source.readString();
        this.nombre = source.readString();
        this.apellidos = source.readString();
        this.tratamientos = source.readString();
        this.fecha = source.readString();
        this.hora = source.readString();
    }

    protected CitaDatosCompletos(Parcel in) {
        this.correoCliente = in.readString();
        this.nombre = in.readString();
        this.apellidos = in.readString();
        this.tratamientos = in.readString();
        this.fecha = in.readString();
        this.hora = in.readString();
    }

    public static final Creator<CitaDatosCompletos> CREATOR = new Creator<CitaDatosCompletos>() {
        @Override
        public CitaDatosCompletos createFromParcel(Parcel source) {
            return new CitaDatosCompletos(source);
        }

        @Override
        public CitaDatosCompletos[] newArray(int size) {
            return new CitaDatosCompletos[size];
        }
    };
}
