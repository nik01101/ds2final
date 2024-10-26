package com.example.ds2final.controller;
import com.example.ds2final.model.Habitacion;
import com.example.ds2final.model.Reservacion;
import com.example.ds2final.service.HabitacionService;
import com.example.ds2final.service.ReservacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/procesar-reservacion")
public class ProcesadorController {
    @Autowired
    private HabitacionService habitacionService;

    @Autowired
    private ReservacionService reservacionService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> procesarReservacion(@RequestBody Map<String, Object> request) {
        Map<String, Object> reservacionData = (Map<String, Object>) request.get("reservacion");


        String fecha = (String) reservacionData.get("fecha");
        Map<String, Object> habitacionData = (Map<String, Object>) reservacionData.get("habitacion");
        String nro = (String) habitacionData.get("nro");
        String estado = (String) habitacionData.get("estado");
        Double costo = (Double) reservacionData.get("costo");

        Habitacion habitacion = habitacionService.actualizarEstadoPorNumero(nro, estado);

        Reservacion reservacion = new Reservacion();
        reservacion.setFecha(LocalDateTime.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        reservacion.setHabitacion(habitacion);
        reservacion.setCosto(costo);
        reservacionService.crearReservacion(reservacion);

        Map<String, Object> response = new HashMap<>();
        response.put("estado", "EN PROCESO");
        response.put("detalles", request);

        return ResponseEntity.ok(response);
    }
}
