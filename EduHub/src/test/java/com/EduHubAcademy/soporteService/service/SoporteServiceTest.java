package com.EduHubAcademy.soporteService.service;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.EduHubAcademy.soporteService.model.Soporte;
import com.EduHubAcademy.soporteService.repository.SoporteRepository;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class SoporteServiceTest {

    @Mock
    private SoporteRepository soporteRepository;

    @InjectMocks
    private SoporteService soporteService;

    @Test
    void testGetSoporteById() {
        Soporte soporte = new Soporte(1L, "Problema con login", "Abierto", LocalDate.now());
        when(soporteRepository.findById(1L)).thenReturn(Optional.of(soporte));
        Soporte result = soporteService.getSoporteById(1L);
        assertNotNull(result);
        assertEquals("Problema con login", result.getDescripcion());
        verify(soporteRepository).findById(1L);
    }

    @Test
    void testSaveSoporte() {
        Soporte soporte = new Soporte(null, "Error en página", "Nuevo", LocalDate.now());
        when(soporteRepository.save(soporte)).thenReturn(soporte);

        Soporte result = soporteService.saveSoporte(soporte);

        assertNotNull(result);
        verify(soporteRepository).save(soporte);
    }
}