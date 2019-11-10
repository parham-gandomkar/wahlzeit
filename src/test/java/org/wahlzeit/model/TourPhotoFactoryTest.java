package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

public class TourPhotoFactoryTest {

    @Test
    public void testCreatingTourPhotoInstance() {
        TourPhotoFactory tourPhotoFactory = new TourPhotoFactory();
        try {
            tourPhotoFactory.createTourPhoto();

        } catch (Exception e) {
            Assert.fail("unable to create an Instance from TourPhoto class");
        }
    }
}
