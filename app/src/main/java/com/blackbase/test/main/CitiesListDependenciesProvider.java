package com.blackbase.test.main;

import android.support.annotation.NonNull;

import com.blackbase.test.common.loader.Loader;
import com.blackbase.test.common.loader.LoaderProvider;

/**
 * Created by klitaviy on 1/24/19-10:53 AM.
 */
public final class CitiesListDependenciesProvider {
    @NonNull
    public static Loader provideLoader() {
        return LoaderProvider.getLoader();
    }
}
