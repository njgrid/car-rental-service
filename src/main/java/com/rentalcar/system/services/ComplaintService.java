package com.rentalcar.system.services;

import com.rentalcar.system.entities.Complaint;

import java.util.List;

public interface ComplaintService {
    void fileComplaint(Complaint complaint);
    void updateComplaint(Complaint complaint);
    void deleteComplaintById(int id);
    Complaint getComplaintById(int id);
    List<Complaint> getAllComplaints();
    List<Complaint> getComplaintsByCustomerId(int customerId);

}

