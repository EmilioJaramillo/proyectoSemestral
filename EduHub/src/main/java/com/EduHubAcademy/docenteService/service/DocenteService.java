package com.EduHubAcademy.docenteService.service;



import com.EduHubAcademy.docenteService.model.Docente;
import com.EduHubAcademy.docenteService.repository.DocenteRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocenteService implements IDocenteService{

    @Autowired
    private DocenteRepository docenteRepository;

    @Override
    public List<Docente> getAllDocentes() {
        return docenteRepository.findAll();
    }
    @Override
    public Docente getDocenteById(Long id) {
        return docenteRepository.findById(id).orElse(null);
    }
    @Override
    public Docente saveDocente(Docente docente) {

        //llamo a la funcion de validacion
        validarDocente(docente, false);

        //en caso que todo salga bien se guardara el nuevo
        return docenteRepository.save(docente);
    }
    @Override
    public void deleteDocente(Long id) {
        if (!docenteRepository.existsById(id)) {
            throw new IllegalArgumentException("No se encontró un docente con el ID proporcionado.");
        }
         docenteRepository.deleteById(id);
    }
    @Override
    public void editDocente(@NotNull Long id, Docente docente) {
        if (docente.getId() == null || !docenteRepository.existsById(docente.getId())) {
            throw new IllegalArgumentException("El docente que intenta editar no existe.");
        }
        validarDocente(docente, true);

        this.saveDocente(docente);
    }
    @Override
    public Docente findDocente(Long id) {
        return docenteRepository.findById(id).orElse(null);

    }


    private void validarDocente(@NotNull Docente docente, boolean esEdicion) {

        //valido que el campo nombre no esté vacío.
        if (docente.getNombre() == null || docente.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del docente es obligatorio");
        }

        //valido que el campo apellido no este vacio
        if(docente.getApellido() == null || docente.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido del estudiante es obligatorio");
        }

        //valido que el correo ingresado este en formato de correos
        if (docente.getCorreo() == null || !docente.getCorreo().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("El email ingresado no tiene un formato válido");
        }

        // guardo en una variable auxiliar de tipo booleano el resultado de la validacion.
        boolean correoRepetido = docenteRepository.existsByCorreo(docente.getCorreo());

// Si es edición, permitira el mismo correo  pero debe  pertenecer al mismo docente
        if (correoRepetido) {
            Docente existente = docenteRepository.findByCorreo(docente.getCorreo());
            if (!esEdicion || (existente != null && !existente.getId().equals(docente.getId()))) {
                throw new IllegalArgumentException("Intenta con otro correo, ya existe un docente con ese email");
            }
        }
    }

}
