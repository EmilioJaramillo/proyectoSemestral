package com.EduHubAcademy.asignaturaService.service;

import com.EduHubAcademy.asignaturaService.model.Asignatura;
import com.EduHubAcademy.asignaturaService.repository.AsignaturaRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IAsignaturaService {

    // listar asignatura
    public List<Asignatura> getAllAsignaturas();

    public Asignatura getAsignaturaById(Long id);

    // guardando las asignaturas
    public Asignatura saveAsignatura(Asignatura asignatura);

    // borrar asignaturas
    public void deleteAsignatura(Long id);

    // editar asignaturas
    public void editAsignatura(Long id, Asignatura asignatura);

    Asignatura findAsignatura(Long id);

}
