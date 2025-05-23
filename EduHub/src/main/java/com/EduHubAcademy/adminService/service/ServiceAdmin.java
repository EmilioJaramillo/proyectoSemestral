package com.EduHubAcademy.adminService.service;

import com.EduHubAcademy.adminService.model.Admin;
import com.EduHubAcademy.adminService.repository.AdminRepository;
import com.EduHubAcademy.estudianteService.model.Estudiante;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceAdmin implements IAdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
    @Override
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }
    @Override
    public Admin saveAdmin(Admin admin) {
        //llamo a la funcion de validacion
        validarAdmin(admin, false);
        return adminRepository.save(admin);
    }
    @Override
    public void deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new IllegalArgumentException("No se encontró un admin con el ID proporcionado.");
        }
        adminRepository.deleteById(id);
    }

    @Override
    public void editAdmin(@NotNull Long id, Admin admin) {
        if (admin.getId() == null || !adminRepository.existsById(admin.getId())) {
            throw new IllegalArgumentException("El admin que intenta editar no existe.");
        }
        validarAdmin(admin, true);

        this.saveAdmin(admin);
    }
    @Override
    public Admin findAdmin(Long id) {
        return adminRepository.findById(id).orElse(null);

    }

    private void validarAdmin(@NotNull Admin admin, boolean esEdicion) {

        //valido que el campo nombre no esté vacío.
        if (admin.getNombre() == null || admin.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del admin es obligatorio");
        }

        //valido que el campo apellido no este vacio
        if(admin.getApellido() == null || admin.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido del admin es obligatorio");
        }
        //valido que el campo contrasenia no este vacio
        if(admin.getContrasenia() == null || admin.getContrasenia().trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña del admin es obligatorio");
        }

        //valido que el correo ingresado este en formato de correos
        if (admin.getCorreo() == null || !admin.getCorreo().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("El email ingresado no tiene un formato válido");
        }

        // guardo en una variable auxiliar de tipo booleano el resultado de la validacion.
        boolean correoRepetido = adminRepository.existsByCorreo(admin.getCorreo());

// Si es edición, permitira el mismo correo  pero debe  pertenecer al mismo estudiante
        if (correoRepetido) {
            Estudiante existente = adminRepository.findByCorreo(admin.getCorreo());
            if (!esEdicion || (existente != null && !existente.getId().equals(admin.getId()))) {
                throw new IllegalArgumentException("Intenta con otro correo, ya existe un admin con ese email");
            }
        }
    }


}
