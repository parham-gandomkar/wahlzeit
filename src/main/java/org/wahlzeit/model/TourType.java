package org.wahlzeit.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TourType {

    private String typeName;
    private TourType superType;
    private Set<TourType> tourTypes = new HashSet<>();

    public TourType(String typeName) {
        this.typeName = typeName;
    }

    public Tour createTour()
    {
        return new Tour(this);
    }

    public void addTourType(TourType tourType) {
        tourType.setSuperType(this);
        tourTypes.add(tourType);
    }

    public boolean isSubType() {
        return superType != null;
    }

    public Iterator<TourType> getTourTypes() {
        return tourTypes.iterator();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public TourType getSuperType() {
        return superType;
    }

    public void setSuperType(TourType superType) {
        this.superType = superType;
    }
}
