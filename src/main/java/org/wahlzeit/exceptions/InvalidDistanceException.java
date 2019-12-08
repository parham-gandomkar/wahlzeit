package org.wahlzeit.exceptions;

public class InvalidDistanceException extends Exception {

    public InvalidDistanceException(String message) {
        super(message);
    }

    public InvalidDistanceException() {
        super("Distance can not be less than 0");
    }
}
