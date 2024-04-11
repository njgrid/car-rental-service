package com.rentalcar.system.repositories;

import com.rentalcar.system.entities.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentRepositoryImpl implements PaymentRepository {

    private final List<Payment> payments;

    public PaymentRepositoryImpl() {
        this.payments = new ArrayList<>();
    }

    @Override
    public void save(Payment payment) {
        payments.add(payment);
    }

    @Override
    public Payment getById(int id) {
        return payments.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Payment> getByCustomerId(int customerId) {
        return payments.stream()
                .filter(p -> p.getCustomerId() == customerId)
                .toList();
    }

    @Override
    public List<Payment> findAll() {
        return new ArrayList<>(payments);
    }
}

