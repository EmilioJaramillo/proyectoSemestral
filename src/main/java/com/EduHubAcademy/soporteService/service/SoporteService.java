package com.EduHubAcademy.soporteService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.EduHubAcademy.soporteService.model.Soporte;
import com.EduHubAcademy.soporteService.repository.SoporteRepository;

public class SoporteService {

    @Autowired
    private SoporteRepository soporteRepository;

    public List<Soporte> getAllSoportes() {
        return soporteRepository.findAll();
    }

    public Soporte getSoporteById(Long id) {
        return soporteRepository.findById(id).orElse(null);
    }

    public Soporte saveSoporte(Soporte soporte) {
        return soporteRepository.save(soporte);
    }

}
