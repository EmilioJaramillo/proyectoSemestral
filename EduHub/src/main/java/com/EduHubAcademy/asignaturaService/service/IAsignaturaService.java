package com.EduHubAcademy.asignaturaService.service;

import java.util.List;

import com.EduHubAcademy.asignaturaService.model.Asignatura;

public interface IAsignaturaService {

    // listar asignatura
    List<Asignatura> getAllAsignaturas();

    Asignatura getAsignaturaById(Long id);

    // guardando las asignaturas
    Asignatura saveAsignatura(Asignatura asignatura);

    // borrar asignaturas
    void deleteAsignatura(Long id);

    // editar asignaturas
    void editAsignatura(Long id, Asignatura asignatura);

    Asignatura findAsignatura(Long id);
}