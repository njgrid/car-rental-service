package com.rentalcar.system.repositories;

import com.rentalcar.system.entities.Booking;
import com.rentalcar.system.utils.BookingStatus;

import java.util.List;

public interface BookingRepository {
    void save(Booking booking);
    Booking getById(int id);
    Booking deleteById(int id);
    Booking findBookingByCustomerId(int customerId);
    List<Booking> findAll();

    List<Booking> findByCarId(int carId);
    List<Booking> findByStatus(BookingStatus status);
}

