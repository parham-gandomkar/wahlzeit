package org.wahlzeit.model;

public class TourPhotoFactory extends PhotoFactory {


    // singleton instance
    public static TourPhotoFactory instance;


    // helper method (factory method)  for initializing
    public static void initializeTourPhotoFactory() {
        getTourPhotoFactory();
    }

    // get method
    public static TourPhotoFactory getTourPhotoFactory() {
        if (instance == null) {
            setInstance(new TourPhotoFactory());
        }
        return instance;
    }

    // set method
    public static void setInstance(TourPhotoFactory tourPhotoFactory) {
        assertIsTheInstanceNull(instance);
        instance = tourPhotoFactory;
    }

    // the instance should be only one time initialised (singleton pattern)
    private static void assertIsTheInstanceNull(TourPhotoFactory instance) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initialize TourPhotoFactory twice");
        }
    }

    // method type = helper method (factory)
    public TourPhoto createTourPhoto() {
        return new TourPhoto();
    }



}
