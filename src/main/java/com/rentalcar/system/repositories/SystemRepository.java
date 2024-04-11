package com.rentalcar.system.repositories;

import java.util.List;

public interface SystemRepository {
    void save(System system);
    System getById(int id);
    System deleteById(int id);
    List<System> findAll();
}

