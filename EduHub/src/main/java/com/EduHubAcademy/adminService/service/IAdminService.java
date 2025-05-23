package com.EduHubAcademy.adminService.service;

import com.EduHubAcademy.adminService.model.Admin;


import java.util.List;

public interface IAdminService {
    // listar admin
    public List<Admin> getAllAdmins();

    public Admin getAdminById(Long id);

    // guardando las admins
    public Admin saveAdmin(Admin admin);

    // borrar admin
    public void deleteAdmin(Long id);

    // editar admin
    public void editAdmin(Long id, Admin admin);

    Admin findAdmin(Long id);

}
