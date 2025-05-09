package com.EduHubAcademy.soporteService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EduHubAcademy.soporteService.model.Soporte;
import com.EduHubAcademy.soporteService.service.SoporteService;

@RestController
@RequestMapping("/soportes")
public class SoporteController {

    @Autowired
    private SoporteService soporteService;

    @GetMapping
    public List<Soporte> getAllSoportes() {
        return soporteService.getAllSoportes();
    }

    @GetMapping("/{id}")
    public Soporte getSoporte(@PathVariable Long id) {
        return soporteService.getSoporteById(id);
    }

    @PostMapping
    public Soporte createSoporte(@RequestBody Soporte soporte) {
        return soporteService.saveSoporte(soporte);
    }


}
