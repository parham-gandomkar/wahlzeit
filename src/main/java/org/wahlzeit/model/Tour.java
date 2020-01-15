package org.wahlzeit.model;

public class Tour {
    private TourType tourType;
    private Double price;
    private String Tourleader;
    private String TravelAgency;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTourleader() {
        return Tourleader;
    }

    public void setTourleader(String tourleader) {
        Tourleader = tourleader;
    }

    public String getTravelAgency() {
        return TravelAgency;
    }

    public void setTravelAgency(String travelAgency) {
        TravelAgency = travelAgency;
    }

    public Tour(TourType tourType) {
        this.tourType = tourType;
    }

    public TourType getTourType() {
        return tourType;
    }
}
