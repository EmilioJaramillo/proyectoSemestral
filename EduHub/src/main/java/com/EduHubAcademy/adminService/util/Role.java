package com.EduHubAcademy.usuarioService.util;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

public enum Role {
Admin(Arrays.asList(Permiso.getAllAsignaturas, Permiso.getAsignatura, Permiso.saveAsignatura, Permiso.deleteAsignatura, Permiso.editAsignatura)),
Docente(Arrays.asList(Permiso.getAllAsignaturas, Permiso.getAsignatura, Permiso.editAsignatura)),
Estudiante(Arrays.asList(Permiso.getAllAsignaturas, Permiso.getAsignatura));

    private List<Permiso> permisos;





}
