package org.wahlzeit.model;

import org.wahlzeit.exceptions.TourException;

public class TourPhoto extends Photo {

    TourPhotoManager tourPhotoManager = TourPhotoManager.getTourPhotoManagerInstance();
    private double price;
    private String ort;
    private String type;

    public String getType() throws TourException {
        assertIsTypeValid(type);
        return type;
    }

    public void setType(String type) throws TourException {
        assertIsTypeValid(type);
        this.type = type;
    }



    public double getPrice() throws TourException {
        assertIsPriceValid(price);
        return price;
    }

    public void setPrice(double price) throws TourException {
        assertIsPriceValid(price);
        this.price = price;
    }

    private void assertIsPriceValid(double price) {
        if(Double.isInfinite(price)){
            throw new IllegalArgumentException("Overflow!");
        }
        if(Double.isNaN(price)) {
            throw new IllegalArgumentException("NaN!");
        }
        if (price < 0)
        {
            throw new IllegalArgumentException("price is less than zero");
        }
    }


    public String getOrt() throws TourException {
        assertIsOrtValid(ort);
        return ort;
    }

    public void setOrt(String ort) throws TourException {
        assertIsOrtValid(ort);
        this.ort = ort;
    }




    public TourPhoto() {
        super();
    }


    public TourPhoto(PhotoId id) {
        super(id);
    }

    private void assertInvariantVariables() throws TourException {
        assertIsPriceValid(price);
        assertIsOrtValid(ort);
        assertIsTypeValid(type);
    }
    private void assertIsOrtValid(String ort) throws TourException {
        if (ort == null)
            throw new TourException("TourPhoto.getOrt", "ort is null");
    }

    private void assertIsTypeValid(String type) throws TourException {
        if (type == null)
            throw new TourException("TourPhoto.getType", "type is null");
    }

}
