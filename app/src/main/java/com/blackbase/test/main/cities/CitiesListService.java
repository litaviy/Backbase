package com.blackbase.test.main.cities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.blackbase.test.common.Condition;
import com.blackbase.test.data.AssetsFileStreamProvider;
import com.blackbase.test.logging.Logger;
import com.blackbase.test.main.cities.data.CitiesServiceData;
import com.blackbase.test.main.cities.data.CityModel;
import com.blackbase.test.main.cities.data.CityModelComparator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by klitaviy on 1/24/19-9:52 AM.
 */
final class CitiesListService implements CitiesListContract.Service {

    @NonNull
    private final CitiesListServiceProperties mProperties;
    @NonNull
    private final AssetsFileStreamProvider mAssetsFileStreamProvider;
    @NonNull
    private final Gson mGson;
    @NonNull
    private final Comparator<CityModel> mCityModelComparator;
    @Nullable
    private Logger mLogger;

    CitiesListService(@NonNull final CitiesListServiceProperties properties,
                      @NonNull final AssetsFileStreamProvider assetsFileStreamProvider,
                      @Nullable final Logger logger) {
        mProperties = properties;
        mAssetsFileStreamProvider = assetsFileStreamProvider;
        mLogger = logger;
        // This variable has tiny context, so should initialized here.
        mGson = new GsonBuilder().create();
        mCityModelComparator = new CityModelComparator();
    }


    @NonNull
    @Override
    public CitiesServiceData getCitiesServiceData() {

        /*
        Used LinkedList because the ended size of the list is unknown.
         */
        final List<CityModel> citiesList = new LinkedList<>();
        /*
        Used TreeMap because of sorting feature and key - value access.
        Used TreeSet because of sorting feature.
         */
        final TreeMap<String, TreeSet<CityModel>> citiesMap = new TreeMap<>();

        try {
            final JsonReader reader = new JsonReader(
                    new InputStreamReader(mAssetsFileStreamProvider.getInputStream(mProperties.getFileName()), "UTF-8")
            );

            reader.beginArray();
            while (reader.hasNext()) {
                final CityModel cityModel = mGson.fromJson(reader, CityModel.class);

                // Map input
                final String firstLetterKey = cityModel.getName().substring(0, 1);
                final TreeSet<CityModel> citiesTreeSet = citiesMap.get(firstLetterKey);
                if (Condition.isNull(citiesTreeSet)) {
                    final TreeSet<CityModel> newCitiesTreeSet = new TreeSet<>(mCityModelComparator);
                    newCitiesTreeSet.add(cityModel);
                    citiesMap.put(firstLetterKey, newCitiesTreeSet);
                } else {
                    citiesTreeSet.add(cityModel);
                }
            }
            reader.endArray();
            reader.close();

            for (TreeSet<CityModel> cityModelTreeSet : citiesMap.values()) {
                citiesList.addAll(cityModelTreeSet);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logException(e);
        }

        return new CitiesServiceData(citiesMap, citiesList);
    }

    private void logException(@NonNull final Exception e) {
        if (Condition.isNotNull(mLogger)) {
            mLogger.exception(e);
        }
    }

    @Override
    public void destroy() {
        mAssetsFileStreamProvider.destroy();
        mLogger = null;
    }
}
