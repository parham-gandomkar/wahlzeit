package org.wahlzeit.model;

public class CartesianCoordinate extends AbstractCoordinate {


    public CartesianCoordinate(double x, double y,double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // we should make sure that the parameter is a Cartesian coordinate
    // if it is not the case, then we should convert it
    @Override
    public double getCartesianDistance(Coordinate coordinate) {
        // class invariant
        assert (isCoordinateValid(this));
        // Preconditions
        assert(isCoordinateValid(coordinate));
        // convert coordinate to cartesian
        coordinate = convertSphericalToCartesian(coordinate);
        assert(isCartesianCoordinate(coordinate));
        double distance = calculateDistance(this, (CartesianCoordinate) coordinate);
        // Postconditions
        assert(isDistanceGreaterThanZero(distance));
        return distance;
    }

    // first convert the instance to spheric coordinate
    // then calculate central angle
    @Override
    public double getCentralAngle(Coordinate coordinate) {
        // class invariant
        assert (isCoordinateValid(this));
        // Preconditions
        assert(isCoordinateValid(coordinate));
        // convert coordinate to spheric
        coordinate = convertCartesianToSpherical(coordinate);
        assert(isSphericCoordinate(coordinate));

        SphericCoordinate sphericCoordinate = (SphericCoordinate) convertCartesianToSpherical(this);
        double angle = calculateCentralAngle((SphericCoordinate)coordinate, sphericCoordinate);

        // Postconditions
        assert(isDistanceGreaterThanZero(angle));
        return angle;
    }




    // override and change the behavior of
    // this method that is defined in the parent class
    // (abstract coordinate)
    @Override
    public Coordinate asSphericCoordinate() {
        // class invariant
        assert (isCoordinateValid(this));
        Coordinate coordinate =  convertCartesianToSpherical(this);
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

        if(isCartesianCoordinate(coordinate))
            return super.isEqual(coordinate);
        else if (isSphericCoordinate(coordinate))
        {
            // first convert coordinate from spheric to cartesian then
            // call super.isEqual method
            return super.isEqual(convertSphericalToCartesian(coordinate));
        }
        else
            return false;
    }







}
