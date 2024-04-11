package com.rentalcar.system.services;

import com.rentalcar.system.entities.Booking;
import com.rentalcar.system.exceptions.ValidationException;
import com.rentalcar.system.repositories.BookingRepository;
import com.rentalcar.system.utils.BookingStatus;

import java.util.List;

public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void makeBooking(Booking booking) throws ValidationException {
        validateBooking(booking);
        booking.setStatus(BookingStatus.PENDING); // Set status to pending initially
        bookingRepository.save(booking);
    }

    @Override
    public Booking getBookingById(int bookingId) {
        return bookingRepository.getById(bookingId);
    }

    @Override
    public Booking updateBooking(Booking booking) throws ValidationException {
        validateBooking(booking);
        // Update the booking if it exists
        Booking existingBooking = bookingRepository.getById(booking.getBookingId());
        if (existingBooking != null) {
            existingBooking.setStartDate(booking.getStartDate());
            existingBooking.setEndDate(booking.getEndDate());
            existingBooking.setStatus(booking.getStatus());
            bookingRepository.save(existingBooking);
            return existingBooking;
        } else {
            throw new IllegalArgumentException("Booking not found");
        }
    }

    @Override
    public void cancelBookingById(int bookingId) {
        Booking booking = bookingRepository.getById(bookingId);
        if (booking != null && booking.getStatus() != BookingStatus.CANCELED) {
            booking.setStatus(BookingStatus.CANCELED);
            bookingRepository.save(booking);
        }
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    private void validateBooking(Booking booking) throws ValidationException {
        // Perform validation checks on the booking
        if (booking.getStartDate().after(booking.getEndDate())) {
            throw new ValidationException("End date must be after start date");
        }
    }
}
