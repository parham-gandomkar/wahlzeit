package org.wahlzeit.exceptions;

public class TourException extends Exception {
    public TourException(String type, String message) {
        super(message);
    }

}
