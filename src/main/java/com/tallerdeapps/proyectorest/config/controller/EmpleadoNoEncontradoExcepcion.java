package com.tallerdeapps.proyectorest.config.controller;

/**
 *
 */
public class EmpleadoNoEncontradoExcepcion extends RuntimeException{

    public EmpleadoNoEncontradoExcepcion(String string) {
        super(string);
    }

    public EmpleadoNoEncontradoExcepcion(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public EmpleadoNoEncontradoExcepcion(Throwable thrwbl) {
        super(thrwbl);
    }
    
}
