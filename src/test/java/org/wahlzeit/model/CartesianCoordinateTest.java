package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.wahlzeit.exceptions.InvalidCoordinateClassTypeException;
import org.wahlzeit.exceptions.InvalidDistanceException;

public class CartesianCoordinateTest {

    private static Coordinate coordinate;

    @BeforeClass
    public static void beforeTest() {
        coordinate = new CartesianCoordinate(4.705070719,1.505812665,0.7712572494);
    }

    @Test
    public void testIsEqual() {
        boolean isEqual = coordinate.isEqual(new CartesianCoordinate(4.705070719,1.505812665,0.7712572494));
        Assert.assertTrue(isEqual);
        // first converting and then checking equality
        isEqual = coordinate.isEqual(new SphericalCoordinate(30,60,5));
        Assert.assertTrue(isEqual);
    }

    @Test
    public void testIsNotEqual() {
        boolean isEqual = coordinate.isEqual(new CartesianCoordinate(10.12,23.23,34.34));
        Assert.assertFalse(isEqual);
    }

    @Test
    public void testConvertingToSphericalCoordinate() throws InvalidCoordinateClassTypeException {
        Coordinate coordinate2 = coordinate.asSphericalCoordinate();
        // comparing converted object with actual value
        Assert.assertTrue(Math.abs(((SphericalCoordinate) coordinate2).getPhi() - 1.4159266) < 0.1);
        Assert.assertTrue(Math.abs(((SphericalCoordinate) coordinate2).getTheta() - 0.3097396) < 0.1);
        Assert.assertTrue(Math.abs(((SphericalCoordinate) coordinate2).getRadius() - 5) < 0.1);
    }

    @Test
    public void testCalculatingDistance() throws InvalidDistanceException, InvalidCoordinateClassTypeException {
        double distance = coordinate.getCartesianDistance(new CartesianCoordinate(10.12,23.23,34.34));
        Assert.assertTrue(Math.abs(40.34999 - distance)< 0.1);
    }

    @Test
    public void testCalculatingCentralAngle() throws InvalidDistanceException, InvalidCoordinateClassTypeException {
        double centralAngle = coordinate.getCartesianDistance(new SphericalCoordinate(130,60,5));
        Assert.assertTrue(Math.abs(2.62374 - centralAngle)< 0.1);
    }

    @Test
    public void testEqualityOfCartesianObjectWithTheSameValue() {
        Coordinate cartesianCoordinate1 = CoordinateFactory.makeCoordinate(
                CoordinateFactory.CARTESIAN_COORDINATE, 1,2,3);

        Coordinate cartesianCoordinate2 = CoordinateFactory.makeCoordinate(
                CoordinateFactory.CARTESIAN_COORDINATE, 1,2,3);

        Assert.assertTrue(cartesianCoordinate1.equals(cartesianCoordinate2));
    }

    @Test
    public void testNotEqualityOfCartesianObjectWithTheDifferentValue() {
        Coordinate cartesianCoordinate1 = CoordinateFactory.makeCoordinate(
                CoordinateFactory.CARTESIAN_COORDINATE, 0,3,3);

        Coordinate cartesianCoordinate2 = CoordinateFactory.makeCoordinate(
                CoordinateFactory.CARTESIAN_COORDINATE, 1,2,3);

        Assert.assertFalse(cartesianCoordinate1.equals(cartesianCoordinate2));
    }


}
