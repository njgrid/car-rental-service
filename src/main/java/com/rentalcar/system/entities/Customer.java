
package com.rentalcar.system.entities;
import lombok.*;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {

    private int customerId;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phone;
    private boolean loggedIn;

}

