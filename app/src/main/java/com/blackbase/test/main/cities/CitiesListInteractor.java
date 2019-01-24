package com.blackbase.test.main.cities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.blackbase.test.common.Condition;
import com.blackbase.test.main.cities.data.CitiesServiceData;
import com.blackbase.test.main.cities.data.CityModel;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by klitaviy on 1/24/19-10:09 AM.
 */
final class CitiesListInteractor implements CitiesListContract.Interactor {

    @NonNull
    private final CitiesListContract.Service mService;

    @Nullable
    private CitiesServiceData mCitiesServiceData;

    CitiesListInteractor(@NonNull final CitiesListContract.Service service) {
        mService = service;
    }

    @Override
    public void init() {
        mCitiesServiceData = mService.getCitiesServiceData();
    }

    @Override
    public boolean hasData() {
        return mCitiesServiceData != null;
    }

    @NonNull
    @Override
    public List<CityModel> getCitiesList() {
        return Condition.isNull(mCitiesServiceData) ?
                new ArrayList<CityModel>() : mCitiesServiceData.getCitiesList();
    }

    @NonNull
    @Override
    public TreeMap<String, TreeSet<CityModel>> getCitiesMap() {
        return Condition.isNull(mCitiesServiceData) ?
                new TreeMap<String, TreeSet<CityModel>>() : mCitiesServiceData.getCitiesMap();
    }


    @Override
    public void destroy() {
        mService.destroy();
        mCitiesServiceData = null;
    }
}
