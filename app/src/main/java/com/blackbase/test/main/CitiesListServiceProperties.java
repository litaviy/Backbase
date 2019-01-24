package com.blackbase.test.main;

import android.support.annotation.NonNull;

/**
 * Created by klitaviy on 1/24/19-9:51 AM.
 */
public class CitiesListServiceProperties {

    @NonNull
    private final String mFileName;

    public CitiesListServiceProperties(@NonNull final String fileName) {
        mFileName = fileName;
    }

    @NonNull
    public String getFileName() {
        return mFileName;
    }
}
