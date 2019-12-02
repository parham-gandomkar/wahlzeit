package org.wahlzeit.model;

public class SphericCoordinate extends AbstractCoordinate {


    public SphericCoordinate(double phi, double theta, double radius) {
        this.x = phi;
        this.y = theta;
        this.z = radius;
    }

    // first convert the instance to coordinate coordinate
    // then calculate central angle
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        // class invariant
        assert (isCoordinateValid(this));
        // Preconditions
        assert(isCoordinateValid(coordinate));
        coordinate = convertCartesianToSpherical(coordinate);
        assert(isCartesianCoordinate(coordinate));
        CartesianCoordinate cartesianCoordinate = (CartesianCoordinate) convertSphericalToCartesian(this);
        double distance = calculateDistance((CartesianCoordinate) coordinate, cartesianCoordinate);
        // Postconditions
        assert(isDistanceGreaterThanZero(distance));
        return distance;
    }



    @Override
    public double getCentralAngle(Coordinate coordinate) {
            // class invariant
            assert (isCoordinateValid(this));
            // Preconditions
            assert(isCoordinateValid(coordinate));
            coordinate = convertCartesianToSpherical(coordinate);
            double angle =  calculateCentralAngle(this, (SphericCoordinate) coordinate);
            // Postconditions
            assert(isDistanceGreaterThanZero(angle));
            return angle;
    }

    // override and change the behavior of
    // this method that is defined in the parent class
    // (abstract coordinate)
    @Override
    public Coordinate asCartesianCoordinate() {
        // class invariant
        assert (isCoordinateValid(this));
        Coordinate coordinate =  convertSphericalToCartesian(this);
        // Postconditions
        assert(isSphericCoordinate(coordinate));
        return coordinate;
    }

    @Override
    public boolean isEqual(Coordinate coordinate) {
        // class invariant
        assert (isCoordinateValid(this));
        // Preconditions
        assert(isCoordinateValid(coordinate));

        if (isSphericCoordinate(coordinate))
            return super.isEqual(coordinate);
        else if (isCartesianCoordinate(coordinate))
        {
            // first convert coordinate from cartesian to spheric then
            // call super.isEqual method
            return super.isEqual(convertSphericalToCartesian(coordinate));
        }
        else
            return false;
    }


}
