package com.blackbase.test.main.cities.data.filtering;

import android.support.annotation.NonNull;

import com.blackbase.test.main.cities.data.CityModel;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by klitaviy on 1/24/19-9:40 AM.
 */
public class CitiesFilterFabricImpl implements CitiesFilterFabric {

    @NonNull
    private final CitiesFilteringAlgorithm mFilteringAlgorithm;

    public CitiesFilterFabricImpl(@NonNull final CitiesFilteringAlgorithm filteringAlgorithm) {
        mFilteringAlgorithm = filteringAlgorithm;
    }

    @NonNull
    @Override
    public CitiesFilter create(@NonNull final TreeMap<String, TreeSet<CityModel>> source) {
        return new CitiesFilter(mFilteringAlgorithm, source);
    }
}
