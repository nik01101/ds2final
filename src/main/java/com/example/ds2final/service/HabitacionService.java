package com.example.ds2final.service;

import com.example.ds2final.model.Habitacion;
import com.example.ds2final.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class HabitacionService {
    @Autowired
    private HabitacionRepository habitacionRepository;

    public List<Habitacion> obtenerHabitaciones() {
        return habitacionRepository.findAll();
    }

    public Optional<Habitacion> obtenerHabitacionPorNro(String nro) {
        return habitacionRepository.findByNro(nro);
    }

    public Optional<Habitacion> obtenerHabitacionPorId(Long id) {
        return habitacionRepository.findById(id);
    }

    public Habitacion crearHabitacion(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public void eliminarHabitacion(Long id) {
        habitacionRepository.deleteById(id);
    }

    public Optional<Habitacion> actualizarHabitacion(Long id, Habitacion habitacion) {
        return habitacionRepository.findById(id)
                .map(habitacionExistente -> {
                    habitacionExistente.setNro(habitacion.getNro());
                    return habitacionRepository.save(habitacionExistente);
                });
    }

    public Habitacion actualizarEstado(Long id, String estado) {
        Habitacion habitacion = habitacionRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Habitación no encontrada"));
        habitacion.setEstado(estado);
        return habitacionRepository.save(habitacion);
    }

    public Habitacion actualizarEstadoPorNumero(String nro, String estado) {
        Habitacion habitacion = habitacionRepository.findByNro(nro)
                .orElseThrow(() -> new NoSuchElementException("Habitación no encontrada")); // Igualmente aquí
        habitacion.setEstado(estado);
        return habitacionRepository.save(habitacion);
    }
}