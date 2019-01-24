package com.blackbase.test.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.blackbase.test.common.Condition;
import com.blackbase.test.data.file.AssetsFileReader;
import com.blackbase.test.logging.Logger;
import com.blackbase.test.main.data.CityModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by klitaviy on 1/24/19-9:52 AM.
 */
final class CitiesListService implements CitiesListContract.Service {

    @NonNull
    private final CitiesListServiceProperties mProperties;
    @NonNull
    private final AssetsFileReader mAssetsFileReader;
    @NonNull
    private final Gson mGson;
    @Nullable
    private Logger mLogger;

    CitiesListService(@NonNull final CitiesListServiceProperties properties,
                      @NonNull final AssetsFileReader assetsFileReader,
                      @Nullable final Logger logger) {
        mProperties = properties;
        mAssetsFileReader = assetsFileReader;
        mLogger = logger;
        // This variable has tiny context, so should initialized here.
        mGson = new GsonBuilder().create();
    }

    @NonNull
    @Override
    public List<CityModel> getCities() {
        final JSONObject citiesJson = mAssetsFileReader.getJson(mProperties.getFileName());
        if (Condition.isNull(citiesJson)) {
            logException(new Exception("mAssetsFileReader provided nullable JSON!"));
            return new ArrayList<>();
        } else {
            final List<CityModel> cityModels = mGson.fromJson(citiesJson.toString(), new TypeToken<List<CityModel>>() {
            }.getType());

            if (Condition.isNull(cityModels)) {
                logException(new Exception("Unable to create List<CityModel> from given JSON!"));
                return new ArrayList<>();
            } else {
                return cityModels;
            }
        }
    }

    private void logException(@NonNull final Exception e) {
        if (Condition.isNotNull(mLogger)) {
            mLogger.exception(e);
        }
    }

    @Override
    public void destroy() {
        mLogger = null;
    }
}
