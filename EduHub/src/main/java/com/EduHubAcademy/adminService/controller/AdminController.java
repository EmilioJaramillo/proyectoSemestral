package com.EduHubAcademy.adminService.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;

import com.EduHubAcademy.adminService.model.Admin;
import com.EduHubAcademy.adminService.service.ServiceAdmin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/admins")
@Tag(name = "Administradores", description = "Operaciones relacionadas con administradores")
public class AdminController {

    @Autowired
    private ServiceAdmin adminService;

    @Operation(summary = "Obtener todos los administradores", description = "Devuelve una lista de todos los administradores")
    @ApiResponse(responseCode = "200", description = "Lista de administradores obtenida correctamente")
    @GetMapping("/traer")
    public CollectionModel<EntityModel<Admin>> getAllAdmins() {
        List<EntityModel<Admin>> admins = adminService.getAllAdmins().stream()
            .map(admin -> EntityModel.of(admin,
                linkTo(methodOn(AdminController.class).getAdmin(admin.getId())).withSelfRel(),
                linkTo(methodOn(AdminController.class).getAllAdmins()).withRel("admins")))
            .collect(Collectors.toList());
        return CollectionModel.of(admins,
            linkTo(methodOn(AdminController.class).getAllAdmins()).withSelfRel());
    }

    @Operation(summary = "Obtener admin por ID", description = "Devuelve un administrador según su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Admin encontrado"),
        @ApiResponse(responseCode = "404", description = "Admin no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<Admin> getAdmin(
            @Parameter(description = "ID del admin a buscar") @PathVariable Long id) {
        Admin admin = adminService.getAdminById(id);
        return EntityModel.of(admin,
            linkTo(methodOn(AdminController.class).getAdmin(id)).withSelfRel(),
            linkTo(methodOn(AdminController.class).getAllAdmins()).withRel("admins"));
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