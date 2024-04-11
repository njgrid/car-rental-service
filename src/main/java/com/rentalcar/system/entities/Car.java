package com.rentalcar.system.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Car {
    private int carId;
    private String model;
    private String brand;
    private int year;
    private String type; // e.g., Sedan, SUV, Hatchback, etc.
    private String location;
    private int pricePerDay;
    private boolean available;
}
