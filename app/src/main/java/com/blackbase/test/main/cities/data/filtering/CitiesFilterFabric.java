package com.blackbase.test.main.cities.data.filtering;

import android.support.annotation.NonNull;

import com.blackbase.test.main.cities.data.CityModel;

import java.util.List;

/**
 * Created by klitaviy on 1/24/19-9:39 AM.
 */
public interface CitiesFilterFabric {
    @NonNull
    CitiesFilter create(@NonNull final List<CityModel> source);
}
