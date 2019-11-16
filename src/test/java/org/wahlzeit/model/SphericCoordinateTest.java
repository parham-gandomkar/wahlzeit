package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SphericCoordinateTest {
    private static Coordinate coordinate;

    @BeforeClass
    public static void beforeTest() {
        coordinate = new SphericCoordinate(12.12,23.23,34.34);
    }

    @Test
    public void testIsEqual() {
        boolean isEqual = coordinate.isEqual(new SphericCoordinate(12.12,23.23,34.34));
        Assert.assertTrue(isEqual);
    }

    @Test
    public void testIsNotEqual() {
        boolean isEqual = coordinate.isEqual(new SphericCoordinate(10.12,23.23,34.34));
        Assert.assertFalse(isEqual);
    }

}
