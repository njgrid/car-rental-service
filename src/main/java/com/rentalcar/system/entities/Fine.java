package com.rentalcar.system.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Fine {
    private int fineId;
    private int bookingId;
    private int amount;
    private String reason;
    private Date dateIssued;
}
