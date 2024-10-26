package com.example.ds2final.service;

import com.example.ds2final.model.Reservacion;
import com.example.ds2final.repository.ReservacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservacionService {
    @Autowired
    private ReservacionRepository reservacionRepository;

    public Reservacion crearReservacion(Reservacion reservacion) {
        return reservacionRepository.save(reservacion);
    }
    public List<Reservacion> obtenerReservaciones(){
        return reservacionRepository.findAll();
    }
    public Reservacion obtenerReservacion(Long id){
        return reservacionRepository.findById(id).orElse(null);
    }
}
