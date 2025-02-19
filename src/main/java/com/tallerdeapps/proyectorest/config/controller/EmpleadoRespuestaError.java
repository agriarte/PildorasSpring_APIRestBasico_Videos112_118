package com.tallerdeapps.proyectorest.config.controller;

/**
 *
 */
public class EmpleadoRespuestaError {
    
    private int estado;
    private String mensaje;
    private long timeStamp;

    public EmpleadoRespuestaError() {
    }

    public EmpleadoRespuestaError(int estado, String mensaje, long timeStamp) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.timeStamp = timeStamp;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    
    
    
}
