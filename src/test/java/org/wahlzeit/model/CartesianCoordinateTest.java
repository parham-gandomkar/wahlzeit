package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CartesianCoordinateTest {

    private static Coordinate coordinate;

    @BeforeClass
    public static void beforeTest() {
        coordinate = new CartesianCoordinate(12.12,23.23,34.34);
    }

    @Test
    public void testIsEqual() {
        boolean isEqual = coordinate.isEqual(new CartesianCoordinate(12.12,23.23,34.34));
        Assert.assertTrue(isEqual);
    }

    @Test
    public void testIsNotEqual() {
        boolean isEqual = coordinate.isEqual(new CartesianCoordinate(10.12,23.23,34.34));
        Assert.assertFalse(isEqual);
    }

    @Test
    public void testGetDistance() {
        double distance = coordinate.getDistance(new CartesianCoordinate(56, 43.11, 22.22));

        Assert.assertEquals(distance, 49.7, 49.7);
    }
}
