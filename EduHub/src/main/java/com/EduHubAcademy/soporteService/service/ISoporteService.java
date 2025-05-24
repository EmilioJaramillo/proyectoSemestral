package com.EduHubAcademy.soporteService.service;

import com.EduHubAcademy.soporteService.model.Soporte;

import java.util.List;

public interface ISoporteService {

    // listar soporte
    public List<Soporte> getAllSoportes();

    public Soporte getSoporteById(Long id);

    // guardando las tickets
    public Soporte saveSoporte(Soporte soporte);

    // borrar tickets
    public void deleteSoporte(Long id);

    // editar tickets
    public void editSoporte(Long id, Soporte soporte);

   Soporte findSoporte(Long id);

}
