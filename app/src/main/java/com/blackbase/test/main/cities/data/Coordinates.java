package com.blackbase.test.main.cities.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by klitaviy on 1/23/19-10:21 PM.
 */
public class Coordinates {

    @SerializedName("lat")
    private final float mLatitude;
    @SerializedName("lon")
    private final float mLongitude;

    public Coordinates(final float latitude, final float longitude) {
        mLatitude = latitude;
        mLongitude = longitude;
    }

    public float getLatitude() {
        return mLatitude;
    }

    public float getLongitude() {
        return mLongitude;
    }
}
