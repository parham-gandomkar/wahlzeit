package org.wahlzeit.model;

public  class Location {

    public static final Location EMPTY = initializeEmptyLocation();

    private String name;
    private String address;
    private Coordinate coordinate;


    private Location(String name, String address, Coordinate coordinate) {
        this.name = name;
        this.address = address;
        this.coordinate = coordinate;
    }

    private static Location initializeEmptyLocation() {
        return new Location("","",Coordinate.EMPTY);
    }


    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
