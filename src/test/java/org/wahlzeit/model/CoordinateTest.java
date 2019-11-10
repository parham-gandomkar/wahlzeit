package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CoordinateTest {

    private static Coordinate coordinate;

    @BeforeClass
    public static void beforeTest() {
        coordinate = new Coordinate(12.12,23.23,34.34);

    }

    @Test
    public void testGetDistance() {
        double distance = coordinate.getDistance(new Coordinate(56, 43.11, 22.22));

        Assert.assertEquals(distance, 49.7, 49.7);
    }

    @Test
    public void testIsEqual() {
        boolean isEqual = coordinate.isEqual(new Coordinate(12.12,23.23,34.34));
        Assert.assertTrue(isEqual);
    }

    @Test
    public void testIsNotEqual() {
        boolean isEqual = coordinate.isEqual(new Coordinate(10.12,23.23,34.34));
        Assert.assertFalse(isEqual);
    }
}
