package com.rentalcar.system.entities;

import com.rentalcar.system.utils.BookingStatus;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Booking {
    private int bookingId;
    private int customerId;
    private Car car;
    private Date startDate;
    private Date endDate;
    private BookingStatus status;
    private Payment payment;
}
