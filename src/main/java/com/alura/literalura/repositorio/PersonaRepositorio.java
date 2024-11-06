package com.alura.literalura.repositorio;

import com.alura.literalura.modelo.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonaRepositorio extends JpaRepository<Persona, Long> {
    Optional<Persona> findByNombre(String nombreAutor);
    List<Persona> findByFechaMuerteGreaterThanAndFechaNecimientoLessThan(Integer anho2, Integer anho1);
}
