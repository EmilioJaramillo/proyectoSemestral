package com.EduHubAcademy.soporteService.controller;

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

import com.EduHubAcademy.soporteService.model.Soporte;
import com.EduHubAcademy.soporteService.service.SoporteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/soportes")
@Tag(name = "Soportes", description = "Operaciones relacionadas con tickets de soporte")
public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    @Operation(summary = "Obtener todos los tickets de soporte", description = "Devuelve una lista de todos los tickets de soporte")
    @ApiResponse(responseCode = "200", description = "Lista de tickets obtenida correctamente")
    @GetMapping("/traer")
    public List<Soporte> getAllSoportes() {
        return soporteService.getAllSoportes();
    }

    @Operation(summary = "Obtener ticket de soporte por ID", description = "Devuelve un ticket de soporte según su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Ticket encontrado"),
        @ApiResponse(responseCode = "404", description = "Ticket no encontrado")
    })
    @GetMapping("/{id}")
    public Soporte getSoporte(
            @Parameter(description = "ID del ticket a buscar") @PathVariable Long id) {
        return soporteService.getSoporteById(id);
    }

    @Operation(summary = "Crear un nuevo ticket de soporte", description = "Guarda un nuevo ticket de soporte en la base de datos")
    @ApiResponse(responseCode = "201", description = "Ticket creado correctamente")
    @PostMapping("/crear")
    public Soporte saveSoporte(
            @Parameter(description = "Objeto ticket a crear") @RequestBody Soporte soporte) {
        return soporteService.saveSoporte(soporte);
    }

    @Operation(summary = "Eliminar un ticket de soporte", description = "Elimina un ticket de soporte por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Ticket eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Ticket no encontrado")
    })
    @DeleteMapping("/borrar/{id}")
    public String deleteSoporte(
            @Parameter(description = "ID del ticket a eliminar") @PathVariable Long id) {
        soporteService.deleteSoporte(id);
        return "El ticket fue eliminado correctamente";
    }

    @Operation(summary = "Editar un ticket de soporte", description = "Edita los datos de un ticket de soporte existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Ticket editado correctamente"),
        @ApiResponse(responseCode = "404", description = "Ticket no encontrado")
    })
    @PutMapping("/editar")
    public Soporte editSoporte(
            @Parameter(description = "ID del ticket a editar") @RequestParam Long id,
            @Parameter(description = "Objeto ticket con los nuevos datos") @RequestBody Soporte soporte) {
        soporteService.editSoporte(id, soporte);
        return soporteService.findSoporte(id);
    }
}