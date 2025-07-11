package com.EduHubAcademy.asignaturaService.controller;

import com.EduHubAcademy.asignaturaService.dto.AsignaturaDto;
import com.EduHubAcademy.asignaturaService.model.Asignatura;
import com.EduHubAcademy.asignaturaService.model.SaveAsignaturaRequest;
import com.EduHubAcademy.asignaturaService.repository.AsignaturaRepository;
import com.EduHubAcademy.asignaturaService.service.AsignaturaService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/cursos")
@Tag(name = "Asignaturas", description = "Operaciones relacionadas con asignaturas")
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;
    @Autowired
    private AsignaturaRepository repo;

    @Autowired
    private SaveAsignaturaRequest request;

    @Operation(summary = "Obtener todas las asignaturas", description = "Devuelve una lista de todas las asignaturas")
    @ApiResponse(responseCode = "200", description = "Lista de asignaturas obtenida correctamente")
    @GetMapping("/traer")
    public CollectionModel<EntityModel<Asignatura>> getAllAsignaturas() {
        List<EntityModel<Asignatura>> asignaturas = asignaturaService.getAllAsignaturas().stream()
            .map(asignatura -> EntityModel.of(asignatura,
                linkTo(methodOn(AsignaturaController.class).getAsignatura(asignatura.getId())).withSelfRel(),
                linkTo(methodOn(AsignaturaController.class).getAllAsignaturas()).withRel("asignaturas")))
            .collect(Collectors.toList());
        return CollectionModel.of(asignaturas,
            linkTo(methodOn(AsignaturaController.class).getAllAsignaturas()).withSelfRel());
    }

    @Operation(summary = "Obtener asignatura por ID", description = "Devuelve una asignatura según su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Asignatura encontrada"),
        @ApiResponse(responseCode = "404", description = "Asignatura no encontrada")
    })



//    @GetMapping("/{id}")
//    public ResponseEntity<EntityModel<Asignatura>> getAsignatura(  @Parameter(description = "ID de la asignatura a buscar")@PathVariable Long id) {
//        Asignatura asignatura = asignaturaService.getAsignaturaById(id);
//        if (asignatura == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(
//                EntityModel.of(asignatura,
//                        linkTo(methodOn(AsignaturaController.class).getAsignatura(id)).withSelfRel(),
//                        linkTo(methodOn(AsignaturaController.class).getAllAsignaturas()).withRel("asignaturas"))
//        );
//    }

    //metodo para traer por Id
    @GetMapping("/{id}")
    public ResponseEntity<AsignaturaDto> getAsignatura(@PathVariable Long id) {
        Asignatura asignatura = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));

        AsignaturaDto dto = new AsignaturaDto();
        BeanUtils.copyProperties(asignatura, dto);
        return ResponseEntity.ok(dto);
    }


    @Operation(summary = "Crear una nueva asignatura", description = "Guarda una nueva asignatura en la base de datos")
    @ApiResponse(responseCode = "201", description = "Asignatura creada correctamente")
    @PostMapping("/crear")
    public Asignatura saveAsignatura(
            @Parameter(description = "Objeto asignatura a crear")@RequestBody SaveAsignaturaRequest request) {
        Asignatura asignatura = asignaturaService.saveAsignatura(
                request.getNombre(),
                request.getDocenteId()
        );
        return asignatura;
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
    @PutMapping("/{id}/incrementar-inscritos")
    public ResponseEntity<Void> incrementarInscritos(@PathVariable Long id) {
        Asignatura asignatura = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Asignatura no encontrada"));

        if (asignatura.getInscritos() >= asignatura.getCupoMaximo()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No hay cupos disponibles");
        }

        asignatura.setInscritos(asignatura.getInscritos() + 1);
        repo.save(asignatura);
        return ResponseEntity.ok().build();
    }
}

