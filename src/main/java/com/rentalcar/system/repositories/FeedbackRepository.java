package com.rentalcar.system.repositories;

import com.rentalcar.system.entities.Feedback;

import java.util.List;

public interface FeedbackRepository {
    void save(Feedback feedback);
    Feedback getById(int id);
    Feedback deleteById(int id);
    List<Feedback> findAll();
    List<Feedback> findByCustomerId(int customerId);
}

