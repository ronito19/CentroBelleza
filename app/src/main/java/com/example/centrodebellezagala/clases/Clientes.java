package com.example.centrodebellezagala.clases;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Objects;

public class Clientes implements Serializable
{

    private String nombre;
    private String apellidos;
    private Integer edad;
    private String telefono;
    private String correo;
    private String clave1;
    private String clave2;
    private String foto;
    //-----------------------------------------------------------------------------------------


    public Clientes()
    {
        this.nombre = "";
        this.apellidos = "";
        this.edad = 0;
        this.telefono = "";
        this.correo = "";
        this.clave1 = "";
        this.clave2 = "";
        this.foto = null;
    }


    public Clientes(String nombre, String apellidos, Integer edad, String telefono, String correo, String clave1, String clave2)
    {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
        this.clave1 = clave1;
        this.clave2 = clave2;
        this.foto = null;
    }


    public Clientes(String nombre, String apellidos, Integer edad, String telefono, String correo, String foto)
    {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
        this.foto = null;
    }


    public Clientes(String nombre)
    {
        this.nombre = "";
        this.apellidos = "";
        this.edad = 0;
        this.telefono = "";
        this.correo = "";
        this.clave1 = "";
        this.clave2 = "";
        this.foto = null;
    }


    public Clientes(String nombre, String apellidos, Integer edad, String telefono, String correo, String clave1, String clave2, String foto) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
        this.clave1 = clave1;
        this.clave2 = clave2;
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

    public Integer getEdad(){return edad;}

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

    public String getClave1() {
        return clave1;
    }

    public void setClave1(String clave1) {
        this.clave1 = clave1;
    }

    public String getClave2() {
        return clave2;
    }

    public void setClave2(String clave2) {
        this.clave2 = clave2;
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
                ", clave1='" + clave1 + '\'' +
                ", clave2='" + clave2 + '\'' +
                ", foto='" + foto + '\'' +
                '}';
    }


}
