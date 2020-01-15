package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.wahlzeit.exceptions.TourException;

public class TourManagerTest {

    private static TourManager tourManager;

    @BeforeClass
    public static void beforeTest() {
        tourManager = new TourManager();
    }

    @Test
    public void testCreateTour() throws TourException {
        Tour tour = tourManager.createTour("Adventure Tour");
        Assert.assertEquals("Adventure Tour", tour.getTourType().getTypeName());
    }

    @Test
    public void testAddTourType() throws TourException {
        TourType tourType = tourManager.getTourType("Adventure Tour");
        Assert.assertEquals("Adventure Tour", tourType.getTypeName());
    }
}
