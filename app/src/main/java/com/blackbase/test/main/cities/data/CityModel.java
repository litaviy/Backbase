package com.blackbase.test.main.cities.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by klitaviy on 1/23/19-10:16 PM.
 */
public class CityModel {


    @NonNull
    @SerializedName("country")
    private final String mCountry;
    @NonNull
    @SerializedName("name")
    private final String mName;
    @SerializedName("_id")
    private final int mId;
    @NonNull
    @SerializedName("coord")
    private final Coordinates mCoordinates;

    public CityModel(@NonNull final String country,
                     @NonNull final String name,
                     final int id,
                     @NonNull final Coordinates coordinates) {
        mCountry = country;
        mName = name;
        mId = id;
        mCoordinates = coordinates;
    }

    @NonNull
    public String getCountry() {
        return mCountry;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    public int getId() {
        return mId;
    }

    @NonNull
    public Coordinates getCoordinates() {
        return mCoordinates;
    }
}
