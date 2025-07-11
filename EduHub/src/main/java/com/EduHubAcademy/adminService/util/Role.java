package com.EduHubAcademy.adminService.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor

public enum Role {
Admin(Arrays.asList(Permiso.getAllAsignaturas, Permiso.getAsignatura, Permiso.saveAsignatura, Permiso.deleteAsignatura, Permiso.editAsignatura)),
Docente(Arrays.asList(Permiso.getAllAsignaturas, Permiso.getAsignatura, Permiso.editAsignatura)),
Estudiante(Arrays.asList(Permiso.getAllAsignaturas, Permiso.getAsignatura));

    private List<Permiso> permisos;





}
