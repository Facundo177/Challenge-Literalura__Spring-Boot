package com.alura.literatura.repository;

import com.alura.literatura.model.Autor;
import com.alura.literatura.model.Idioma;
import com.alura.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    Boolean existsByTitulo(String titulo);
    Boolean existsBy();
    Optional<Libro> findByTitulo(String titulo);
    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);
    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> librosPorIdioma(Idioma idioma);
}
