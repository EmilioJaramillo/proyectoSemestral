package com.EduHubAcademy.estudianteService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AsignaturaDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Long docenteId;
    private String url_contenido;
    private LocalDate fechaCreacion;
    private int cupoMaximo;
    private int inscritos = 0;
}
