package com.rentalcar.system.services;

import com.rentalcar.system.entities.Admin;
import com.rentalcar.system.entities.Car;

import java.util.List;

public interface AdminService {
    void addNewAdmin(Admin admin);
    Admin getAdminById(int id);
    Admin updateAdmin(Admin admin);
    void deleteAdminById(int id);
    List<Admin> getAllAdmins();

    void addNewCar(Car car);
    void setRentalPrice(Car car);
    Car updatecardDetail(Car car);



    // Other business logic methods related to admins
}

