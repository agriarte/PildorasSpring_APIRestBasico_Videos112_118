package com.tallerdeapps.proyectorest.config.controller;

import com.tallerdeapps.proyectorest.entidad.Empleado;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/api")
public class EmpleadoRestController {

    private List<Empleado> misEmpleados;

    // esta anotaci�n hace que solo se gasten recursos una vez para crear la lista de empleados
    @PostConstruct
    public void cargaDatos() {
        misEmpleados = new ArrayList<>();
        misEmpleados.add(new Empleado("Juan", "D�az"));
        misEmpleados.add(new Empleado("Ana", "Mart�n"));
        misEmpleados.add(new Empleado("Esperanza", "P�rez"));
        misEmpleados.add(new Empleado("Carlos", "Areces"));
    }

    @GetMapping("/empleados")
    public List<Empleado> getEmpleados() {

        return misEmpleados;
    }

    // @PathVariable permite extraer datos de la URL para ser usados como variables en los m�todos.
    // En este ejemplo usamos /un_numero a modo de Id de la lista
    @GetMapping("/empleados/{empleadoId}")
    public Empleado getEmpleadoId(@PathVariable String empleadoId) {
        
        // para evitar entradas no num�ricas empleadoId llega como String y se convierte a Int. En caso de error se captura 
        // excepci�n personalizada.
        int id;
        try {
           id = Integer.parseInt(empleadoId); 
        } catch(NumberFormatException e){
             throw new EmpleadoNoEncontradoExcepcion("El Id " + empleadoId + " no es v�lido. Debe ser un n�mero.");
        }
        // Verifica si el �ndice es v�lido
        if (id >= 0 && id < misEmpleados.size()) {
            return misEmpleados.get(id);
        } else {
            // Si el �ndice es inv�lido, puedes manejarlo como desees. Por ejemplo, lanzar una excepci�n o devolver un valor predeterminado.
            throw new EmpleadoNoEncontradoExcepcion("El Id " + empleadoId + " no existe. Empleado no encontrado");
        }
    }
    // m�todo que devuelve una clase ResponseEntity que representa toda la respuesta http( encabezado, cuerpo, estado,..)
    // ResponseEntity devuelve en formato JSON el error. Para el estado y la hora se utiliza la clase EmpleadoRespuestaError y para 
    // la excepci�n personalizada EmpleadoNoEncontradoExcepcion
    @ExceptionHandler
    public ResponseEntity<EmpleadoRespuestaError> manejoExcepcion(EmpleadoNoEncontradoExcepcion ex){
        EmpleadoRespuestaError error = new EmpleadoRespuestaError();
        error.setEstado(HttpStatus.NOT_FOUND.value());
        error.setMensaje(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    } 
    
    /*
    control de excepciones sin crear clase para manejar excepci�n personalizada. No requiere anotaciones adicionales, clases ni m�todos.
    @GetMapping("/empleados/{empleadoId}")
    public Empleado getEmpleadoId(@PathVariable int empleadoId) {
        // Verifica si el �ndice es v�lido
        if (empleadoId >= 0 && empleadoId < misEmpleados.size()) {
            return misEmpleados.get(empleadoId);
        } else {
            // Si el �ndice es inv�lido, puedes manejarlo como desees. Por ejemplo, lanzar una excepci�n o devolver un valor predeterminado.
            throw new IllegalArgumentException("ID de empleado no v�lido: " + empleadoId);
        }
    }
    */
    

}
