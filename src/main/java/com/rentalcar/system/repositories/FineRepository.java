package com.rentalcar.system.repositories;

import com.rentalcar.system.entities.Fine;

import java.util.List;

public interface FineRepository {
    void save(Fine fine);
    Fine getById(int id);
    Fine deleteById(int id);
    List<Fine> findByCustomerId(int customerId);
    List<Fine> findAll();
}

