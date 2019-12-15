package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.wahlzeit.exceptions.InvalidCoordinateClassTypeException;
import org.wahlzeit.exceptions.InvalidDistanceException;

public class SphericalCoordinateTest {
    private static Coordinate coordinate;

    @BeforeClass
    public static void beforeTest() {
        coordinate = new SphericalCoordinate(0.78539816339745,0.92729521800161,7.0710678118655);
    }

    @Test
    public void testIsEqual() {
        boolean isEqual = coordinate.isEqual(new SphericalCoordinate(0.78539816339745,0.92729521800161,7.0710678118655));
        Assert.assertTrue(isEqual);
        // first converting and then checking equality
        isEqual = coordinate.isEqual(new CartesianCoordinate(3,4,5));
        Assert.assertTrue(isEqual);
    }

    @Test
    public void testIsNotEqual() {
        boolean isEqual = coordinate.isEqual(new SphericalCoordinate(10.12,23.23,34.34));
        Assert.assertFalse(isEqual);
    }

    @Test
    public void testConvertingToCartesianCoordinate() throws InvalidCoordinateClassTypeException {
        Coordinate coordinate2 = coordinate.asCartesianCoordinate();
        // comparing converted object with actual value
        Assert.assertTrue(Math.abs(((CartesianCoordinate) coordinate2).getX() - 3) < 0.1);
        Assert.assertTrue(Math.abs(((CartesianCoordinate) coordinate2).getY() - 4) < 0.1);
        Assert.assertTrue(Math.abs(((CartesianCoordinate) coordinate2).getZ() - 5) < 0.1);
    }


    @Test
    public void testCalculatingDistance() throws InvalidDistanceException, InvalidCoordinateClassTypeException {
        double distance = coordinate.getCartesianDistance(new CartesianCoordinate(10.12,23.23,34.34));
        Assert.assertTrue(Math.abs(35.7955 - distance)< 0.1);
    }

    @Test
    public void testCalculatingCentralAngle() throws InvalidDistanceException, InvalidCoordinateClassTypeException {
        double centralAngle = coordinate.getCartesianDistance(new SphericalCoordinate(130,60,5));
        Assert.assertTrue(Math.abs(7.44640 - centralAngle)< 0.1);
    }

    @Test
    public void testEqualityOfSphericalObjectWithTheSameValue() {
        Coordinate sphericalObject1 = CoordinateFactory.makeCoordinate(
                CoordinateFactory.CARTESIAN_COORDINATE, 1,2,3);

        Coordinate sphericalObject2 = CoordinateFactory.makeCoordinate(
                CoordinateFactory.CARTESIAN_COORDINATE, 1,2,3);

        Assert.assertTrue(sphericalObject1.equals(sphericalObject2));
    }

    @Test
    public void testNotEqualityOfSphericalObjectWithTheDifferentValue() {
        Coordinate sphericalObject1 = CoordinateFactory.makeCoordinate(
                CoordinateFactory.CARTESIAN_COORDINATE, 0,3,3);

        Coordinate sphericalObject2 = CoordinateFactory.makeCoordinate(
                CoordinateFactory.CARTESIAN_COORDINATE, 1,2,3);

        Assert.assertFalse(sphericalObject1.equals(sphericalObject2));
    }


}
