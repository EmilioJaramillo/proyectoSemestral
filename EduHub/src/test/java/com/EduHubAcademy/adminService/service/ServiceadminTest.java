package com.EduHubAcademy.adminService.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.EduHubAcademy.adminService.model.Admin;
import com.EduHubAcademy.adminService.repository.AdminRepository;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class ServiceadminTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private ServiceAdmin serviceAdmin;

    @Test
    void testGetAdminById() {
        Admin admin = new Admin(1L, "Nombre", "Apellido", "correo@mail.com", "pass");
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        Admin result = serviceAdmin.getAdminById(1L);
        assertNotNull(result);
        assertEquals("Nombre", result.getNombre());
        verify(adminRepository).findById(1L);
    }

    @Test
    void testSaveAdmin() {
        Admin admin = new Admin(null, "Nombre", "Apellido", "correo@mail.com", "pass");
        when(adminRepository.existsByCorreo(admin.getCorreo())).thenReturn(false);
        when(adminRepository.save(admin)).thenReturn(admin);

        Admin result = serviceAdmin.saveAdmin(admin);

        assertNotNull(result);
        verify(adminRepository).save(admin);
    }
}