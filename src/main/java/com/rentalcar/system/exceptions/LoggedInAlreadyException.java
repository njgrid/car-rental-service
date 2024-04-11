package com.rentalcar.system.exceptions;

public class LoggedInAlreadyException extends Exception {
    public LoggedInAlreadyException(String msg) {
        super(msg);
    }
}
