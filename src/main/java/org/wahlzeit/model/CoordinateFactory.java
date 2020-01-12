package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Map;


@PatternInstance(
        patternName = "Factory",
        participants = {
                "CoordinateFactory"
        }
)
public class CoordinateFactory {

    private static Map<String, CartesianCoordinate> cartesianCoordinateHashMap = new HashMap<>();
    private static Map<String, SphericalCoordinate> sphericalCoordinateHashMap = new HashMap<>();
    public static final String CARTESIAN_COORDINATE = "cartesian";
    public static final String SPHERICAL_COORDINATE = "spherical";

    public static Coordinate makeCoordinate(String coordinate, double a, double b, double c) {
        if (coordinate.equals(CARTESIAN_COORDINATE)) {
            String key = CartesianCoordinate.getKeyString(a,b,c);
            if (cartesianCoordinateHashMap.containsKey(key))
                return cartesianCoordinateHashMap.get(key);
            else
            {
                CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(a,b,c);
                cartesianCoordinateHashMap.put(key,cartesianCoordinate);
                return cartesianCoordinate;
            }
        }
        else if (coordinate.equals(SPHERICAL_COORDINATE)) {
            String key = SphericalCoordinate.getKeyString(a,b,c);
            if (sphericalCoordinateHashMap.containsKey(key))
                return sphericalCoordinateHashMap.get(key);
            else
            {
                SphericalCoordinate sphericalCoordinate = new SphericalCoordinate(a,b,c);
                sphericalCoordinateHashMap.put(key,sphericalCoordinate);
                return sphericalCoordinate;
            }
        }
        return null;
    }
}
