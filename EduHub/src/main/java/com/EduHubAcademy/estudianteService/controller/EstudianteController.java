package com.EduHubAcademy.estudianteService.controller;

import com.EduHubAcademy.estudianteService.model.Estudiante;
import com.EduHubAcademy.estudianteService.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")

public class EstudianteController {


      @Autowired
    private EstudianteService estudianteService;

     @GetMapping("/traer")
    public List<Estudiante> getAllEstudiantes() {
        return estudianteService.getAllEstudiantes();
    }


    @GetMapping("/{id}")
    public Estudiante getEstudiante(@PathVariable Long id) {
        return estudianteService.getEstudianteById(id);
    }

    @PostMapping("/crear")
    public Estudiante saveEstudiante(@RequestBody Estudiante estudiante) {

        return estudianteService.saveEstudiante(estudiante);
    }

    @DeleteMapping("/borrar/{id}")
    public String deleteEstudiante(@PathVariable Long id) {
        estudianteService.deleteEstudiante(id);
        return "El estudiante fue eliminado correctamente";

    }

    @PutMapping ("/editar")
    public Estudiante editEstudiante(@PathVariable Long id,
                                     @RequestBody Estudiante estudiante) {
       estudianteService.editEstudiante(id, estudiante);
      Estudiante estudianteEditado = estudianteService.findEstudiante(id);
        return estudianteEditado;
    }

}
