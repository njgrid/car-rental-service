package com.rentalcar.system.repositories;

import com.rentalcar.system.entities.Booking;
import com.rentalcar.system.utils.BookingStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingRepositoryImpl implements BookingRepository {

    private final List<Booking> bookings;

    public BookingRepositoryImpl() {
        this.bookings = new ArrayList<>();
    }

    @Override
    public void save(Booking booking) {
        bookings.add(booking);
    }

    @Override
    public Booking getById(int id) {
        return bookings.stream()
                .filter(booking -> booking.getBookingId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Booking deleteById(int id) {
        Booking bookingToRemove = getById(id);
        if (bookingToRemove != null) {
            bookings.remove(bookingToRemove);
        }
        return bookingToRemove;
    }

    @Override
    public List<Booking> findAll() {
        return new ArrayList<>(bookings);
    }

    @Override
    public Booking findBookingByCustomerId(int customerId) {
        return bookings.stream()
                .filter(booking -> booking.getCustomerId() == customerId)
                .findFirst()
                .orElse(null);
    }


    @Override
    public List<Booking> findByCarId(int carId) {
        return bookings.stream()
                .filter(booking -> booking.getCar().getCarId() == carId)
                .collect(Collectors.toList());
    }


    @Override
    public List<Booking> findByStatus(BookingStatus status) {
        return bookings.stream()
                .filter(booking -> booking.getStatus() == status)
                .collect(Collectors.toList());
    }
}

