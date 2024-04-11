package com.rentalcar.system.repositories;

import com.rentalcar.system.entities.Payment;

import java.util.List;

public interface PaymentRepository {
    void save(Payment payment);
    Payment getById(int id);
    List<Payment> getByCustomerId(int customerId);
    List<Payment> findAll();

}

