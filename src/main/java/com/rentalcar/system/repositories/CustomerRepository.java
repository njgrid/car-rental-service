package com.rentalcar.system.repositories;

import com.rentalcar.system.entities.Customer;

import java.util.List;

public interface CustomerRepository {
    void save(Customer customer);
    Customer getById(int id);
    Customer deleteById(int id);
    void update(Customer customer);
    List<Customer> getByName(String name);
    List<Customer> findAll();

    public Customer getByEmailAndPassword(String email, String password);
}

