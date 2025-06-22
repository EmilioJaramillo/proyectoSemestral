package com.EduHubAcademy.asignaturaService.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.EduHubAcademy.asignaturaService.model.Asignatura;
import com.EduHubAcademy.asignaturaService.repository.AsignaturaRepository;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class AsignaturaServiceTest {

    @Mock
    private AsignaturaRepository asignaturaRepository;

    @InjectMocks
    private AsignaturaService asignaturaService;

    @Test
    void testGetAsignaturaById() {
        Asignatura asignatura = new Asignatura(1L, "Matemáticas", "Descripción", "url");
        when(asignaturaRepository.findById(1L)).thenReturn(Optional.of(asignatura));
        Asignatura result = asignaturaService.getAsignaturaById(1L);
        assertNotNull(result);
        assertEquals("Matemáticas", result.getNombre());
        verify(asignaturaRepository).findById(1L);
    }

    @Test
    void testSaveAsignatura() {
        Asignatura asignatura = new Asignatura(null, "Historia", "Descripción", "url");
        when(asignaturaRepository.save(asignatura)).thenReturn(asignatura);
        Asignatura result = asignaturaService.saveAsignatura(asignatura);
        assertNotNull(result);
        verify(asignaturaRepository).save(asignatura);
    }
}