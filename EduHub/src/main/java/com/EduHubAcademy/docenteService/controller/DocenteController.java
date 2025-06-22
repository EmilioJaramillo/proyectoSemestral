package com.EduHubAcademy.docenteService.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EduHubAcademy.docenteService.model.Docente;
import com.EduHubAcademy.docenteService.service.DocenteService;

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
