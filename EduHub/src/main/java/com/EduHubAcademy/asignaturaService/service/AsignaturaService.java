package com.EduHubAcademy.asignaturaService.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.EduHubAcademy.asignaturaService.dto.DocenteDto;
import com.EduHubAcademy.asignaturaService.feignClient.DocenteClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.EduHubAcademy.asignaturaService.model.Asignatura;
import com.EduHubAcademy.asignaturaService.repository.AsignaturaRepository;

@Service
public class AsignaturaService  {

    @Autowired
    private AsignaturaRepository asignaturaRepository;
    @Autowired
    private DocenteClient docenteClient;


    public List<Asignatura> getAllAsignaturas() {
        return asignaturaRepository.findAll();
    }


    public Asignatura getAsignaturaById(Long id) {
        return asignaturaRepository.findById(id).orElse(null);
    }


    public Asignatura saveAsignatura(String nombreAsignatura, Long docenteId) {
        DocenteDto docente = docenteClient.getDocente(docenteId);

        if (!docente.isActivo()) {
            throw new IllegalArgumentException("El docente no está activo. No se puede asignar.");
        }

        Asignatura asignatura = new Asignatura();
        asignatura.setNombre(nombreAsignatura);
        asignatura.setDocenteId(docenteId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        asignatura.setFechaCreacion(LocalDate.parse("11/02/2025", formatter));

        return asignaturaRepository.save(asignatura);
    }



    public void deleteAsignatura(Long id) {
        asignaturaRepository.deleteById(id);
    }


    public void editAsignatura(Long id, Asignatura asignatura) {
        this.saveAsignatura(asignatura.getNombre(), asignatura.getDocenteId());
    }


    public Asignatura findAsignatura(Long id) {
        return asignaturaRepository.findById(id).orElse(null);
    }

    private void validarAsignatura(Asignatura asignatura, boolean esEdicion) {
        // valido que el campo nombre de la materia no esté vacío.
        if (asignatura.getNombre() == null || asignatura.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la materia es obligatorio");
        }
        // valido que el campo de descripcion no este vacio
        if (asignatura.getDescripcion() == null || asignatura.getDescripcion().trim().isEmpty()) {
            throw new IllegalArgumentException("La descripcion de la materia es obligatoria");
        }
    }
}

