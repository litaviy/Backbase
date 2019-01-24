package com.blackbase.test.main.cities.data;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by klitaviy on 1/24/19-10:46 PM.
 */
public final class CitiesServiceData {

    @NonNull
    private final TreeMap<String, TreeSet<CityModel>> mCitiesMap;
    @NonNull
    private final List<CityModel> mCitiesList;

    public CitiesServiceData(@NonNull final TreeMap<String, TreeSet<CityModel>> citiesMap,
                             @NonNull final List<CityModel> citiesList) {
        mCitiesMap = citiesMap;
        mCitiesList = citiesList;
    }

    @NonNull
    public List<CityModel> getCitiesList() {
        return mCitiesList;
    }

    @NonNull
    public TreeMap<String, TreeSet<CityModel>> getCitiesMap() {
        return mCitiesMap;
    }
}
