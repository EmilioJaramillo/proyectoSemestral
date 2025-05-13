package com.EduHubAcademy.soporteService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.EduHubAcademy.soporteService.model.Soporte;
import com.EduHubAcademy.soporteService.service.SoporteService;

@RestController
@RequestMapping("/soportes")
public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    @GetMapping ("/traer")
    public List<Soporte> getAllSoportes() {
        return soporteService.getAllSoportes();
    }

    @GetMapping("/{id}")
    public Soporte getSoporte(@PathVariable Long id) {
        return soporteService.getSoporteById(id);
    }

    @PostMapping("/crear")
    public Soporte saveSoporte(@RequestBody Soporte soporte) {

        return soporteService.saveSoporte(soporte);
    }

    @DeleteMapping("/borrar/{id}")
    public String deleteSoporte(@PathVariable Long id) {
        soporteService.deleteSoporte(id);
        return "La persona fue eliminada correctamente";

    }


    @PutMapping ("/editar")
    public Soporte editSoporte(@RequestBody Soporte soporte) {
        soporteService.editSoporte(soporte);

        return soporteService.findSoporte(soporte.getId());
    }



}
