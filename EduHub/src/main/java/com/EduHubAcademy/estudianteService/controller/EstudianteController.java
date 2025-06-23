package com.EduHubAcademy.estudianteService.controller;

import com.EduHubAcademy.estudianteService.model.Estudiante;
import com.EduHubAcademy.estudianteService.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/estudiantes")
@Tag(name = "Estudiantes", description = "Operaciones relacionadas con estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @Operation(summary = "Obtener todos los estudiantes", description = "Devuelve una lista de todos los estudiantes")
    @ApiResponse(responseCode = "200", description = "Lista de estudiantes obtenida correctamente")
    @GetMapping("/traer")
    public CollectionModel<EntityModel<Estudiante>> getAllEstudiantes() {
        List<EntityModel<Estudiante>> estudiantes = estudianteService.getAllEstudiantes().stream()
            .map(estudiante -> EntityModel.of(estudiante,
                linkTo(methodOn(EstudianteController.class).getEstudiante(estudiante.getId())).withSelfRel(),
                linkTo(methodOn(EstudianteController.class).getAllEstudiantes()).withRel("estudiantes")))
            .collect(Collectors.toList());
        return CollectionModel.of(estudiantes,
            linkTo(methodOn(EstudianteController.class).getAllEstudiantes()).withSelfRel());
    }

    @Operation(summary = "Obtener estudiante por ID", description = "Devuelve un estudiante según su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Estudiante encontrado"),
        @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<Estudiante> getEstudiante(
            @Parameter(description = "ID del estudiante a buscar") @PathVariable Long id) {
        Estudiante estudiante = estudianteService.getEstudianteById(id);
        return EntityModel.of(estudiante,
            linkTo(methodOn(EstudianteController.class).getEstudiante(id)).withSelfRel(),
            linkTo(methodOn(EstudianteController.class).getAllEstudiantes()).withRel("estudiantes"));
    }

    @Operation(summary = "Crear un nuevo estudiante", description = "Guarda un nuevo estudiante en la base de datos")
    @ApiResponse(responseCode = "201", description = "Estudiante creado correctamente")
    @PostMapping("/crear")
    public Estudiante saveEstudiante(
            @Parameter(description = "Objeto estudiante a crear") @RequestBody Estudiante estudiante) {
        return estudianteService.saveEstudiante(estudiante);
    }

    @Operation(summary = "Eliminar un estudiante", description = "Elimina un estudiante por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Estudiante eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    })
    @DeleteMapping("/borrar/{id}")
    public String deleteEstudiante(
            @Parameter(description = "ID del estudiante a eliminar") @PathVariable Long id) {
        estudianteService.deleteEstudiante(id);
        return "El estudiante fue eliminado correctamente";
    }

    @Operation(summary = "Editar un estudiante", description = "Edita los datos de un estudiante existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Estudiante editado correctamente"),
        @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    })
    @PutMapping("/editar")
    public Estudiante editEstudiante(
            @Parameter(description = "ID del estudiante a editar") @RequestParam Long id,
            @Parameter(description = "Objeto estudiante con los nuevos datos") @RequestBody Estudiante estudiante) {
        estudianteService.editEstudiante(id, estudiante);
        return estudianteService.findEstudiante(id);
    }
}