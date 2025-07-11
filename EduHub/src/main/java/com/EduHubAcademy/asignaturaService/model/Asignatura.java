package com.EduHubAcademy.asignaturaService.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


@Entity
@Table(name = "service_asignatura")
public class Asignatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private Long docenteId;
    private String url_contenido;
    private LocalDate fechaCreacion;
    private int cupoMaximo;
    private int inscritos = 0;




}
