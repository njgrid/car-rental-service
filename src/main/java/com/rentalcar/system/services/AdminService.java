package com.rentalcar.system.services;

import com.rentalcar.system.entities.Admin;

import java.util.List;

public interface AdminService {
    void addNewAdmin(Admin admin);
    Admin getAdminById(int id);
    Admin updateAdmin(Admin admin);
    void deleteAdminById(int id);
    List<Admin> getAllAdmins();


    // Other business logic methods related to admins
}

