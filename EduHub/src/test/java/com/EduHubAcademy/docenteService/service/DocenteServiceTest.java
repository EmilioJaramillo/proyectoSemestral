package com.EduHubAcademy.docenteService.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
}