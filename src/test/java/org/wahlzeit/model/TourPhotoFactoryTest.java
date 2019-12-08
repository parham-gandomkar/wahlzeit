package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.wahlzeit.exceptions.TourException;

public class TourPhotoFactoryTest {

    private static TourPhoto tourPhoto;

    @BeforeClass
    public static void beforeTest() {
        TourPhotoFactory factory = new TourPhotoFactory();
        tourPhoto = factory.createTourPhoto();
    }


    @Test
    public void testCreatingTourPhotoInstance() {
        TourPhotoFactory tourPhotoFactory = new TourPhotoFactory();
        try {
            tourPhotoFactory.createTourPhoto();

        } catch (Exception e) {
            Assert.fail("unable to create an Instance from TourPhoto class");
        }
    }


    @Test
    public void testTourPhotoInstance() throws TourException {
        tourPhoto.setOrt("tehran");
        try {
            tourPhoto.setPrice(-23);
            Assert.fail("negative number can not be as price");
        }
        catch (Exception e)
        {
        }
        try {
            tourPhoto.setType(null);
            Assert.fail("null type can not be as tour type");
        }
        catch (Exception e)
        {
        }
    }
}
