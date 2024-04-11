package com.rentalcar.system;
import com.rentalcar.system.entities.Booking;
import com.rentalcar.system.entities.Car;
import com.rentalcar.system.entities.Customer;
import com.rentalcar.system.exceptions.WrongCredentials;
import com.rentalcar.system.repositories.*;
import com.rentalcar.system.services.*;

import java.util.Date;
import java.util.List;

public class CarRentalApp {
    public static void main(String[] args) {


        // Create repositories
        CarRepository carRepository = new CarRepositoryImpl();
        CustomerRepository customerRepository = new CustomerRepositoryImpl();
        BookingRepository bookingRepository = new BookingRepositoryImpl();
        PaymentRepository paymentRepository = new PaymentRepositoryImpl();

        // Create services
        CarService carService = new CarServiceImpl(carRepository, bookingRepository);
        BookingService bookingService = new BookingServiceImpl(bookingRepository);
        PaymentService paymentService = new PaymentServiceImpl(paymentRepository, bookingRepository);
        CustomerService customerService = new CustomerServiceImpl(customerRepository, carService, bookingService, bookingRepository, paymentService);


        // Register a customer
        Customer customer1 = new Customer(9, "Nayan Jain", "john@example.com", "mypassword", "123 Street", "1234567890", false);
        customerService.registerCustomer(customer1);


        // Add cars to the system
        Car car1 = new Car(1, "Toyota Camry", "Toyota", 2020, "Sedan", "Location 1", 50, true);
        Car car2 = new Car(2, "Honda Civic", "Honda", 2019, "Sedan", "Location 2", 45, true);
        carService.addNewCar(car1);
        carService.addNewCar(car2);

        // Search for available cars
        String location = "Location 1";
        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + (1000 * 60 * 60 * 24 * 7)); // 7 days from now
        String vehicleType = "Sedan";
        List<Car> availableCars = customerService.searchCars(location, startDate, endDate, vehicleType);
        System.out.println("Available cars:");
        for (Car car : availableCars) {
            System.out.println(car);
        }


        // Book a car (status will be pending, as no payment yet done)
        Car carToBook1;
        Booking booking1 = null;

        if (!availableCars.isEmpty()) {
            carToBook1 = availableCars.get(0);
            try {
                booking1 = customerService.bookCar(customer1.getCustomerId(), carToBook1.getCarId(), startDate, endDate);
                System.out.println("Booking successful:");
                System.out.println(booking1);
            } catch (WrongCredentials | IllegalStateException e) {
                System.out.println("Booking failed: " + e.getMessage());
            }
        }

        //booking status before:
        if(booking1 != null){
            System.out.println("booking status before making a payment: "+ booking1.getStatus());
        }


        // Make a payment for the booking
        if (booking1 != null) {
            int amount = booking1.getPayment().getAmount();
            try {
                paymentService.processPayment(customer1.getCustomerId(), amount);
                System.out.println("Payment successful. Booking confirmed.");
            } catch (Exception e) {
                System.out.println("Payment failed: " + e.getMessage());
            }
        }

        // Retrieve the updated booking after making the payment
        Booking updatedBooking = bookingService.getBookingById(booking1.getBookingId());
        if (updatedBooking != null) {
            System.out.println("Updated booking status after payment: " + updatedBooking.getStatus());
        } else {
            System.out.println("Booking not found.");
        }

    }
}
