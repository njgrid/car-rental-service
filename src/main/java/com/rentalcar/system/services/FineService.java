package com.rentalcar.system.services;

import com.rentalcar.system.entities.Fine;

import java.util.List;

public interface FineService {
    void issueFine(Fine fine);
    Fine getFineById(int id);
    List<Fine> getAllFines();
}

