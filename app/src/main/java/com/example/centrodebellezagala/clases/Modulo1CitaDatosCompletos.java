package com.example.centrodebellezagala.clases;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Modulo1CitaDatosCompletos implements Parcelable
{
    // Atributos
    private String correoCliente;
    private String nombre;
    private String apellidos;
    private String tratamientos;
    private String fecha;
    private String hora;
    private String foto;
    private String id;
    //-------------------------------------------------------------


    // Constructores
    public Modulo1CitaDatosCompletos()
    {
        this.correoCliente = "";
        this.nombre = "";
        this.apellidos = "";
        this.tratamientos = "";
        this.fecha = "";
        this.hora = "";
        this.foto = null;
    }


    public Modulo1CitaDatosCompletos(String correoCliente, String nombre, String apellidos, String tratamientos, String fecha, String hora)
    {
        this.correoCliente = correoCliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.tratamientos = tratamientos;
        this.fecha = fecha;
        this.hora = hora;
        this.foto = null;
    }


    public Modulo1CitaDatosCompletos(String correoCliente, String nombre, String apellidos, String tratamientos, String fecha, String hora, String foto) {
        this.correoCliente = correoCliente;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.tratamientos = tratamientos;
        this.fecha = fecha;
        this.hora = hora;
        this.foto = foto;
    }


    //---------------------------------------------------------------------------------------------------

    // Getters y setters
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //-------------------------------------------------------------------------------------------

    // Equals y hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Modulo1CitaDatosCompletos)) return false;
        Modulo1CitaDatosCompletos citas = (Modulo1CitaDatosCompletos) o;
        return correoCliente.equals(citas.correoCliente) && fecha.equals(citas.fecha) && hora.equals(citas.hora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(correoCliente, fecha, hora);
    }
    //-------------------------------------------------------------------------------------------



    // ToString
    @Override
    public String toString() {
        return "CitaDatosCompletos{" +
                "correoCliente='" + correoCliente + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", tratamientos='" + tratamientos + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }


    // Metodos Parcelables
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
        dest.writeString(this.foto);
        dest.writeString(this.id);
    }

    public void readFromParcel(Parcel source) {
        this.correoCliente = source.readString();
        this.nombre = source.readString();
        this.apellidos = source.readString();
        this.tratamientos = source.readString();
        this.fecha = source.readString();
        this.hora = source.readString();
        this.foto = source.readString();
        this.id = source.readString();
    }

    protected Modulo1CitaDatosCompletos(Parcel in) {
        this.correoCliente = in.readString();
        this.nombre = in.readString();
        this.apellidos = in.readString();
        this.tratamientos = in.readString();
        this.fecha = in.readString();
        this.hora = in.readString();
        this.foto = in.readString();
        this.id = in.readString();
    }

    public static final Creator<Modulo1CitaDatosCompletos> CREATOR = new Creator<Modulo1CitaDatosCompletos>() {
        @Override
        public Modulo1CitaDatosCompletos createFromParcel(Parcel source) {
            return new Modulo1CitaDatosCompletos(source);
        }

        @Override
        public Modulo1CitaDatosCompletos[] newArray(int size) {
            return new Modulo1CitaDatosCompletos[size];
        }
    };
}
