package com.rentalcar.system.services;

import com.rentalcar.system.entities.Booking;
import com.rentalcar.system.entities.Car;
import com.rentalcar.system.entities.Customer;

import com.rentalcar.system.entities.Payment;
import com.rentalcar.system.exceptions.WrongCredentials;
import com.rentalcar.system.repositories.BookingRepository;
import com.rentalcar.system.repositories.CustomerRepository;
import com.rentalcar.system.utils.BookingStatus;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CarService carService;
    private final BookingService bookingService;

    private final PaymentService paymentService;

    private final BookingRepository bookingRepository;
    //can have injectors as well for this

    public CustomerServiceImpl(CustomerRepository customerRepository, CarService carService, BookingService bookingService, BookingRepository bookingRepository, PaymentService paymentService) {
        this.customerRepository = customerRepository;
        this.carService = carService;
        this.bookingService = bookingService;
        this.bookingRepository= bookingRepository;
        this.paymentService = paymentService;
    }

    @Override
    public void registerCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerRepository.getById(id);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public void deleteCustomerById(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer login(String email, String password) {
        Customer customer = customerRepository.getByEmailAndPassword(email, password);
        if (customer != null) {
            customer.setLoggedIn(true); // Update the loggedIn field to true
        }
        return customer;
    }


    @Override
    public void logout(Customer customer) {
        customer.setLoggedIn(false);
    }
    //alternately can have session manager etc.



    @Override
    public Car getCarDetails(int carId) {
        return null;
    }

    @Override
    public Booking bookCar(int customerId, int carId, Date startDate, Date endDate) throws WrongCredentials {
        // Get the customer and car
        Customer customer = customerRepository.getById(customerId);
        Car car = carService.getCarById(carId);

        // Validate customer and car
        if (customer == null || car == null) {
            throw new IllegalArgumentException("Customer or car not found");
        }

        // Check if the car is available for booking
        if (!isCarAvailable(car, startDate, endDate, bookingService.getAllBookings())) {
            throw new IllegalStateException("Car is not available for the specified dates");
        }

        long timeDifference = endDate.getTime() - startDate.getTime();
        int daysDifference = (int) timeDifference / (1000 * 60 * 60 * 24);
        int amount = car.getPricePerDay() * daysDifference;

        // Create the booking
        Booking booking = new Booking();
        booking.setCustomerId(customerId);
        booking.setCar(car);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        booking.setStatus(BookingStatus.PENDING); // Set status to pending initially
        booking.setPayment(new Payment(customerId+1,customerId,amount));

        // Save the booking
        bookingRepository.save(booking);

        return booking;
    }


    @Override
    public Booking getBookingDetails(int bookingId) {
        // Implement logic to retrieve booking details
        return null;
    }

    @Override
    public void cancelBooking(int bookingId) {
        // Implement logic to cancel a booking
    }

    @Override
    public void extendBookingDuration(int bookingId, Date newEndDate) {
        // Implement logic to extend the duration of a booking
    }

    @Override
    public void makePayment(int bookingId, int amount) {
        Booking booking = bookingService.getBookingById(bookingId);
        if (booking != null) {
            if (paymentService.processPayment(booking.getCustomerId(), amount)) {
                // Update booking status to CONFIRMED
                booking.setStatus(BookingStatus.CONFIRMED);
                bookingRepository.save(booking);
                System.out.println("Payment successful. Booking confirmed.");
            } else {
                System.out.println("Payment failed. Please try again later.");
            }
        } else {
            System.out.println("Booking not found.");
        }
    }

    @Override
    public void lodgeComplaint(int customerId, String description) {
    }

    @Override
    public void provideFeedback(int customerId, int bookingId, String feedback) {
    }

    @Override
    public void returnCar(int bookingId) {
    }

    @Override
    public void payFine(int customerId, double amount) {
    }

    @Override
    public void deRegisterCustomer(int customerId) {
    }

    @Override
    public List<Car> searchCars(String location, Date startDate, Date endDate, String vehicleType) {
        List<Car> allCars = carService.getAllCars();
        List<Booking> bookings = bookingService.getAllBookings();

        // Filter out cars based on location, vehicle type, and availability
        return allCars.stream()
                .filter(car -> car.getLocation().equals(location))
                .filter(car -> car.getType().equals(vehicleType))
                .filter(car -> isCarAvailable(car, startDate, endDate, bookings))
                .collect(Collectors.toList());
    }

    private boolean isCarAvailable(Car car, Date startDate, Date endDate, List<Booking> bookings) {
        for (Booking booking : bookings) {
            if (booking.getCar().equals(car) && !bookingIsAvailable(booking, startDate, endDate)) {
                return false;
            }
        }
        return true;
    }

    private boolean bookingIsAvailable(Booking booking, Date startDate, Date endDate) {
        return !(booking.getEndDate().before(startDate) || booking.getStartDate().after(endDate));
    }

}

