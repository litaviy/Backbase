package com.blackbase.test.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.blackbase.test.common.Condition;
import com.blackbase.test.data.file.AssetsFileStreamProvider;
import com.blackbase.test.logging.Logger;
import com.blackbase.test.main.data.CityModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

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
    }

    @NonNull
    @Override
    public List<CityModel> getCities() {
        final List<CityModel> cityModels = new LinkedList<>();

        try {
            final JsonReader reader = new JsonReader(
                    new InputStreamReader(mAssetsFileStreamProvider.getInputStream(mProperties.getFileName()), "UTF-8")
            );

            reader.beginArray();
            while (reader.hasNext()) {
                final CityModel message = mGson.fromJson(reader, CityModel.class);
                cityModels.add(message);
            }
            reader.endArray();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            logException(e);
        }

        return cityModels;
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
