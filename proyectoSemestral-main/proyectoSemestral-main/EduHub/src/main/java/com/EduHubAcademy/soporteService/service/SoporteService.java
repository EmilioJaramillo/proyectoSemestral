package com.EduHubAcademy.soporteService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.EduHubAcademy.soporteService.model.Soporte;
import com.EduHubAcademy.soporteService.repository.SoporteRepository;
import org.springframework.stereotype.Service;

@Service
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

    public void deleteSoporte(Long id) {
         soporteRepository.deleteById(id);
    }

    public void editSoporte(Soporte soporte) {
        this.saveSoporte(soporte);
    }

    public Soporte findSoporte(Long id) {
        return soporteRepository.findById(id).orElse(null);

    }

}
