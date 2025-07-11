package com.EduHubAcademy.estudianteService.feignClient;

import com.EduHubAcademy.asignaturaService.config.FeignConfig;
import com.EduHubAcademy.asignaturaService.dto.AsignaturaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "asignatura-service", url = "http://localhost:8080", configuration = FeignConfig.class)
public interface AsignaturaClient {


        @GetMapping("/asignaturas/{id}")
        AsignaturaDto getAsignatura(@PathVariable Long id);
    }

