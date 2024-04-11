package com.rentalcar.system.repositories;

import com.rentalcar.system.entities.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {

    private final List<Customer> customerList;

    public CustomerRepositoryImpl() {
        this.customerList = new ArrayList<>();
    }

    @Override
    public void save(Customer customer) {
        if(customerList.contains(customer)){
            update(customer);
            return;
        }
        customerList.add(customer);
    }

    @Override
    public Customer getById(int id) {
        return customerList.stream()
                .filter(c -> c.getCustomerId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Customer customer) {
        int index = customerList.indexOf(customer);
        if (index != -1) {
            customerList.set(index, customer);
        }
    }

    @Override
    public Customer deleteById(int id) {
        Customer customer = customerList.stream().filter(c -> c.getCustomerId() == id).findFirst().get();
        customerList.remove(customer);
        return customer;

    }

    @Override
    public List<Customer> getByName(String name){
        return customerList.stream().filter(d -> d.getName().equals(name)).toList();
    }

    @Override
    public List<Customer> findAll() {
        return customerList.stream().toList();
    }

    @Override
    public Customer getByEmailAndPassword(String email, String password){
        // Implement logic to retrieve customer by email and password from the database
        // Example: Use JDBC or JPA to execute a query
        // Replace this with your actual database query implementation
        for (Customer customer : customerList) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                return customer;
            }
        }
        return null; // Customer not found with the provided email and password
    }
}

