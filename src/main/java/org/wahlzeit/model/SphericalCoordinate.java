package org.wahlzeit.model;

import org.wahlzeit.exceptions.InvalidCoordinateClassTypeException;
import org.wahlzeit.exceptions.InvalidDistanceException;

@PatternInstance(
        patternName = {"Value Object","Template"},
        participants = {
                "SphericalCoordinate", "AbstractCoordinate"
        }
)
public class SphericalCoordinate extends AbstractCoordinate {

    private final double phi;
    private final double theta;
    private final double radius;

    public double getPhi() {
        return phi;
    }

    public double getTheta() {
        return theta;
    }

    public double getRadius() {
        return radius;
    }

    public SphericalCoordinate(double phi, double theta, double radius) {
        assertVerifyAttributes(phi,theta,radius);
        this.phi = phi;
        this.theta = theta;
        this.radius = radius;
    }


    // first convert the instance to cartesian coordinate
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
            coordinate = convertCartesianToSpherical(coordinate);
            assertIsCoordinateValid(coordinate);
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
            return isCoordinateEqual(coordinate);
        else if (isCartesianCoordinate(coordinate))
        {
            // first convert coordinate from cartesian to spheric then
            // call super.isEqual method
            return isCoordinateEqual(convertCartesianToSpherical(coordinate));
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

    private boolean isCoordinateEqual(Coordinate coordinate) {
        if (isNull(coordinate)) return false;
        if (coordinate == this)
            return true;
        // for comparing two double variable,
        // i used an approximation for checking equality
        return ((Math.abs(((SphericalCoordinate) coordinate).getPhi() - phi) < 0.1) &&
                (Math.abs(((SphericalCoordinate) coordinate).getTheta() - theta) < 0.1) &&
                (Math.abs(((SphericalCoordinate) coordinate).getRadius() - radius)) < 0.1);
    }

    @Override
    public int hashCode() {
        return Integer.valueOf(String.valueOf(phi) + String.valueOf(theta) + String.valueOf(radius));
    }

    public static String getKeyString(double latitude, double longitude, double radius) {
        return String.format("latitude=%.10f, longitude=%.10f, radius=%.10f", latitude, longitude, radius);
    }

}
