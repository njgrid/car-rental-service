package com.rentalcar.system.services;

import com.rentalcar.system.entities.Booking;
import com.rentalcar.system.entities.Payment;
import com.rentalcar.system.repositories.BookingRepository;
import com.rentalcar.system.repositories.PaymentRepository;
import com.rentalcar.system.utils.BookingStatus;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

//    @Override
//    public boolean processPayment(int customerId, int amount) {
//        Payment payment = new Payment();
//        payment.setCustomerId(customerId);
//        payment.setAmount(amount);
//        paymentRepository.save(payment);
//        return true;
//    }

    @Override
    public boolean processPayment(int customerId, int amount) {
        Payment payment = new Payment();
        payment.setCustomerId(customerId);
        payment.setAmount(amount);
        paymentRepository.save(payment);

        // Find the booking associated with the customer
        Booking booking = bookingRepository.findBookingByCustomerId(customerId);

        if (booking != null) {
            booking.setPayment(payment);
            booking.setStatus(BookingStatus.CONFIRMED);
            bookingRepository.save(booking);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void updatePayment(Payment payment) {
        // Assuming update logic here if needed
    }

    @Override
    public void deletePaymentById(int id) {
        // Assuming delete logic here if needed
    }

    @Override
    public Payment getPaymentById(int id) {
        return paymentRepository.getById(id);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> getPaymentsByCustomerId(int customerId) {
        return paymentRepository.getByCustomerId(customerId);
    }
}

