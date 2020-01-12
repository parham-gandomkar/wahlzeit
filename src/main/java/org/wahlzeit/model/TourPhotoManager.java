package org.wahlzeit.model;


@PatternInstance(
        patternName = "Singleton",
        participants = {
                "PhotoManager", "TourPhotoManager"
        }
)
public class TourPhotoManager extends PhotoManager {

    private static TourPhotoManager instance = new TourPhotoManager();
    private TourPhotoFactory PhotoFactory = TourPhotoFactory.getTourPhotoFactory();

    public static TourPhotoManager getTourPhotoManagerInstance() {
        return instance;
    }


    // get photo from id
    public Photo getPhotoFromId(PhotoId id){
        assertIsPhotoIdNull(id);

        Photo result = doGetPhotoFromId(id);
        if (result == null) {
            result = loadPhoto(id);
        }
        return result;
    }

    private void assertIsPhotoIdNull(PhotoId id) {
        if (id == null) {
            throw new IllegalArgumentException("Argument Photo Id is null");
        }
    }

    private Photo loadPhoto(PhotoId id) {
        Photo result;
        result = TourPhotoFactory.getInstance().loadPhoto(id);
        if (result != null) {
            doAddPhoto(result);
        } else {
            throw new IllegalArgumentException("Couldn't load a photo from id");
        }
        return result;
    }
}
