package com.EduHubAcademy.estudianteService.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.EduHubAcademy.estudianteService.model.Estudiante;
import com.EduHubAcademy.estudianteService.repository.EstudianteRepository;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class EstudianteServiceTest {

    @Mock
    private EstudianteRepository estudianteRepository;

    @InjectMocks
    private EstudianteService estudianteService;

    @Test
    void testGetEstudianteById() {
        Estudiante estudiante = new Estudiante(1L, "Pedro", "López", "pedro@mail.com", "Ingeniería");
        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(estudiante));
        Estudiante result = estudianteService.getEstudianteById(1L);
        assertNotNull(result);
        assertEquals("Pedro", result.getNombre());
        verify(estudianteRepository).findById(1L);
    }

    @Test
    void testSaveEstudiante() {
        Estudiante estudiante = new Estudiante(null, "Ana", "García", "ana@mail.com", "Derecho");
        when(estudianteRepository.existsByCorreo(estudiante.getCorreo())).thenReturn(false);
        when(estudianteRepository.save(estudiante)).thenReturn(estudiante);

        Estudiante result = estudianteService.saveEstudiante(estudiante);

        assertNotNull(result);
        verify(estudianteRepository).save(estudiante);
    }
}