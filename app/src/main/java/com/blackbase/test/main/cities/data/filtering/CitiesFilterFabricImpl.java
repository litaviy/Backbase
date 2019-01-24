package com.blackbase.test.main.cities.data.filtering;

import android.support.annotation.NonNull;

import com.blackbase.test.main.cities.data.CityModel;

import java.util.List;

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
    public CitiesFilter create(@NonNull final List<CityModel> source) {
        return new CitiesFilter(mFilteringAlgorithm, source);
    }
}
