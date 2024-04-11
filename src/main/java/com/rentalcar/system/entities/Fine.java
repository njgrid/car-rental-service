package com.rentalcar.system.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Fine {
    private int id;
    private LocalDateTime timestamp;
    private String description;
    private int rating;
    private int customerId;
}
