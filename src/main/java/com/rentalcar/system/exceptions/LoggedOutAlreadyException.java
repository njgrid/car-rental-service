package com.rentalcar.system.exceptions;

public class LoggedOutAlreadyException extends Exception {
    public LoggedOutAlreadyException(String msg) {
        super(msg);
    }
}
