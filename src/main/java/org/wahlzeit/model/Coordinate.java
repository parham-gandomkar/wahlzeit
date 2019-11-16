package org.wahlzeit.model;

public interface Coordinate {

    // we do not define a separate static  method for
    // asCartesianCoordinate and asSphericCoordinate
    // instead the implementation of this method for
    // each concrete class are different
     Coordinate asCoordinate();
    // Also we do not define a separate static method for
    // getCartesianDistance and getCentralAngle
     double getDistance(Coordinate coordinateInterface);
     boolean isEqual(Coordinate coordinateInterface);
}
