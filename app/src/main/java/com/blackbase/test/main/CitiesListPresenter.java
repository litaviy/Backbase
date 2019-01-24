package com.blackbase.test.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.blackbase.test.common.Condition;
import com.blackbase.test.main.data.CityModel;
import com.blackbase.test.main.data.Coordinates;
import com.blackbase.test.main.data.filtering.CitiesFilter;
import com.blackbase.test.main.data.filtering.CitiesFilterFabric;
import com.blackbase.test.main.data.filtering.FilteredResultsListener;

import java.util.List;

/**
 * Created by klitaviy on 1/24/19-10:11 AM.
 */
final class CitiesListPresenter implements CitiesListContract.Presenter {

    @NonNull
    private final CitiesListContract.Coordinator mCoordinator;
    @NonNull
    private final CitiesListContract.Interactor mInteractor;
    @NonNull
    private final CitiesFilterFabric mCitiesFilterFabric;
    @Nullable
    private CitiesListContract.View mView;
    @NonNull
    private final FilteredResultsListener<CityModel> mFilteredResultsListener = new FilteredResultsListener<CityModel>() {
        @Override
        public void onFilteredResultsDelivered(@NonNull final List<CityModel> filteredResults) {
            if (Condition.isNotNull(mView)) {
                mView.updateCities(filteredResults);
            }
        }
    };
    @Nullable
    private CitiesFilter mCitiesFilter;

    CitiesListPresenter(@NonNull final CitiesListContract.Coordinator coordinator,
                        @NonNull final CitiesListContract.View view,
                        @NonNull final CitiesListContract.Interactor interactor,
                        @NonNull final CitiesFilterFabric citiesFilterFabric) {
        mCoordinator = coordinator;
        mView = view;
        mInteractor = interactor;
        mCitiesFilterFabric = citiesFilterFabric;
    }

    @Override
    public void destroy() {
        mView = null;
        mInteractor.destroy();
        if (Condition.isNotNull(mCitiesFilter)) {
            mCitiesFilter.unregisterListener(mFilteredResultsListener);
            mCitiesFilter = null;
        }
    }

    @Override
    public void onViewReady() {
        if (Condition.isNotNull(mView)) {
            mView.showProgress();

            mInteractor.init();

            mCitiesFilter = mCitiesFilterFabric.create(mInteractor.getCities());
            mCitiesFilter.registerListener(mFilteredResultsListener);

            mView.setCities(mInteractor.getCities(), this);

            mView.hideProgress();
        }
    }

    @Override
    public void onFilterFieldHasChanged(final CharSequence constraint) {
        if (Condition.isNotNull(mCitiesFilter)) {
            mCitiesFilter.filter(constraint);
        }
    }

    @Override
    public void onCityCoordinatesClick(@NonNull final Coordinates coordinates) {
        mCoordinator.cityCoordinatesClicked(coordinates);
    }
}
