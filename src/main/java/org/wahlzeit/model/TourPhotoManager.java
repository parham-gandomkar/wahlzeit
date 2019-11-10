package org.wahlzeit.model;

public class TourPhotoManager extends PhotoManager {

    private static TourPhotoManager instance = new TourPhotoManager();
    private TourPhotoFactory PhotoFactory = TourPhotoFactory.getTourPhotoFactory();

    public static TourPhotoManager getTourPhotoManagerInstance() {
        return instance;
    }

}
