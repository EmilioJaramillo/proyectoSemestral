package com.EduHubAcademy.asignaturaService.service;

import com.EduHubAcademy.asignaturaService.model.Asignatura;
import com.EduHubAcademy.asignaturaService.repository.AsignaturaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class AsignaturaServiceTest {

    @Mock
    private AsignaturaRepository asignaturaRepository;

    @InjectMocks
    private AsignaturaService asignaturaService;

    @Test
    void testGetAsignaturaById() {
        Asignatura asignatura =    new Asignatura(2L, "Matematica", "Desc", 3L, "url2", LocalDate.of(2025, 6, 10),30,10);
        when(asignaturaRepository.findById(1L)).thenReturn(Optional.of(asignatura));

        Asignatura result = asignaturaService.getAsignaturaById(1L);

        assertNotNull(result);
        assertEquals("Matemáticas", result.getNombre());
        verify(asignaturaRepository).findById(1L);
    }

    @Test
    void testSaveAsignatura() {
        Asignatura asignatura = new Asignatura(2L, "Historia", "Desc", 3L, "url2", LocalDate.of(2025, 6, 10),30,10);
        when(asignaturaRepository.save(asignatura)).thenReturn(asignatura);

        Asignatura result = asignaturaService.saveAsignatura("Historia",3L);

        assertNotNull(result);
        verify(asignaturaRepository).save(asignatura);
    }

    @Test
    void testGetAllAsignaturas() {
        List<Asignatura> asignaturas = Arrays.asList(
                new Asignatura(1L, "Matemáticas", "Desc", 2L, "url", LocalDate.of(2025, 6, 10),30,6),
                new Asignatura(2L, "Historia", "Desc", 3L, "url2", LocalDate.of(2025, 6, 10),30,10)
        );
        when(asignaturaRepository.findAll()).thenReturn(asignaturas);

        List<Asignatura> result = asignaturaService.getAllAsignaturas();

        assertEquals(2, result.size());
        assertEquals("Matemáticas", result.get(0).getNombre());
    }

    @Test
    void testGetAsignaturaByIdNotFound() {
        when(asignaturaRepository.findById(99L)).thenReturn(Optional.empty());

        Asignatura result = asignaturaService.getAsignaturaById(99L);

        assertNull(result);
    }
}
