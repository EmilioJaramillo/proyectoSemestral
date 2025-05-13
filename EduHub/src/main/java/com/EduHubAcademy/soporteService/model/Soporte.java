package com.EduHubAcademy.soporteService.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "servicio_soporte")
@Data
public class Soporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private String estado;

}
