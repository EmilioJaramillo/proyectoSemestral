package com.EduHubAcademy.adminService.model;


import jakarta.persistence.*;
import lombok.*;



@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "service_admin")

public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private  String nombre;
    private String apellido;
    private String correo;
    private String contrasenia;






}
