package com.blackbase.test.main;

import android.support.annotation.NonNull;

import com.blackbase.test.common.Destroyable;
import com.blackbase.test.common.Loadable;
import com.blackbase.test.main.data.CityModel;
import com.blackbase.test.main.data.Coordinates;

import java.util.List;

/**
 * Created by klitaviy on 1/23/19-10:15 PM.
 */
public interface CitiesListContract {

    interface Coordinator {
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

        @NonNull
        List<CityModel> getCities();
    }

    /*
    Might be anything we need. For this case it will be storage service.
    In another case might be gateway service.
     */
    interface Service extends Destroyable {
        @NonNull
        List<CityModel> getCities();
    }
}
