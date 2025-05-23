package com.EduHubAcademy.asignaturaService.service;

import com.EduHubAcademy.asignaturaService.model.Asignatura;
import com.EduHubAcademy.asignaturaService.repository.AsignaturaRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignaturaService implements IAsignaturaService {


    @Autowired
    private AsignaturaRepository asignaturaRepository;
    @Override
    public List<Asignatura> getAllAsignaturas() {
        return asignaturaRepository.findAll();
    }
    @Override
    public Asignatura getAsignaturaById(Long id) {
        return asignaturaRepository.findById(id).orElse(null);
    }
    @Override
    public Asignatura saveAsignatura(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }
    @Override
    public void deleteAsignatura(Long id) {
        asignaturaRepository.deleteById(id);
    }

    @Override
    public void editAsignatura(Long  id, Asignatura asignatura) {
        this.saveAsignatura(asignatura);
    }
    @Override
    public Asignatura findAsignatura(Long id) {
        return asignaturaRepository.findById(id).orElse(null);

    }

    private void validarAsignatura(@NotNull Asignatura asignatura, boolean esEdicion) {

        //valido que el campo nombre de la materia no esté vacío.
        if (asignatura.getNombre() == null || asignatura.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la materia es obligatorio");
        }

        //valido que el campo de descripcion no este vacio
        if (asignatura.getDescripcion() == null || asignatura.getDescripcion().trim().isEmpty()) {
            throw new IllegalArgumentException(" La descripcion de la materia es obligatorio");
        }

    }


}
