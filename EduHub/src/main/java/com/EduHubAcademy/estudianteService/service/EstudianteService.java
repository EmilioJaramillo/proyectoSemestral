package com.EduHubAcademy.estudianteService.service;

import com.EduHubAcademy.asignaturaService.dto.AsignaturaDto;
import com.EduHubAcademy.estudianteService.feignClient.AsignaturaClient;
import com.EduHubAcademy.estudianteService.model.Estudiante;
import com.EduHubAcademy.estudianteService.model.Inscripcion;
import com.EduHubAcademy.estudianteService.repository.EstudianteRepository;
import com.EduHubAcademy.estudianteService.repository.InscripcionRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EstudianteService  {


    @Autowired
    private EstudianteRepository estudianteRepository;


    @Autowired
    private AsignaturaClient asignaturaClient;

    @Autowired
    private InscripcionRepository inscripcionRepository;

    public Inscripcion inscribirEstudiante(Long estudianteId, Long asignaturaId) {
        AsignaturaDto asignatura = asignaturaClient.getAsignatura(asignaturaId);

        if (asignatura.getInscritos() >= asignatura.getCupoMaximo()) {
            throw new IllegalStateException("No hay cupos disponibles para esta asignatura.");
        }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEstudianteId(estudianteId);
        inscripcion.setAsignaturaId(asignaturaId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        inscripcion.setFechaInscripcion(LocalDate.parse("10/03/2025", formatter));

        return inscripcionRepository.save(inscripcion);
    }

    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    public Estudiante getEstudianteById(Long id) {
        return estudianteRepository.findById(id).orElse(null);
    }


    public Estudiante saveEstudiante (Estudiante estudiante) {

        //llamo a la funcion de validacion
        validarEstudiante(estudiante, false);

        //en caso que todo salga bien se guardara el nuevo
        return estudianteRepository.save(estudiante);
    }

    public void deleteEstudiante(Long id) {
        if (!estudianteRepository.existsById(id)) {
            throw new IllegalArgumentException("No se encontró un estudiante con el ID proporcionado.");
        }
         estudianteRepository.deleteById(id);
    }

    public void editEstudiante(@NotNull Long id, @NotNull Estudiante estudiante) {
        if (estudiante.getId() == null || !estudianteRepository.existsById(estudiante.getId())) {
            throw new IllegalArgumentException("El estudiante que intenta editar no existe.");
        }
        validarEstudiante(estudiante, true);


        this.saveEstudiante(estudiante);
    }

    public Estudiante findEstudiante(Long id) {
        return estudianteRepository.findById(id).orElse(null);

    }

    private void validarEstudiante(@NotNull Estudiante estudiante, boolean esEdicion) {

        //valido que el campo nombre no esté vacío.
        if (estudiante.getNombre() == null || estudiante.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del estudiante es obligatorio");
        }

        //valido que el campo apellido no este vacio
        if(estudiante.getApellido() == null || estudiante.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido del estudiante es obligatorio");
        }

        //valido que el correo ingresado este en formato de correos
        if (estudiante.getCorreo() == null || !estudiante.getCorreo().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("El email ingresado no tiene un formato válido");
        }

        // guardo en una variable auxiliar de tipo booleano el resultado de la validacion.
        boolean correoRepetido = estudianteRepository.existsByCorreo(estudiante.getCorreo());

// Si es edición, permitira el mismo correo  pero debe  pertenecer al mismo estudiante
        if (correoRepetido) {
            Estudiante existente = estudianteRepository.findByCorreo(estudiante.getCorreo());
            if (!esEdicion || (existente != null && !existente.getId().equals(estudiante.getId()))) {
                throw new IllegalArgumentException("Intenta con otro correo, ya existe un estudiante con ese email");
            }
        }
    }


}
