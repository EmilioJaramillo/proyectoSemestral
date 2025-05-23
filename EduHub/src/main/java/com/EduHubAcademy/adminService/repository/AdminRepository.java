package com.EduHubAcademy.adminService.repository;

import com.EduHubAcademy.adminService.model.Admin;
import com.EduHubAcademy.estudianteService.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long>{
    boolean existsByCorreo(String correo);

    Estudiante findByCorreo(String correo);
}
