package org.wahlzeit.model;

public  class Location {

    public static final Location EMPTY = initializeEmptyLocation();

    private Coordinate coordinate;


    private Location(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    private static Location initializeEmptyLocation() {
        return new Location(Coordinate.EMPTY);
    }


    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
