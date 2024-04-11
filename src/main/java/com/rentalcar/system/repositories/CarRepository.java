package com.rentalcar.system.repositories;

import com.rentalcar.system.entities.Car;

import java.util.Date;
import java.util.List;

public interface CarRepository {
    void save(Car car);
    Car getById(int id);
    Car deleteById(int id);
    List<Car> findByBrand(String brand);
    List<Car> findByType(String type);
    List<Car> findAll();
    void update(Car car);

}

