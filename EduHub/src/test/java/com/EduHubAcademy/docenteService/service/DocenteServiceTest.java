package com.EduHubAcademy.docenteService.service;

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

import com.EduHubAcademy.docenteService.model.Docente;
import com.EduHubAcademy.docenteService.repository.DocenteRepository;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class DocenteServiceTest {

    @Mock
    private DocenteRepository docenteRepository;

    @InjectMocks
    private DocenteService docenteService;

    @Test
    void testGetDocenteById() {
        Docente docente = new Docente(1L, "Juan", "Pérez", "juan@mail.com", "Matemáticas");
        when(docenteRepository.findById(1L)).thenReturn(Optional.of(docente));
        Docente result = docenteService.getDocenteById(1L);
        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(docenteRepository).findById(1L);
    }

    @Test
    void testSaveDocente() {
        Docente docente = new Docente(null, "Ana", "García", "ana@mail.com", "Historia");
        when(docenteRepository.existsByCorreo(docente.getCorreo())).thenReturn(false);
        when(docenteRepository.save(docente)).thenReturn(docente);

        Docente result = docenteService.saveDocente(docente);

        assertNotNull(result);
        verify(docenteRepository).save(docente);
    }

    // Testear obtener todos los docentes
@Test
void testGetAllDocentes() {
    List<Docente> docentes = Arrays.asList(
        new Docente(1L, "Juan", "Pérez", "juan@mail.com", "Matemáticas"),
        new Docente(2L, "Ana", "García", "ana@mail.com", "Historia")
    );
    when(docenteRepository.findAll()).thenReturn(docentes);

    List<Docente> result = docenteService.getAllDocentes();

    assertEquals(2, result.size());
    assertEquals("Juan", result.get(0).getNombre());
}

// Testear caso de no encontrado (get por ID)
@Test
void testGetDocenteByIdNotFound() {
    when(docenteRepository.findById(99L)).thenReturn(Optional.empty());
    Docente result = docenteService.getDocenteById(99L);
    assertNull(result);
}
}