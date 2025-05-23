package com.EduHubAcademy.adminService.controller;

import com.EduHubAcademy.adminService.model.Admin;
import com.EduHubAcademy.adminService.service.ServiceAdmin;
import com.EduHubAcademy.asignaturaService.model.Asignatura;
import com.EduHubAcademy.soporteService.model.Soporte;
import com.EduHubAcademy.soporteService.service.SoporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private ServiceAdmin adminService;

    @GetMapping("/traer")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public Admin getAdmin(@PathVariable Long id) {
        return adminService.getAdminById(id);
    }

    @PostMapping("/crear")
    public Admin saveAdmin(@RequestBody Admin admin) {

        return adminService.saveAdmin(admin);
    }

    @DeleteMapping("/borrar/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return "La admin fue eliminada correctamente";

    }

    @PutMapping ("/editar")
    public Admin editAdmin(@PathVariable Long id,
                                     @RequestBody Admin admin) {
        adminService.editAdmin(id, admin);
        Admin adminEditado = adminService.findAdmin(id);
        return adminEditado;
    }

}
