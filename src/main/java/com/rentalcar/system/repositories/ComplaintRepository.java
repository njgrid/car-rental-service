package com.rentalcar.system.repositories;

import com.rentalcar.system.entities.Complaint;

import java.util.List;

public interface ComplaintRepository {
    void save(Complaint complaint);
    Complaint getById(int id);
    Complaint deleteById(int id);
    List<Complaint> findByCustomerId(int customerId);
    List<Complaint> findAll();
}

