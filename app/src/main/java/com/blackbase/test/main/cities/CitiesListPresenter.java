package com.blackbase.test.main.cities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.blackbase.test.common.Condition;
import com.blackbase.test.main.cities.data.CityModel;
import com.blackbase.test.main.cities.data.Coordinates;
import com.blackbase.test.main.cities.data.filtering.CitiesFilter;
import com.blackbase.test.main.cities.data.filtering.CitiesFilterFabric;
import com.blackbase.test.main.cities.data.filtering.FilteredResultsListener;
import com.blackbase.test.main.cities.data.worker.Worker;
import com.blackbase.test.main.cities.data.worker.WorkerListener;

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
    @Nullable
    private WorkerListener<List<CityModel>> mCitiesDataWorkerListener = new WorkerListener<List<CityModel>>() {
        @Override
        public void onInitialSetup() {
            if (Condition.isNotNull(mView)) {
                mView.showProgress();
            }
        }

        @Override
        public List<CityModel> provideResults() {
            mInteractor.init();
            return mInteractor.getCities();
        }

        @Override
        public void onResultsProvided(@Nullable final List<CityModel> results) {
            if (Condition.isNotNull(mView)) {
                mCitiesFilter = mCitiesFilterFabric.create(mInteractor.getCities());
                mCitiesFilter.registerListener(mFilteredResultsListener);

                if (Condition.isNotNull(results)) {
                    mView.setCities(results, CitiesListPresenter.this);
                }

                mView.hideProgress();
            }
        }
    };

    @Nullable
    private Worker<List<CityModel>> mCitiesDataWorker;

    CitiesListPresenter(@NonNull final CitiesListContract.Coordinator coordinator,
                        @NonNull final CitiesListContract.View view,
                        @NonNull final CitiesListContract.Interactor interactor,
                        @NonNull final CitiesFilterFabric citiesFilterFabric) {
        mCoordinator = coordinator;
        mView = view;
        mInteractor = interactor;
        mCitiesFilterFabric = citiesFilterFabric;
        mCitiesDataWorker = new Worker<>(mCitiesDataWorkerListener);
    }

    @Override
    public void destroy() {
        if (Condition.isNotNull(mCitiesDataWorker)) {
            mCitiesDataWorker.destroy();
            mCitiesDataWorker = null;
        }
        mCitiesDataWorkerListener = null;
        mView = null;
        mCoordinator.destroy();
        mInteractor.destroy();
        if (Condition.isNotNull(mCitiesFilter)) {
            mCitiesFilter.unregisterListener(mFilteredResultsListener);
            mCitiesFilter.destroy();
            mCitiesFilter = null;
        }
    }

    @Override
    public void onViewReady() {
        if (Condition.isNotNull(mCitiesDataWorker)) {
            mCitiesDataWorker.execute();
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
