package com.blackbase.test.main.cities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.blackbase.test.common.Condition;
import com.blackbase.test.main.cities.data.CityModel;
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
    @Nullable
    private CitiesFilter mCitiesFilter;
    @Nullable
    private Worker<Boolean> mCitiesDataWorker;
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
    private WorkerListener<Boolean> mCitiesDataWorkerListener = new WorkerListener<Boolean>() {
        @Override
        public void onInitialSetup() {
            if (Condition.isNotNull(mView)) {
                mView.showProgress();
            }
        }

        @Override
        public Boolean provideResults() {
            mInteractor.init();
            return true;
        }

        @Override
        public void onResultsProvided(@Nullable final Boolean results) {
            if (Condition.isNotNull(mView)) {
                mCitiesFilter = mCitiesFilterFabric.create(mInteractor.getCitiesMap());
                mCitiesFilter.registerListener(mFilteredResultsListener);

                if (Condition.isNotNull(results)) {
                    mView.setCities(mInteractor.getCitiesList(), CitiesListPresenter.this);
                }

                mView.hideProgress();
            }
        }
    };

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
        if (mInteractor.hasData()) {
            if (Condition.isNotNull(mView)) {
                mView.setCities(mInteractor.getCitiesList(), CitiesListPresenter.this);
            }
        } else if (Condition.isNotNull(mCitiesDataWorker)) {
            if (!mCitiesDataWorker.isWorking()) {
                mCitiesDataWorker.execute();
            }
        }
    }

    @Override
    public void onFilterFieldHasChanged(final CharSequence constraint) {
        if (Condition.isNotNull(mCitiesFilter)) {
            mCitiesFilter.filter(constraint);
        }
    }

    @Override
    public void onCityCoordinatesClick(@NonNull final CityModel cityModel) {
        mCoordinator.cityCoordinatesClicked(cityModel);
    }
}
