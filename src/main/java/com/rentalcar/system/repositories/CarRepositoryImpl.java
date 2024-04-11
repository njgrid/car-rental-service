package com.rentalcar.system.repositories;

import com.rentalcar.system.entities.Booking;
import com.rentalcar.system.entities.Car;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CarRepositoryImpl implements CarRepository {
    List<Car> carList;
    public CarRepositoryImpl(){
        carList = new ArrayList<>();
    }

    @Override
    public void save(Car car){
        if(carList.contains(car)){
            update(car);
            return;
        }
        carList.add(car);
    }

    @Override
    public Car getById(int id){
        return carList.stream().filter(car -> car.getCarId() == id).findFirst().get();
    }

    @Override
    public Car deleteById(int id) {
        Car car = carList.stream().filter(d -> d.getCarId() == id).findFirst().get();
        carList.remove(car);
        return car;
    }

    @Override
    public List<Car> findByBrand(String brandName) {
        return carList.stream().filter(d -> d.getBrand().equals(brandName)).toList();
    }

    @Override
    public List<Car> findByType(String carType) {
        return carList.stream().filter(d -> d.getType().equals(carType)).toList();
    }

    @Override
    public List<Car> findAll() {
        return carList.stream().toList();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void update(Car car){
        Car prevCar = carList.stream().filter(c -> c.getCarId() == car.getCarId()).findFirst().get();
        prevCar.setCarId(car.getCarId());
        prevCar.setModel(car.getModel());
        prevCar.setBrand(car.getBrand());
        prevCar.setYear(car.getYear());
        prevCar.setType(car.getType());
        prevCar.setPricePerDay(car.getPricePerDay());
        prevCar.setAvailable(car.isAvailable());
    }


}


