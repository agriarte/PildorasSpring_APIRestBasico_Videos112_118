package com.tallerdeapps.proyectorest.entidad;

/**
 *
 */
public class Empleado {
    private String nombre;
    private String apellidos;

    public Empleado() {
    }

    public Empleado(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
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
    

    
    
}
