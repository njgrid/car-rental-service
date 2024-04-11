package com.rentalcar.system.repositories;

import com.rentalcar.system.entities.Admin;

import java.util.List;

public interface AdminRepository {
    void save(Admin admin);
    Admin getById (int id);
    Admin deleteById(int id);
    List<Admin> getByName(String name);
    List<Admin> findAll();
}
