package com.EduHubAcademy.asignaturaService.controller;

import com.EduHubAcademy.asignaturaService.model.Asignatura;
import com.EduHubAcademy.asignaturaService.service.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class AsignaturaController {

     @Autowired
    private AsignaturaService asignaturaService;

    @GetMapping("/traer")
    public List<Asignatura> getAllAsignaturas() {
        return asignaturaService.getAllAsignaturas();
    }


    @GetMapping("/{id}")
    public Asignatura getAsignatura(@PathVariable Long id) {
        return asignaturaService.getAsignaturaById(id);
    }

    @PostMapping("/crear")
    public Asignatura saveAsignatura(@RequestBody Asignatura asignatura) {

        return asignaturaService.saveAsignatura(asignatura);
    }

    @DeleteMapping("/borrar/{id}")
    public String deleteAsignatura(@PathVariable Long id) {
        asignaturaService.deleteAsignatura(id);
        return "El Asignatura fue eliminado correctamente";

    }


    @PutMapping ("/editar")
    public Asignatura editAsignatura(@PathVariable Long id,
                                     @RequestBody Asignatura asignatura) {
        asignaturaService.editAsignatura(id, asignatura);
       Asignatura asignaturaEditado = asignaturaService.findAsignatura(id);
        return asignaturaEditado;
    }

}
