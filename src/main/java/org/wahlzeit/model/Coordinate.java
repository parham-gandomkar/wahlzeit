package org.wahlzeit.model;

public interface Coordinate {

     Coordinate asCartesianCoordinate();
     Coordinate asSphericCoordinate();

     double getCartesianDistance(Coordinate coordinateInterface);
     double getCentralAngle(Coordinate coordinateInterface);

     boolean isEqual(Coordinate coordinateInterface);
}
