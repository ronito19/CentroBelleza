package com.example.centrodebellezagala.clases;

import java.io.Serializable;
import java.util.Objects;

public class Citas implements Serializable
{
    private String tratamientos;
    private String fecha;
    private String hora;
    private Modulo2Clientes cliente;
    //-----------------------------------------------------------


    public Citas(String tratamiento, String fecha, String hora, String correoCliente)
    {
        this.tratamientos = "";
        this.fecha = "";
        this.hora = "";
        this.cliente = null;
    }



    public Citas(String tratamientos, String fecha, String hora, Modulo2Clientes cliente)
    {
        this.tratamientos = tratamientos;
        this.fecha = fecha;
        this.hora = hora;
        this.cliente = cliente;
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

    public Modulo2Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Modulo2Clientes cliente) {
        this.cliente = cliente;
    }

    //-------------------------------------------------------------------

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Citas)) return false;
        Citas citas = (Citas) o;
        return fecha.equals(citas.fecha) && hora.equals(citas.hora) && cliente.equals(citas.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fecha, hora, cliente);
    }


    //----------------------------------------------------------------------


    @Override
    public String toString() {
        return "Citas{" +
                "tratamientos='" + tratamientos + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", cliente=" + cliente +
                '}';
    }
}
