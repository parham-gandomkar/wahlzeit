package org.wahlzeit.model;

import org.wahlzeit.exceptions.InvalidCoordinateClassTypeException;
import org.wahlzeit.exceptions.InvalidDistanceException;

public class SphericalCoordinate extends AbstractCoordinate {


    public SphericalCoordinate(double phi, double theta, double radius) {
        assertVerifyAttributes(phi,theta,radius);
        this.x = phi;
        this.y = theta;
        this.z = radius;
    }


    // first convert the instance to coordinate coordinate
    // then calculate central angle
    @Override
    public double getCartesianDistance(Coordinate coordinate) throws InvalidCoordinateClassTypeException, InvalidDistanceException {
        // class invariant
        assertIsCoordinateValid(this);
        // Preconditions
        assertIsCoordinateValid(coordinate);
        coordinate = convertSphericalToCartesian(coordinate);
        assertIsCartesianCoordinate(coordinate);
        CartesianCoordinate cartesianCoordinate = (CartesianCoordinate) convertSphericalToCartesian(this);
        double distance = calculateDistance((CartesianCoordinate) coordinate, cartesianCoordinate);
        // Post conditions
        assertIsDistanceGreaterThanZero(distance);
        return distance;
    }



    @Override
    public double getCentralAngle(Coordinate coordinate) throws InvalidDistanceException {
            // class invariant
            assertIsCoordinateValid(this);
            // Preconditions
            assertIsCoordinateValid(coordinate);
            coordinate = convertCartesianToSpherical(coordinate);
            double angle =  calculateCentralAngle(this, (SphericalCoordinate) coordinate);
            // Postconditions
            assertIsDistanceGreaterThanZero(angle);
            return angle;
    }

    // override and change the behavior of
    // this method that is defined in the parent class
    // (abstract coordinate)
    @Override
    public Coordinate asCartesianCoordinate() throws InvalidCoordinateClassTypeException {
        // class invariant
        assertIsCoordinateValid(this);
        Coordinate coordinate =  convertSphericalToCartesian(this);
        // Post conditions
        assertIsCartesianCoordinate(coordinate);
        return coordinate;
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        // class invariant
        assertIsCoordinateValid(this);
        // Preconditions
        assertIsCoordinateValid(coordinate);

        if (isSphericalCoordinate(coordinate))
            return super.isEqual(coordinate);
        else if (isCartesianCoordinate(coordinate))
        {
            // first convert coordinate from cartesian to spheric then
            // call super.isEqual method
            return super.isEqual(convertCartesianToSpherical(coordinate));
        }
        else
            return false;
    }

    private void assertVerifyAttributes(double phi, double theta, double radius) {
        assert (Math.abs(theta) <= 90.0) : "latitude must be between -90 and +90";
        assert (phi > -180.0
                && phi <= 180.0) : "longitude must be greater than -180 and smaller or equal to +180";
        assert (radius >= 0) : "radius must not be negative";
    }



}
