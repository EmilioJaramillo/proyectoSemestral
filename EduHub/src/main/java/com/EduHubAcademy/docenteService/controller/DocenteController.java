package com.EduHubAcademy.docenteService.controller;

import com.EduHubAcademy.docenteService.model.Docente;
import com.EduHubAcademy.docenteService.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/docentes")
@Tag(name = "Docentes", description = "Operaciones relacionadas con docentes")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @Operation(summary = "Obtener todos los docentes", description = "Devuelve una lista de todos los docentes")
    @ApiResponse(responseCode = "200", description = "Lista de docentes obtenida correctamente")
    @GetMapping("/traer")
    public List<Docente> getAllDocentes() {
        return docenteService.getAllDocentes();
    }

    @Operation(summary = "Obtener docente por ID", description = "Devuelve un docente según su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Docente encontrado"),
        @ApiResponse(responseCode = "404", description = "Docente no encontrado")
    })
    @GetMapping("/{id}")
    public Docente getDocente(
            @Parameter(description = "ID del docente a buscar") @PathVariable Long id) {
        return docenteService.getDocenteById(id);
    }

    @Operation(summary = "Crear un nuevo docente", description = "Guarda un nuevo docente en la base de datos")
    @ApiResponse(responseCode = "201", description = "Docente creado correctamente")
    @PostMapping("/crear")
    public Docente saveDocente(
            @Parameter(description = "Objeto docente a crear") @RequestBody Docente docente) {
        return docenteService.saveDocente(docente);
    }

    @Operation(summary = "Eliminar un docente", description = "Elimina un docente por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Docente eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Docente no encontrado")
    })
    @DeleteMapping("/borrar/{id}")
    public String deleteDocente(
            @Parameter(description = "ID del docente a eliminar") @PathVariable Long id) {
        docenteService.deleteDocente(id);
        return "El Docente fue eliminado correctamente";
    }

    @Operation(summary = "Editar un docente", description = "Edita los datos de un docente existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Docente editado correctamente"),
        @ApiResponse(responseCode = "404", description = "Docente no encontrado")
    })
    @PutMapping("/editar")
    public Docente editDocente(
            @Parameter(description = "ID del docente a editar") @RequestParam Long id,
            @Parameter(description = "Objeto docente con los nuevos datos") @RequestBody Docente docente) {
        docenteService.editDocente(id, docente);
        return docenteService.findDocente(id);
    }
}