package com.rentalcar.system.services;

import com.rentalcar.system.entities.Booking;
import com.rentalcar.system.entities.Car;
import com.rentalcar.system.entities.Customer;
import com.rentalcar.system.exceptions.LoggedInAlreadyException;
import com.rentalcar.system.exceptions.LoggedOutAlreadyException;
import com.rentalcar.system.exceptions.WrongCredentials;

import java.util.Date;
import java.util.List;

public interface CustomerService {

    Customer getCustomerById(int id);
    Customer updateCustomer(Customer customer);
    void deleteCustomerById(int id);
    List<Customer> getAllCustomers();
    void registerCustomer(Customer customer);
    Customer login(String email, String password) throws LoggedInAlreadyException;
    void logout(Customer customer) throws LoggedOutAlreadyException;
    List<Car> searchCars(String location, Date startDate, Date endDate, String vehicleType);
    Car getCarDetails(int carId);
    Booking bookCar(int customerId, int carId, Date startDate, Date endDate) throws WrongCredentials;
    Booking getBookingDetails(int bookingId);
    void cancelBooking(int bookingId);
    void extendBookingDuration(int bookingId, Date newEndDate);
    void makePayment(int bookingId, int amount);
    void lodgeComplaint(int customerId, String description);
    void provideFeedback(int customerId, int bookingId, String feedback);
    void returnCar(int bookingId);
    void payFine(int customerId, double amount);
    void deRegisterCustomer(int customerId);

}

