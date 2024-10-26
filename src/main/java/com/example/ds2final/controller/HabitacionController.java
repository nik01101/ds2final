package com.example.ds2final.controller;


import com.example.ds2final.model.Habitacion;
import com.example.ds2final.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/habitaciones")
public class HabitacionController {
    @Autowired
    private HabitacionService habitacionService;
    @GetMapping
    public ResponseEntity<List<Habitacion>> obtenerHabitaciones() {
        List<Habitacion> habitaciones = habitacionService.obtenerHabitaciones();
        return ResponseEntity.ok(habitaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> obtenerHabitacionPorId(@PathVariable Long id) {
        Optional<Habitacion> habitacion = habitacionService.obtenerHabitacionPorId(id);
        return habitacion != null ? ResponseEntity.ok(habitacion.get()) : ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<Habitacion> crearHabitacion(@RequestBody Habitacion habitacion) {
        Habitacion habitacionGuardar = habitacionService.crearHabitacion(habitacion);
        return  ResponseEntity.status(HttpStatus.CREATED).body(habitacionGuardar);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Habitacion> actualizarHabitacion(@RequestBody Habitacion habitacion, @PathVariable Long id) {
        Optional<Habitacion> habitacionOptional = habitacionService.actualizarHabitacion(id, habitacion);
        return habitacionOptional
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }
    @PutMapping("/{id}/estado")
    public ResponseEntity<Habitacion> actualizarEstadoHabitacion(@PathVariable Long id, @RequestBody String nuevoEstado) {
        Habitacion habitacionActualizada = habitacionService.actualizarEstado(id, nuevoEstado);

        return ResponseEntity.ok(habitacionActualizada);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarHabitacion(@PathVariable Long id) {
        habitacionService.eliminarHabitacion(id);

        return ResponseEntity.noContent().build();
    }
}
