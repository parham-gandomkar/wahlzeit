package org.wahlzeit.model;

public class CoordinateFactory {

    public static final String CARTESIAN_COORDINATE = "cartesian";
    public static final String SPHERIC_COORDINATE = "spheric";

    public static Coordinate mackeCoordinate(String coordinate, double a, double b, double c) {
        if (coordinate.equals(CARTESIAN_COORDINATE)) {
            return new CartesianCoordinate(a, b, c);
        }
        else if (coordinate.equals(SPHERIC_COORDINATE)) {
            return new CartesianCoordinate(a, b, c);
        }
        return null;
    }
}
