package com.rentalcar.system.services;

import com.rentalcar.system.entities.Feedback;

import java.util.List;

public interface FeedbackService {
    void submitFeedback(Feedback feedback);
    Feedback getFeedbackById(int id);
    List<Feedback> getAllFeedback();


    // Other business logic methods related to feedback
}
