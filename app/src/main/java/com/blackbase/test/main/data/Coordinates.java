package com.blackbase.test.main.data;

/**
 * Created by klitaviy on 1/23/19-10:21 PM.
 */
public class Coordinates {

    private final double mLatitude;
    private final double mLongitude;

    public Coordinates(final double latitude, final double longitude) {
        mLatitude = latitude;
        mLongitude = longitude;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }
}
