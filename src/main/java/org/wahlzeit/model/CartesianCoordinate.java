package org.wahlzeit.model;

import org.wahlzeit.exceptions.InvalidCoordinateClassTypeException;
import org.wahlzeit.exceptions.InvalidDistanceException;

public class CartesianCoordinate extends AbstractCoordinate {


    public CartesianCoordinate(double x, double y,double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // we should make sure that the parameter is a Cartesian coordinate
    // if it is not the case, then we should convert it
    @Override
    public double getCartesianDistance(Coordinate coordinate) throws InvalidDistanceException, InvalidCoordinateClassTypeException {
        // class invariant
        assertIsCoordinateValid(this);
        // preconditions
        assertIsCoordinateValid(coordinate);
        // convert coordinate to cartesian
        coordinate = convertSphericalToCartesian(coordinate);
        assertIsCartesianCoordinate(coordinate);
        double distance = calculateDistance(this, (CartesianCoordinate) coordinate);
        // post conditions
        assertIsDistanceGreaterThanZero(distance);
        return distance;
    }

    // first convert the instance to spheric coordinate
    // then calculate central angle
    @Override
    public double getCentralAngle(Coordinate coordinate) throws InvalidDistanceException, InvalidCoordinateClassTypeException {
        // class invariant
        assertIsCoordinateValid(this);
        // Preconditions
        assertIsCoordinateValid(coordinate);
        // convert coordinate to spheric
        coordinate = convertCartesianToSpherical(coordinate);
        assertIsSphericalCoordinate(coordinate);

        SphericalCoordinate sphericalCoordinate = (SphericalCoordinate) convertCartesianToSpherical(this);
        double angle = calculateCentralAngle((SphericalCoordinate)coordinate, sphericalCoordinate);

        // Post conditions
        assertIsDistanceGreaterThanZero(angle);
        return angle;
    }




    // override and change the behavior of
    // this method that is defined in the parent class
    // (abstract coordinate)
    @Override
    public Coordinate asSphericalCoordinate() throws InvalidCoordinateClassTypeException {
        // class invariant
        assertIsCoordinateValid(this);
        Coordinate coordinate =  convertCartesianToSpherical(this);
        // Post conditions
        assertIsSphericalCoordinate(coordinate);
        return coordinate;
    }


    @Override
    public boolean isEqual(Coordinate coordinate) {
        // class invariant
        assertIsCoordinateValid(this);
        // Preconditions
        assertIsCoordinateValid(coordinate);

        if(isCartesianCoordinate(coordinate))
            return super.isEqual(coordinate);
        else if (isSphericalCoordinate(coordinate))
        {
            // first convert coordinate from spherical to cartesian then
            // call super.isEqual method
            return super.isEqual(convertSphericalToCartesian(coordinate));
        }
        else
            return false;
    }







}
