package com.example.ds2final.repository;

import com.example.ds2final.model.Reservacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservacionRepository extends JpaRepository<Reservacion,Long> {
    Reservacion findById(long id);
}
