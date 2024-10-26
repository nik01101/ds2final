package com.example.ds2final.repository;

import com.example.ds2final.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion,Long> {
    @Query("SELECT h FROM Habitacion h WHERE h.estado = 'LIBRE'")
    List<Habitacion> findDisponibles();
    Optional<Habitacion> findByNro(String nro);
    Optional<Habitacion> findById(Long id);
}
