package org.wahlzeit.model;

import org.wahlzeit.exceptions.InvalidCoordinateClassTypeException;
import org.wahlzeit.exceptions.InvalidDistanceException;

public interface Coordinate {

     Coordinate asCartesianCoordinate() throws InvalidCoordinateClassTypeException;
     Coordinate asSphericalCoordinate() throws InvalidCoordinateClassTypeException;

     double getCartesianDistance(Coordinate coordinateInterface) throws InvalidDistanceException, InvalidCoordinateClassTypeException;
     double getCentralAngle(Coordinate coordinateInterface) throws InvalidDistanceException, InvalidCoordinateClassTypeException;

     boolean isEqual(Coordinate coordinateInterface);
}
