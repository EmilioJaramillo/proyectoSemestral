package com.EduHubAcademy.asignaturaService.feignClient;

import com.EduHubAcademy.asignaturaService.config.FeignConfig;
import com.EduHubAcademy.asignaturaService.dto.DocenteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "docente-service", url = "http://localhost:8082", configuration = FeignConfig.class)
public interface DocenteClient {
    @GetMapping("/docente/{id}")
    DocenteDto getDocente(@PathVariable Long id);
}




