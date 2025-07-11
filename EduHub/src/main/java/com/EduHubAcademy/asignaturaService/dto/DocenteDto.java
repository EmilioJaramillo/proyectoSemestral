package com.EduHubAcademy.asignaturaService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocenteDto {

    private Long  docenteId;
    private String nombre;
    private String apellido;
    private String correo;
    private String especialidad;
    private boolean activo;
}
