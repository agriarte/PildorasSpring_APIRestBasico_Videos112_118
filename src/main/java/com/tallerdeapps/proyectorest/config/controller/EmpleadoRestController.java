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

    // esta anotación hace que solo se gasten recursos una vez para crear la lista de empleados
    @PostConstruct
    public void cargaDatos() {
        misEmpleados = new ArrayList<>();
        misEmpleados.add(new Empleado("Juan", "Díaz"));
        misEmpleados.add(new Empleado("Ana", "Martín"));
        misEmpleados.add(new Empleado("Esperanza", "Pérez"));
        misEmpleados.add(new Empleado("Carlos", "Areces"));
    }

    @GetMapping("/empleados")
    public List<Empleado> getEmpleados() {

        return misEmpleados;
    }

    // @PathVariable permite extraer datos de la URL para ser usados como variables en los métodos.
    // En este ejemplo usamos /un_numero a modo de Id de la lista
    @GetMapping("/empleados/{empleadoId}")
    public Empleado getEmpleadoId(@PathVariable String empleadoId) {
        
        // para evitar entradas no numéricas empleadoId llega como String y se convierte a Int. En caso de error se captura 
        // excepción personalizada.
        int id;
        try {
           id = Integer.parseInt(empleadoId); 
        } catch(NumberFormatException e){
             throw new EmpleadoNoEncontradoExcepcion("El Id " + empleadoId + " no es válido. Debe ser un número.");
        }
        // Verifica si el índice es válido
        if (id >= 0 && id < misEmpleados.size()) {
            return misEmpleados.get(id);
        } else {
            // Si el índice es inválido, puedes manejarlo como desees. Por ejemplo, lanzar una excepción o devolver un valor predeterminado.
            throw new EmpleadoNoEncontradoExcepcion("El Id " + empleadoId + " no existe. Empleado no encontrado");
        }
    }
    // método que devuelve una clase ResponseEntity que representa toda la respuesta http( encabezado, cuerpo, estado,..)
    // ResponseEntity devuelve en formato JSON el error. Para el estado y la hora se utiliza la clase EmpleadoRespuestaError y para 
    // la excepción personalizada EmpleadoNoEncontradoExcepcion
    @ExceptionHandler
    public ResponseEntity<EmpleadoRespuestaError> manejoExcepcion(EmpleadoNoEncontradoExcepcion ex){
        EmpleadoRespuestaError error = new EmpleadoRespuestaError();
        error.setEstado(HttpStatus.NOT_FOUND.value());
        error.setMensaje(ex.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    } 
    
    /*
    control de excepciones sin crear clase para manejar excepción personalizada. No requiere anotaciones adicionales, clases ni métodos.
    @GetMapping("/empleados/{empleadoId}")
    public Empleado getEmpleadoId(@PathVariable int empleadoId) {
        // Verifica si el índice es válido
        if (empleadoId >= 0 && empleadoId < misEmpleados.size()) {
            return misEmpleados.get(empleadoId);
        } else {
            // Si el índice es inválido, puedes manejarlo como desees. Por ejemplo, lanzar una excepción o devolver un valor predeterminado.
            throw new IllegalArgumentException("ID de empleado no válido: " + empleadoId);
        }
    }
    */
    

}
