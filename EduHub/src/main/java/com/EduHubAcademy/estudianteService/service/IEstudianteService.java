package com.EduHubAcademy.estudianteService.service;

import com.EduHubAcademy.estudianteService.model.Estudiante;

import java.util.List;

public interface IEstudianteService {

    // listando estudiantes
    public List<Estudiante> getAllEstudiantes();

    public Estudiante getEstudianteById(Long id);

    // guardando las estudiantes
    public Estudiante saveEstudiante(Estudiante estudiante);

    // borrando estudiante
    public void deleteEstudiante(Long id);

    // editando estudiantes
    public void editEstudiante(Long id, Estudiante estudiante);

    Estudiante findEstudiante(Long id);
}
