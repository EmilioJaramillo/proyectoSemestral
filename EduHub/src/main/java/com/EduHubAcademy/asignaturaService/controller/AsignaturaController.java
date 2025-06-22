package com.EduHubAcademy.asignaturaService.controller;

import com.EduHubAcademy.asignaturaService.model.Asignatura;
import com.EduHubAcademy.asignaturaService.service.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@RestController
@RequestMapping("/cursos")
@Tag(name = "Asignaturas", description = "Operaciones relacionadas con asignaturas")
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    @Operation(summary = "Obtener todas las asignaturas", description = "Devuelve una lista de todas las asignaturas")
    @ApiResponse(responseCode = "200", description = "Lista de asignaturas obtenida correctamente")
    @GetMapping("/traer")
    public List<Asignatura> getAllAsignaturas() {
        return asignaturaService.getAllAsignaturas();
    }

    @Operation(summary = "Obtener asignatura por ID", description = "Devuelve una asignatura según su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Asignatura encontrada"),
        @ApiResponse(responseCode = "404", description = "Asignatura no encontrada")
    })
    @GetMapping("/{id}")
    public Asignatura getAsignatura(
            @Parameter(description = "ID de la asignatura a buscar") @PathVariable Long id) {
        return asignaturaService.getAsignaturaById(id);
    }

    @Operation(summary = "Crear una nueva asignatura", description = "Guarda una nueva asignatura en la base de datos")
    @ApiResponse(responseCode = "201", description = "Asignatura creada correctamente")
    @PostMapping("/crear")
    public Asignatura saveAsignatura(
            @Parameter(description = "Objeto asignatura a crear") @RequestBody Asignatura asignatura) {
        return asignaturaService.saveAsignatura(asignatura);
    }

    @Operation(summary = "Eliminar una asignatura", description = "Elimina una asignatura por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Asignatura eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Asignatura no encontrada")
    })
    @DeleteMapping("/borrar/{id}")
    public String deleteAsignatura(
            @Parameter(description = "ID de la asignatura a eliminar") @PathVariable Long id) {
        asignaturaService.deleteAsignatura(id);
        return "El Asignatura fue eliminado correctamente";
    }

    @Operation(summary = "Editar una asignatura", description = "Edita los datos de una asignatura existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Asignatura editada correctamente"),
        @ApiResponse(responseCode = "404", description = "Asignatura no encontrada")
    })
    @PutMapping("/editar")
    public Asignatura editAsignatura(
            @Parameter(description = "ID de la asignatura a editar") @RequestParam Long id,
            @Parameter(description = "Objeto asignatura con los nuevos datos") @RequestBody Asignatura asignatura) {
        asignaturaService.editAsignatura(id, asignatura);
        return asignaturaService.findAsignatura(id);
    }
}