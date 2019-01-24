package com.blackbase.test.main;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.blackbase.test.common.loader.Loader;
import com.blackbase.test.common.loader.LoaderProvider;
import com.blackbase.test.data.file.AssetsFileStreamProvider;
import com.blackbase.test.data.file.AssetsFileStreamProviderImpl;
import com.blackbase.test.logging.LoggerProvider;
import com.blackbase.test.main.data.filtering.CitiesFilterFabric;
import com.blackbase.test.main.data.filtering.CitiesFilterFabricImpl;
import com.blackbase.test.main.data.filtering.CitiesFilteringAlgorithm;
import com.blackbase.test.main.data.filtering.CitiesFilteringAlgorithmImpl;

/**
 * Created by klitaviy on 1/24/19-10:53 AM.
 */
final class CitiesListDependenciesProvider {
    @NonNull
    public static Loader provideLoader() {
        return LoaderProvider.getLoader();
    }

    @NonNull
    public static CitiesListContract.Presenter providePresenter(@NonNull final Activity activity,
                                                                @NonNull final CitiesListContract.View view) {

        final CitiesListContract.Coordinator coordinator = new CitiesListCoordinator(activity);

        // Properties customization.
        final CitiesListServiceProperties serviceProperties = new CitiesListServiceProperties("cities.json");
        final AssetsFileStreamProvider streamProvider = new AssetsFileStreamProviderImpl(activity.getAssets());
        final CitiesListContract.Service service = new CitiesListService(
                serviceProperties, streamProvider, LoggerProvider.provide()
        );

        final CitiesListContract.Interactor interactor = new CitiesListInteractor(service);

        final CitiesFilteringAlgorithm filteringAlgorithm = new CitiesFilteringAlgorithmImpl();
        final CitiesFilterFabric citiesFilterFabric = new CitiesFilterFabricImpl(filteringAlgorithm);

        return new CitiesListPresenter(coordinator, view, interactor, citiesFilterFabric);
    }
}
