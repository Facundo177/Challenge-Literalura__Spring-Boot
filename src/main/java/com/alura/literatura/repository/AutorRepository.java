package com.alura.literatura.repository;

import com.alura.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Boolean existsByNombre(String nombre);
    Boolean existsBy();
    Optional<Autor> findByNombre(String nombre);
    Optional<Autor> findByNombreContainsIgnoreCase(String nombre);
    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento < :numero AND a.fechaMuerte > :numero")
    List<Autor> autoresVivosEnUnAno(int numero);

}
