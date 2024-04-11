package com.rentalcar.system.services;

import com.rentalcar.system.entities.Booking;
import com.rentalcar.system.exceptions.ValidationException;

import java.util.List;

public interface BookingService {
    void makeBooking(Booking booking) throws ValidationException;
    Booking getBookingById(int id);
    Booking updateBooking(Booking booking) throws ValidationException;
    void cancelBookingById(int id);
    List<Booking> getAllBookings();



    // Other business logic methods related to bookings
}
