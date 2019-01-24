package com.blackbase.test.main.cities;

import android.support.annotation.NonNull;

import com.blackbase.test.main.cities.data.CityModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by klitaviy on 1/24/19-10:09 AM.
 */
final class CitiesListInteractor implements CitiesListContract.Interactor {

    @NonNull
    private final CitiesListContract.Service mService;

    @NonNull
    private List<CityModel> mCityModels;

    CitiesListInteractor(@NonNull final CitiesListContract.Service service) {
        mService = service;
        mCityModels = new ArrayList<>();
    }

    @Override
    public void init() {
        mCityModels = mService.getCities();
    }

    @NonNull
    @Override
    public List<CityModel> getCities() {
        return mCityModels;
    }

    @Override
    public void destroy() {
        mService.destroy();
    }
}
