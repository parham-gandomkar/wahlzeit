package org.wahlzeit.model;

import org.wahlzeit.exceptions.TourException;

import java.util.Hashtable;

public class TourManager {

    private Hashtable<Integer, Tour> tours = new Hashtable<>();
    private Hashtable<String, TourType> types = new Hashtable<>();

    public Tour createTour(String typeName) throws TourException {
        assertIsTypeNameValid(typeName);
        TourType tourType = new TourType(typeName);
        Tour tourResult = tours.get(tourType.hashCode());
        if (tourResult == null)
        {
            tourResult = tourType.createTour();
            tours.put(tourResult.hashCode(), tourResult);
        }
        return tourResult;
    }


    public TourType getTourType(String typeName){
        TourType tourType = types.get(typeName);

        if (tourType == null){
            tourType = new TourType(typeName);
            types.put(typeName,tourType);
        }
        return tourType;
    }

    private void assertIsTypeNameValid(String typeName) throws TourException {
        if (typeName.length() < 4)
            throw new TourException("invalid length", "type name must be at least 4 char");
    }
}
