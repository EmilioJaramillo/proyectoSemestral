package com.EduHubAcademy.docenteService.service;

import com.EduHubAcademy.docenteService.model.Docente;

import java.util.List;

public interface IDocenteService {
    // listar docentes
    public List<Docente> getAllDocentes();

    public Docente getDocenteById(Long id);

    // guardando las docentes
    public Docente saveDocente(Docente docente);

    // borrar docente
    public void deleteDocente(Long id);

    // editar docente
    public void editDocente(Long id, Docente docente);

    Docente findDocente(Long id);
}
