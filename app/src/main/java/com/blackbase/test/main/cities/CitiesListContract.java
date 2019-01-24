package com.blackbase.test.main.cities;

import android.support.annotation.NonNull;

import com.blackbase.test.common.Destroyable;
import com.blackbase.test.common.Loadable;
import com.blackbase.test.main.cities.data.CitiesServiceData;
import com.blackbase.test.main.cities.data.CityModel;
import com.blackbase.test.main.cities.data.Coordinates;

import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by klitaviy on 1/23/19-10:15 PM.
 */
public interface CitiesListContract {

    interface Coordinator extends Destroyable {
        void cityCoordinatesClicked(@NonNull final Coordinates coordinates);
    }

    interface View extends Loadable {

        void setCities(@NonNull final List<CityModel> cities, @NonNull final CityClickListener cityClickListener);

        void updateCities(@NonNull final List<CityModel> cities);

        interface CityClickListener {
            void onCityCoordinatesClick(@NonNull final Coordinates coordinates);
        }
    }

    interface Presenter extends Destroyable, View.CityClickListener {
        void onViewReady();

        void onFilterFieldHasChanged(final CharSequence constraint);
    }

    interface Interactor extends Destroyable {
        void init();

        boolean hasData();
        @NonNull
        List<CityModel> getCitiesList();

        @NonNull
        TreeMap<String, TreeSet<CityModel>> getCitiesMap();
    }

    /*
    Might be anything we need. For this case it will be storage service.
    In another case might be gateway service.
     */
    interface Service extends Destroyable {
        @NonNull
        CitiesServiceData getCitiesServiceData();
    }
}
