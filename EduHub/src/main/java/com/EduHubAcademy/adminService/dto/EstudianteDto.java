package com.EduHubAcademy.adminService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteDto {
    private Long idEstudiante;
    private String nombre;
    private String apellido;
    private String correo;
    private String carrera;
}
