package com.blackbase.test.data.file;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.blackbase.test.common.Condition;
import com.blackbase.test.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by klitaviy on 1/23/19-9:34 PM.
 */
class AssetsFileReaderImpl implements AssetsFileReader {

    @NonNull
    private final AssetsFileStreamProvider mAssetsStreamProvider;
    @Nullable
    private Logger mLogger;


    AssetsFileReaderImpl(@NonNull final AssetsFileStreamProvider assetsStreamProvider,
                         @Nullable final Logger logger) {
        mAssetsStreamProvider = assetsStreamProvider;
        mLogger = logger;
    }

    @Nullable
    @Override
    public JSONObject getJson(@NonNull String fileName) {
        try {
            final InputStream is = mAssetsStreamProvider.getInputStream(fileName);
            final int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            final String jsonAsString = new String(buffer, "UTF-8");

            return new JSONObject(jsonAsString);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            if (Condition.isNotNull(mLogger)) {
                mLogger.exception(e);
            }

            return null;
        }
    }

    @Override
    public void destroy() {
        mAssetsStreamProvider.destroy();
        mLogger = null;
    }
}
