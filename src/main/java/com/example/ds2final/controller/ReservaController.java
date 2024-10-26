package com.example.ds2final.controller;

import com.example.ds2final.model.Reservacion;
import com.example.ds2final.service.ReservacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservaciones")
public class ReservaController {
    @Autowired
    private ReservacionService reservacionService;
    @PostMapping
    public ResponseEntity<Reservacion> crearReservacion(@RequestBody Reservacion reservacion) {
        Reservacion reservacionGuardada = reservacionService.crearReservacion(reservacion);
        return new ResponseEntity<>(reservacionGuardada, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Reservacion>> obtenerTodasLasReservaciones() {
        List<Reservacion> reservas = reservacionService.obtenerReservaciones();
        return ResponseEntity.ok(reservas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Reservacion> obtenerReservacionPorId(@PathVariable Long id) {
        Reservacion reservacion = reservacionService.obtenerReservacion(id);
        if (reservacion != null) {
            return ResponseEntity.ok(reservacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
