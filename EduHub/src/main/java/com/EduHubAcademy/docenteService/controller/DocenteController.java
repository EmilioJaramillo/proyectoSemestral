package com.EduHubAcademy.docenteService.controller;


import com.EduHubAcademy.docenteService.model.Docente;
import com.EduHubAcademy.docenteService.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docentes")

public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping("/traer")
    public List<Docente> getAllDocentes() {
        return docenteService.getAllDocentes();
    }


    @GetMapping("/{id}")
    public Docente getDocente(@PathVariable Long id) {
        return docenteService.getDocenteById(id);
    }

    @PostMapping("/crear")
    public Docente saveDocente(@RequestBody Docente docente) {

        return docenteService.saveDocente(docente);
    }

    @DeleteMapping("/borrar/{id}")
    public String deleteDocente(@PathVariable Long id) {
        docenteService.deleteDocente(id);
        return "El Docente fue eliminado correctamente";

    }

    @PutMapping ("/editar")
    public Docente editDocente(@PathVariable Long id,
                                     @RequestBody Docente docente) {
        docenteService.editDocente(id, docente);
      Docente docenteEditado = docenteService.findDocente(id);
        return docenteEditado;
    }

}
