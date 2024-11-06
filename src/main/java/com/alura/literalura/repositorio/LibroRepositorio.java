package com.alura.literalura.repositorio;

import com.alura.literalura.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface LibroRepositorio extends JpaRepository <Libro, Long> {

    Optional<Libro> findByTitulo(String titulo);
    List<Libro> findByIdiomaLike(String idioma);
    List<Libro> findTop10ByOrderByNumeroDeDescargasDesc();
}
