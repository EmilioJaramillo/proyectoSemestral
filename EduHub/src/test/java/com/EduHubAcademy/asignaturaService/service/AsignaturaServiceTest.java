package com.EduHubAcademy.asignaturaService.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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

    // Testear obtener una asignatura por ID
    @Test
    void testGetAsignaturaById() {
        Asignatura asignatura = new Asignatura(1L, "Matemáticas", "Descripción", "url");
        when(asignaturaRepository.findById(1L)).thenReturn(Optional.of(asignatura));
        Asignatura result = asignaturaService.getAsignaturaById(1L);
        assertNotNull(result);
        assertEquals("Matemáticas", result.getNombre());
        verify(asignaturaRepository).findById(1L);
    }
    // Testear guardar una asignatura
    @Test
    void testSaveAsignatura() {
        Asignatura asignatura = new Asignatura(null, "Historia", "Descripción", "url");
        when(asignaturaRepository.save(asignatura)).thenReturn(asignatura);
        Asignatura result = asignaturaService.saveAsignatura(asignatura);
        assertNotNull(result);
        verify(asignaturaRepository).save(asignatura);
    }

    // Testear obtener todas las asignaturas
@Test
void testGetAllAsignaturas() {
    List<Asignatura> asignaturas = Arrays.asList(
        new Asignatura(1L, "Matemáticas", "Desc", "url"),
        new Asignatura(2L, "Historia", "Desc", "url2")
    );
    when(asignaturaRepository.findAll()).thenReturn(asignaturas);

    List<Asignatura> result = asignaturaService.getAllAsignaturas();

    assertEquals(2, result.size());
    assertEquals("Matemáticas", result.get(0).getNombre());
}

// Testear caso de no encontrado (get por ID)
@Test
void testGetAsignaturaByIdNotFound() {
    when(asignaturaRepository.findById(99L)).thenReturn(Optional.empty());
    Asignatura result = asignaturaService.getAsignaturaById(99L);
    assertNull(result);
}

}