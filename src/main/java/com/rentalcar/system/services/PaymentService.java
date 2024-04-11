package com.rentalcar.system.services;

import com.rentalcar.system.entities.Payment;

import java.util.List;

public interface PaymentService {
    boolean processPayment(int customerId, int amount);
    void updatePayment(Payment payment);
    void deletePaymentById(int id);
    Payment getPaymentById(int id);
    List<Payment> getAllPayments();
    List<Payment> getPaymentsByCustomerId(int customerId);
}

