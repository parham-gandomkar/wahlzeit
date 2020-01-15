package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.wahlzeit.exceptions.TourException;

import java.util.Iterator;

public class TourTypeTest {

    private static TourType familyTourType;

    @BeforeClass
    public static void beforeTest() {
        familyTourType = new TourType("Family Tour");
    }

    @Test
    public void testIsSubType(){
        TourType museumTourType = new TourType("Museum");
        familyTourType.addTourType(museumTourType);
        Assert.assertTrue(museumTourType.isSubType());
        Assert.assertFalse(familyTourType.isSubType());
    }

    @Test
    public void testGetSubTourTypes()  {
        Iterator<TourType> subTours = familyTourType.getTourTypes();
        Assert.assertEquals(subTours.next().getTypeName(), "Museum");
    }


}
