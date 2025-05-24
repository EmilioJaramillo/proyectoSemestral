package com.EduHubAcademy.estudianteService.repository;

import com.EduHubAcademy.estudianteService.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    boolean existsByCorreo (String correo);

    Estudiante findByCorreo(String correo);


}
