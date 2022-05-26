package com.example.centrodebellezagala.clases;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Clientes implements Parcelable
{

    private String nombre;
    private String apellidos;
    private Integer edad;
    private String telefono;
    private String correo;
    private String clave;
    private String foto;
    //-----------------------------------------------------------------------------------------


    public Clientes()
    {
        this.nombre = "";
        this.apellidos = "";
        this.edad = 0;
        this.telefono = "";
        this.correo = "";
        this.clave = "";
        this.foto = null;
    }


    public Clientes(String nombre, String apellidos, Integer edad, String telefono, String correo, String clave)
    {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
        this.clave = clave;
        this.foto = null;
    }


    public Clientes(String nombre)
    {
        this.nombre = "";
        this.apellidos = "";
        this.edad = 0;
        this.telefono = "";
        this.correo = "";
        this.clave = "";
        this.foto = null;
    }


    public Clientes(String nombre, String apellidos, Integer edad, String telefono, String correo, String clave, String foto) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
        this.clave = clave;
        this.foto = foto;
    }
    //--------------------------------------------------------------------------------------






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

    public Integer getEdad(Integer edad) {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    //-----------------------------------------------------------------------------------


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clientes)) return false;
        Clientes clientes = (Clientes) o;
        return correo.equals(clientes.correo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(correo);
    }
    //-----------------------------------------------------------------------------------


    @Override
    public String toString() {
        return "Clientes{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", edad=" + edad +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", clave='" + clave + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.apellidos);
        dest.writeValue(this.edad);
        dest.writeString(this.telefono);
        dest.writeString(this.correo);
        dest.writeString(this.clave);
        dest.writeString(this.foto);
    }

    public void readFromParcel(Parcel source) {
        this.nombre = source.readString();
        this.apellidos = source.readString();
        this.edad = (Integer) source.readValue(Integer.class.getClassLoader());
        this.telefono = source.readString();
        this.correo = source.readString();
        this.clave = source.readString();
        this.foto = source.readString();
    }

    protected Clientes(Parcel in) {
        this.nombre = in.readString();
        this.apellidos = in.readString();
        this.edad = (Integer) in.readValue(Integer.class.getClassLoader());
        this.telefono = in.readString();
        this.correo = in.readString();
        this.clave = in.readString();
        this.foto = in.readString();
    }

    public static final Creator<Clientes> CREATOR = new Creator<Clientes>() {
        @Override
        public Clientes createFromParcel(Parcel source) {
            return new Clientes(source);
        }

        @Override
        public Clientes[] newArray(int size) {
            return new Clientes[size];
        }
    };
}
