package com.EduHubAcademy.adminService.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.EduHubAcademy.adminService.dto.EstudianteDto;

//Conexion entre admin-estudiante
@FeignClient(name = "estudiante-service")
    public interface EstudianteClient {

        @GetMapping("/estudiantes/{id}")
        EstudianteDto obtenerEstudiante(@PathVariable("id") Long id);


}
