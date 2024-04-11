package com.rentalcar.system.services;

import com.rentalcar.system.entities.Booking;
import com.rentalcar.system.entities.Car;
import com.rentalcar.system.exceptions.ValidationException;
import com.rentalcar.system.exceptions.WrongCredentials;
import com.rentalcar.system.repositories.BookingRepository;
import com.rentalcar.system.repositories.CarRepository;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final BookingRepository bookingRepository;

    public CarServiceImpl(CarRepository carRepository, BookingRepository bookingRepository){
        this.carRepository = carRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void addNewCar(Car car){
        carRepository.save(car);
    }

    @Override
    public Car getCarById(int id) throws WrongCredentials{
        if(carRepository.getById(id) == null) {
            throw new WrongCredentials("Car with id: " + id + " does not exist");
        }
        return carRepository.getById(id);
    }

    @Override
    public void updateCar(Car car) throws ValidationException {
        verifyCredentials(Car.class,car);
        carRepository.update(car);
    }

    @Override
    public Car deleteCarById(int id) throws WrongCredentials{
        if(carRepository.getById(id) == null) {
            throw new WrongCredentials("Car with id: " + id + " does not exist");
        }
        return carRepository.deleteById(id);
    }

    @Override
    public List<Car> getAllCars(){
        return carRepository.findAll();
    }


    public static void verifyCredentials(Class<?> clazz, Object object) throws ValidationException {
        Field[] fields = clazz.getDeclaredFields();
        for(Field field: fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                if (value == null) {
                    throw new ValidationException("Field '" + field.getName() + "' cannot be null");
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to access field: " + field.getName(), e);
            }
        }
    }

}
