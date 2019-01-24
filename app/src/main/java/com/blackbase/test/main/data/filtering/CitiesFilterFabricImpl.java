package com.blackbase.test.main.data.filtering;

import android.support.annotation.NonNull;

import com.blackbase.test.main.data.CityModel;

import java.util.List;

/**
 * Created by klitaviy on 1/24/19-9:40 AM.
 */
class CitiesFilterFabricImpl implements CitiesFilterFabric {

    @NonNull
    private final CitiesFilteringAlgorithm mFilteringAlgorithm;

    CitiesFilterFabricImpl(@NonNull final CitiesFilteringAlgorithm filteringAlgorithm) {
        mFilteringAlgorithm = filteringAlgorithm;
    }

    @NonNull
    @Override
    public CitiesFilter create(@NonNull final List<CityModel> source) {
        return new CitiesFilter(mFilteringAlgorithm, source);
    }
}
