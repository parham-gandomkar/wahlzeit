package org.wahlzeit.model;

import org.wahlzeit.exceptions.InvalidCoordinateClassTypeException;
import org.wahlzeit.exceptions.InvalidDistanceException;

public abstract class AbstractCoordinate implements Coordinate{


    @Override
    public Coordinate asCartesianCoordinate() throws InvalidCoordinateClassTypeException {
        return this;
    }

    @Override
    public Coordinate asSphericalCoordinate() throws InvalidCoordinateClassTypeException {
        return this;
    }

    public boolean isNull(Coordinate coordinate) {
        return coordinate == null;
    }

    // I defined the helper methods
    // in parents.
    // then we don't need to write this method for each child
    // and have redundant code
    public Coordinate convertSphericalToCartesian(Coordinate coordinate) {
        if (!isCartesianCoordinate(coordinate)) {
            float x = (float) (((SphericalCoordinate) coordinate).getRadius() * Math.cos(((SphericalCoordinate) coordinate).getTheta())
                    * Math.sin(((SphericalCoordinate) coordinate).getPhi()));
            float y = (float) (((SphericalCoordinate) coordinate).getRadius() * Math.sin(((SphericalCoordinate) coordinate).getPhi())
                    * Math.sin(((SphericalCoordinate) coordinate).getTheta()));
            float z = (float) (((SphericalCoordinate) coordinate).getRadius() * Math.cos(((SphericalCoordinate) coordinate).getPhi()));
            return new CartesianCoordinate(x, y, z);
        }
        return coordinate;
    }
    public Coordinate convertCartesianToSpherical(Coordinate coordinate) {
        if (!isSphericalCoordinate(coordinate)) {
            float r = (float) Math.sqrt(((CartesianCoordinate) coordinate).getX() * ((CartesianCoordinate) coordinate).getX() +
                    ((CartesianCoordinate) coordinate).getY() * ((CartesianCoordinate) coordinate).getY()
                    + ((CartesianCoordinate) coordinate).getZ() * ((CartesianCoordinate) coordinate).getZ());
            float teta = (float) Math.atan2(((CartesianCoordinate) coordinate).getY(), ((CartesianCoordinate) coordinate).getX());
            float phi = (float) Math.acos(((CartesianCoordinate) coordinate).getZ() / r);
            return new SphericalCoordinate(phi, teta, r);
        }
        return coordinate;
    }
    // helper methods for calculating distance and central Angle
    public double calculateDistance(CartesianCoordinate coordinate, CartesianCoordinate cartesianCoordinate) {
        return Math.sqrt(Math.pow(cartesianCoordinate.getX() - coordinate.getX(), 2) +
                Math.pow(cartesianCoordinate.getY() - coordinate.getY(), 2) +
                Math.pow(cartesianCoordinate.getZ() - coordinate.getZ(), 2));
    }
    public double calculateCentralAngle(SphericalCoordinate coordinate, SphericalCoordinate sphericalCoordinate) {
        double a = Math.sin(coordinate.getPhi() * Math.sin(sphericalCoordinate.getPhi()));
        double b = Math.cos(coordinate.getPhi() * Math.sin(sphericalCoordinate.getPhi()));
        double thetaDiff = Math.abs(coordinate.getTheta() - sphericalCoordinate.getRadius());
        return   Math.acos(a + b * thetaDiff);
    }

    // helper methods and assert methods
    public boolean isSphericalCoordinate(Coordinate coordinate) {
        return coordinate instanceof SphericalCoordinate;
    }
    public boolean isCartesianCoordinate(Coordinate coordinate) {
        return coordinate instanceof CartesianCoordinate;
    }



    public void assertIsDistanceGreaterThanZero(double distance) throws InvalidDistanceException {
        if(distance < 0){
            throw new InvalidDistanceException();
        }
    }

    public void assertIsCartesianCoordinate(Coordinate coordinate) throws InvalidCoordinateClassTypeException {
        if(coordinate instanceof CartesianCoordinate){
            // error escalation
            convertSphericalToCartesian(coordinate);
        }
        else {
            // error signaling
            throw new InvalidCoordinateClassTypeException("coordinate class is not cartesian");
        }
    }

    public void assertIsSphericalCoordinate(Coordinate coordinate) throws InvalidCoordinateClassTypeException {
        if(coordinate instanceof SphericalCoordinate){
            // error escalation
            convertCartesianToSpherical(coordinate);
        }
        else {
            // error signaling
            throw new InvalidCoordinateClassTypeException("coordinate class is not Spherical");
        }
    }

    public void assertIsCoordinateValid(Coordinate coordinate) throws NullPointerException {
        if(coordinate == null){
            throw new NullPointerException();
        }
        else if (coordinate instanceof SphericalCoordinate)
            assertIsSphericalCoordinateValid((SphericalCoordinate)coordinate);
        else if (coordinate instanceof CartesianCoordinate)
            assertIsCartesianCoordinateValid((CartesianCoordinate)coordinate);
    }

    private void assertIsSphericalCoordinateValid(SphericalCoordinate coordinate) throws NullPointerException {
        assertIsDoubleValueValid(coordinate.getPhi());
        assertIsDoubleValueValid(coordinate.getTheta());
        assertIsDoubleValueValid(coordinate.getRadius());
    }

    private void assertIsCartesianCoordinateValid(CartesianCoordinate coordinate) throws NullPointerException {
        assertIsDoubleValueValid(coordinate.getX());
        assertIsDoubleValueValid(coordinate.getY());
        assertIsDoubleValueValid(coordinate.getZ());
    }

    private void assertIsDoubleValueValid(double x){
        if(Double.isInfinite(x)){
            throw new IllegalArgumentException("Overflow!");
        }
        if(Double.isNaN(x)) {
            throw new IllegalArgumentException("NaN!");
        }
    }

}
