package com.blackbase.test.main;

import android.support.annotation.NonNull;

import com.blackbase.test.main.data.CityModel;

import java.util.List;

/**
 * Created by klitaviy on 1/24/19-10:09 AM.
 */
final class CitiesListInteractor implements CitiesListContract.Interactor {

    @NonNull
    private final CitiesListService mService;

    @NonNull
    private List<CityModel> mCityModels;

    CitiesListInteractor(@NonNull final CitiesListService service) {
        mService = service;
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
