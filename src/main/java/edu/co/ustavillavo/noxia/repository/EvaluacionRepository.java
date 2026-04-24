package edu.co.ustavillavo.noxia.repository;

import edu.co.ustavillavo.noxia.model.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Long> {
    List<Evaluacion> findByCedula(String cedula);
    List<Evaluacion> findByNombreContainingIgnoreCase(String nombre);
}