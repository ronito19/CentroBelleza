package com.example.centrodebellezagala.clases;

import java.io.Serializable;
import java.util.Objects;

public class Citas implements Serializable
{
    private String tratamientos;
    private String fecha;
    private String hora;
    //-----------------------------------------------------------


    public Citas()
    {
        this.tratamientos = "";
        this.fecha = "";
        this.hora = "";
    }



    public Citas(String tratamientos, String fecha, String hora) {
        this.tratamientos = tratamientos;
        this.fecha = fecha;
        this.hora = hora;
    }
    //----------------------------------------------------------------


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
    //-------------------------------------------------------------------


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Citas)) return false;
        Citas citas = (Citas) o;
        return tratamientos.equals(citas.tratamientos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tratamientos);
    }
    //----------------------------------------------------------------------


    @Override
    public String toString() {
        return "Citas{" +
                "tratamientos='" + tratamientos + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}
