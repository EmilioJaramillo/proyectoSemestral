package com.EduHubAcademy.adminService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EduHubAcademy.adminService.model.Admin;
import com.EduHubAcademy.adminService.service.ServiceAdmin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/admins")
@Tag(name = "Administradores", description = "Operaciones relacionadas con administradores")
public class AdminController {

    @Autowired
    private ServiceAdmin adminService;

    @Operation(summary = "Obtener todos los administradores", description = "Devuelve una lista de todos los administradores")
    @ApiResponse(responseCode = "200", description = "Lista de administradores obtenida correctamente")
    @GetMapping("/traer")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @Operation(summary = "Obtener admin por ID", description = "Devuelve un administrador según su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Admin encontrado"),
        @ApiResponse(responseCode = "404", description = "Admin no encontrado")
    })
    @GetMapping("/{id}")
    public Admin getAdmin(
            @Parameter(description = "ID del admin a buscar") @PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    @Operation(summary = "Crear un nuevo administrador", description = "Guarda un nuevo administrador en la base de datos")
    @ApiResponse(responseCode = "201", description = "Admin creado correctamente")
    @PostMapping("/crear")
    public Admin saveAdmin(
            @Parameter(description = "Objeto admin a crear") @RequestBody Admin admin) {
        return adminService.saveAdmin(admin);
    }

    @Operation(summary = "Eliminar un administrador", description = "Elimina un administrador por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Admin eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Admin no encontrado")
    })
    @DeleteMapping("/borrar/{id}")
    public String deleteAdmin(
            @Parameter(description = "ID del admin a eliminar") @PathVariable Long id) {
        adminService.deleteAdmin(id);
        return "El admin fue eliminado correctamente";
    }

    @Operation(summary = "Editar un administrador", description = "Edita los datos de un administrador existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Admin editado correctamente"),
        @ApiResponse(responseCode = "404", description = "Admin no encontrado")
    })
    @PutMapping("/editar")
    public Admin editAdmin(
            @Parameter(description = "ID del admin a editar") @RequestParam Long id,
            @Parameter(description = "Objeto admin con los nuevos datos") @RequestBody Admin admin) {
        adminService.editAdmin(id, admin);
        return adminService.findAdmin(id);
    }
}