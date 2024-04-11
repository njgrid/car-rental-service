package com.rentalcar.system.services;

import com.rentalcar.system.entities.Car;
import com.rentalcar.system.exceptions.ValidationException;
import com.rentalcar.system.exceptions.WrongCredentials;

import java.util.List;

public interface CarService {
    void addNewCar(Car car);
    Car getCarById(int id) throws WrongCredentials;
    void updateCar(Car car) throws ValidationException;
    Car deleteCarById(int id) throws WrongCredentials;
    List<Car> getAllCars();

}

