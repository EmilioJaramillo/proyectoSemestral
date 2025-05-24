package com.EduHubAcademy.soporteService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.EduHubAcademy.soporteService.model.Soporte;
import com.EduHubAcademy.soporteService.repository.SoporteRepository;
import org.springframework.stereotype.Service;

@Service
public class SoporteService implements ISoporteService{

    @Autowired
    private SoporteRepository soporteRepository;
    @Override
    public List<Soporte> getAllSoportes() {
        return soporteRepository.findAll();
    }
    @Override
    public Soporte getSoporteById(Long id) {
        return soporteRepository.findById(id).orElse(null);
    }
    @Override
    public Soporte saveSoporte(Soporte soporte) {
        return soporteRepository.save(soporte);
    }
    @Override
    public void deleteSoporte(Long id) {
         soporteRepository.deleteById(id);
    }

    @Override
    public void editSoporte(Long id,Soporte soporte) {
        this.saveSoporte(soporte);
    }
    @Override
    public Soporte findSoporte(Long id) {
        return soporteRepository.findById(id).orElse(null);

    }

}
