package com.EduHubAcademy.docenteService.repository;

import com.EduHubAcademy.docenteService.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteRepository extends JpaRepository<Docente, Long> {
    boolean existsByCorreo(String correo);

    Docente findByCorreo(String correo);
}
